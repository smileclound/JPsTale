package jme3utilities.sky;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.texture.Texture;

public class SkyAppState extends BaseAppState {

    // *************************************************************************
    // constants

    /**
     * maximum number of cloud layers
     */
    final protected static int numCloudLayers = 6;
    /**
     * number of samples in each longitudinal quadrant of a major dome,
     * including both its top and rim (&ge;2)
     */
    final private static int quadrantSamples = 16;
    /**
     * number of samples around the rim of a major dome (&ge;3)
     */
    final private static int rimSamples = 60;
    /**
     * message logger for this class
     */
    final private static Logger logger = Logger.getLogger(SkyAppState.class.getName());
    // *************************************************************************
    // fields
    /**
     * true to counteract rotation of the controlled node, false to allow
     * rotation
     */
    private boolean stabilizeFlag = false;
    /**
     * information about individual cloud layers
     */
    final protected CloudLayer[] cloudLayers;
    /**
     * mesh of the bottom dome, or null if there's no bottom dome
     */
    protected DomeMesh bottomMesh = null;
    /**
     * mesh of the dome with clouds
     */
    protected DomeMesh cloudsMesh = null;
    /**
     * mesh of the dome with sun, moon, and horizon haze
     */
    protected DomeMesh topMesh = null;

    /**
     * phase angle of the moon: default corresponds to a 100% full moon
     */
    protected float phaseAngle = FastMath.PI;
    /**
     * flattened dome for clouds only: set by initialize()
     */
    protected Geometry cloudsOnlyDome = null;
    /**
     * bottom dome: set by initialize()
     */
    protected Geometry bottomDome = null;
    /**
     * dome representing the northern stars: set by initialize()
     */
    protected Geometry northDome = null;
    /**
     * dome representing the southern stars: set by initialize()
     */
    protected Geometry southDome = null;
    /**
     * dome representing the sun, moon, and horizon haze: set by initialize()
     */
    protected Geometry topDome = null;
    /**
     * material for bottom dome: set by constructor
     */
    protected Material bottomMaterial;
    /**
     * material of the dome with clouds: set by constructor
     */
    protected SkyMaterial cloudsMaterial;
    /**
     * material of the top dome: set by constructor
     */
    protected SkyMaterial topMaterial;

    // *************************************************************************
    // constants

    /**
     * base color of the daytime sky: pale blue
     */
    final private static ColorRGBA colorDay = new ColorRGBA(0.4f, 0.6f, 1f, Constants.alphaMax);
    /**
     * light color and intensity for full moonlight: bluish gray
     */
    final private static ColorRGBA moonLight = new ColorRGBA(0.4f, 0.4f, 0.6f, Constants.alphaMax);
    /**
     * light color and intensity for moonless night: nearly black
     */
    final private static ColorRGBA starLight = new ColorRGBA(0.03f, 0.03f, 0.03f, Constants.alphaMax);
    /**
     * light color and intensity for full sunlight: yellowish white
     */
    final private static ColorRGBA sunLight = new ColorRGBA(0.8f, 0.8f, 0.75f, Constants.alphaMax);
    /**
     * color blended in around sunrise and sunset: ruddy orange
     */
    final private static ColorRGBA twilight = new ColorRGBA(0.6f, 0.3f, 0.15f, Constants.alphaMax);
    /**
     * extent of the twilight periods before sunrise and after sunset, expressed
     * as the sine of the sun's angle below the horizon (&le;1, &ge;0)
     */
    final private static float limitOfTwilight = 0.1f;
    /**
     * object index for the moon
     */
    final public static int moonIndex = 1;
    /**
     * object index for the sun
     */
    final public static int sunIndex = 0;
    /**
     * light direction for starlight: don't make this perfectly vertical because
     * that might cause shadow map aliasing
     */
    final private static Vector3f starlightDirection = new Vector3f(1f, 9f, 1f).normalizeLocal();
    // *************************************************************************
    // fields
    /**
     * true if clouds modulate the main light, false for steady light (the
     * default)
     */
    private boolean cloudModulationFlag = false;
    /**
     * texture scale for moon images; larger value gives a larger moon
     * <p>
     * The default value (0.02) exaggerates the moon's size by a factor of 8.
     */
    private float moonScale = 0.02f;
    /**
     * texture scale for sun images; larger value would give a larger sun
     * <p>
     * The default value (0.08) exaggerates the sun's size by a factor of 8.
     */
    private float sunScale = 0.08f;
    /**
     * phase of the moon: default is FULL
     */
    private LunarPhase phase = LunarPhase.FULL;
    /**
     * orientations of the sun and stars relative to the observer
     */
    final private SunAndStars sunAndStars = new SunAndStars();
    /**
     * lights, shadows, and viewports to update
     */
    final private Updater updater = new Updater();

    /**
     * ambient light-source in the scene
     */
    private AmbientLight ambientLight = null;
    /**
     * main light-source in the scene, which represents the sun or moon
     */
    private DirectionalLight mainLight = null;

    private AssetManager assetManager;
    private Camera camera;
    private ViewPort viewPort;
    private Node rootNode;

    /**
     * 初始为 6:00 a.m.
     */
    final private TimeOfDay timeOfDay = new TimeOfDay(6.0f);

    public SkyAppState() {
        rootNode = new Node("sky node");
        rootNode.setQueueBucket(Bucket.Sky);
        rootNode.setShadowMode(ShadowMode.Off);

        cloudLayers = new CloudLayer[numCloudLayers];
    }

    @Override
    protected void initialize(Application app) {
        assetManager = app.getAssetManager();
        camera = app.getCamera();
        viewPort = app.getViewPort();

        /*
         * 北半球
         */
        DomeMesh hemisphere = new DomeMesh(rimSamples, quadrantSamples);
        Material north = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        north.setTexture("ColorMap", assetManager.loadTexture("Textures/skies/star-maps/northern.png"));

        northDome = new Geometry("north", hemisphere);
        northDome.setMaterial(north);

        /*
         * 南半球
         */
        Material south = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        south.setTexture("ColorMap", assetManager.loadTexture("Textures/skies/star-maps/southern.png"));

        southDome = new Geometry("south", hemisphere);
        southDome.setMaterial(south);

        /*
         * 顶层天球：太阳、月亮
         */
        topMesh = new DomeMesh(rimSamples, quadrantSamples);

        topMaterial = new SkyMaterial(assetManager, 2, 0);
        topMaterial.initialize();
        topMaterial.addHaze();
        /*
         * 月相
         */
        topMaterial.addObject(moonIndex, "Textures/skies/moon/full.png");
        /*
         * 日相
         */
        topMaterial.addObject(sunIndex, "Textures/skies/suns/hazy-disc.png");
        topDome = new Geometry("top", topMesh);
        topDome.setMaterial(topMaterial);

        /*
         * 底层天球
         */
        bottomMesh = new DomeMesh(rimSamples, 2);

        bottomMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

        bottomDome = new Geometry("bottom", bottomMesh);
        bottomDome.setMaterial(bottomMaterial);
        Quaternion upsideDown = new Quaternion();
        upsideDown.lookAt(Vector3f.UNIT_X, Vector3f.UNIT_Y.negate());
        bottomDome.setLocalRotation(upsideDown);

        /*
         * 云层
         */
        cloudsMaterial = new SkyMaterial(assetManager, 0, numCloudLayers);
        cloudsMaterial.initialize();
        cloudsMaterial.getAdditionalRenderState().setDepthWrite(false);
        cloudsMaterial.setClearColor(ColorRGBA.BlackNoAlpha);

        for (int layerIndex = 0; layerIndex < numCloudLayers; layerIndex++) {
            cloudLayers[layerIndex] = new CloudLayer(cloudsMaterial, layerIndex);
        }
        setCloudiness(0.5f);

        cloudsMesh = new DomeMesh(rimSamples, quadrantSamples);

        cloudsOnlyDome = new Geometry("clouds", cloudsMesh);
        cloudsOnlyDome.setMaterial(cloudsMaterial);
        cloudsOnlyDome.setLocalScale(1f, 0.3f, 1f);// 云层的高度，使其更贴近观察者。
        cloudsOnlyDome.setLocalTranslation(0f, 0f, 0f);// 云层的位置

        /*
         * 根据渲染循序，从外到内添加天体。
         */
        rootNode.attachChild(northDome);
        rootNode.attachChild(southDome);
        rootNode.attachChild(topDome);
        rootNode.attachChild(bottomDome);
        rootNode.attachChild(cloudsOnlyDome);

        /*
         * Add a new filter post-processor.
         */
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        viewPort.addProcessor(fpp);
        BloomFilter bloom = new BloomFilter(BloomFilter.GlowMode.Objects);
        fpp.addFilter(bloom);

        /*
         * 设置观察者的维度
         */
        sunAndStars.setObserverLatitude(0f);
        moonScale = 0.031f * topMesh.uvScale / FastMath.HALF_PI;
        sunScale = 0.031f * topMesh.uvScale / (Constants.discDiameter * FastMath.HALF_PI);
        sunAndStars.setSolarLongitude(0f);
        setTopVerticalAngle(FastMath.HALF_PI);

        /*
         * 设置亮度
         */
        mainLight = new DirectionalLight();
        mainLight.setName("main");

        ambientLight = new AmbientLight();
        ambientLight.setName("ambient");

        updater.setViewPort(viewPort);
        updater.setAmbientLight(ambientLight);
        updater.setMainLight(mainLight);

        timeOfDay.setRate(1000f);// 时间流逝速度为现实的1000倍
        getStateManager().attach(timeOfDay);
    }

    @Override
    protected void cleanup(Application app) {
    }

    @Override
    protected void onEnable() {
        ((SimpleApplication) getApplication()).getRootNode().attachChild(rootNode);
    }

    @Override
    protected void onDisable() {
        rootNode.removeFromParent();
    }

    public void update(float tpf) {
        updateClouds(tpf);
        /*
         * Translate the sky node to center the sky on the camera.
         */
        Vector3f cameraLocation = camera.getLocation();
        rootNode.setLocalTranslation(cameraLocation);
        /*
         * Scale the sky node so that its furthest geometries are midway between
         * the near and far planes of the view frustum.
         */
        float far = camera.getFrustumFar();
        float near = camera.getFrustumNear();
        float radius = (near + far) / 2f;
        rootNode.setLocalScale(radius);

        if (stabilizeFlag) {
            /*
             * Counteract rotation of the controlled node.
             */
            rootNode.setLocalRotation(Quaternion.IDENTITY);
        }

        updateAll();
    }

    /**
     * Compute the contribution of the moon to the nighttime illumination mix
     * using its phase, assuming it is above the horizon.
     *
     * @return fraction (&le;1, &ge;0) 1 &rarr; full moon, 0 &rarr; no
     *         contribution
     */
    public float getMoonIllumination() {
        float fullAngle = FastMath.abs(phaseAngle - FastMath.PI);
        float weight = 1f - FastMath.saturate(fullAngle * 0.6f);

        assert weight >= 0f : weight;
        assert weight <= 1f : weight;
        return weight;
    }

    /**
     * Alter the opacity of all cloud layers.
     *
     * @param newAlpha
     *            desired opacity of the cloud layers (&le;1, &ge;0)
     */
    public void setCloudiness(float newAlpha) {
        for (int layer = 0; layer < numCloudLayers; layer++) {
            cloudLayers[layer].setOpacity(newAlpha);
        }
    }

    /**
     * Alter the vertical position of the clouds-only dome. When the scene's
     * horizon lies below the astronomical horizon, it may help to depress the
     * clouds-only dome.
     *
     * @param newYOffset
     *            desired vertical offset as a fraction of the dome height
     *            (&lt;1, &ge;0 when flattening&gt;0; 0 when flattening=0)
     */
    public void setCloudYOffset(float newYOffset) {
        if (cloudsOnlyDome == null) {
            if (newYOffset != 0f) {
                logger.log(Level.SEVERE, "offset={0}", newYOffset);
                throw new IllegalArgumentException("offset should be 0");
            }
            return;
        }
        if (!(newYOffset >= 0f && newYOffset < 1f)) {
            logger.log(Level.SEVERE, "offset={0}", newYOffset);
            throw new IllegalArgumentException("offset should be between 0 and 1");
        }

        float deltaY = -newYOffset * cloudsOnlyDome.getLocalScale().y;
        cloudsOnlyDome.setLocalTranslation(0f, deltaY, 0f);
    }

    /**
     * Alter an object's color map texture.
     *
     * @param objectIndex
     *            which object (&ge;0)
     * @param newColorMap
     *            texture to apply (not null)
     */
    public void setObjectTexture(int objectIndex, Texture newColorMap) {
        topMaterial.addObject(objectIndex, newColorMap);
    }

    /**
     * Alter the stabilize flag.
     *
     * @param newState
     *            true to counteract rotation of the controlled node, false to
     *            allow rotation
     */
    public void setStabilizeFlag(boolean newState) {
        stabilizeFlag = newState;
    }

    /**
     * Alter the vertical angle of the top dome, which is Pi/2 by default. If
     * the terrain's horizon lies below the horizontal, increase this angle (to
     * values greater than Pi/2) to avoid clipping the sun and moon when they
     * are near the horizontal.
     *
     * @param newAngle
     *            desired angle from the zenith to the rim of the top dome (in
     *            radians, &lt;1.785, &gt;0)
     */
    public void setTopVerticalAngle(float newAngle) {
        if (!(newAngle > 0f && newAngle < 1.785f)) {
            logger.log(Level.SEVERE, "angle={0}", newAngle);
            throw new IllegalArgumentException("angle should be between 0 and 1.785");
        }

        topMesh.setVerticalAngle(newAngle);
        topDome.setMesh(topMesh);

        bottomMesh.setVerticalAngle(FastMath.PI - newAngle);
        bottomDome.setMesh(bottomMesh);
    }
    // *************************************************************************
    // protected methods

    /**
     * Apply a modified version of the base color to each cloud layer.
     * <p>
     * The return value is used in calculating ambient light intensity.
     *
     * @param baseColor
     *            (not null, unaffected, alpha is ignored)
     * @param sunUp
     *            true if sun is above the horizon, otherwise false
     * @param moonUp
     *            true if moon is above the horizon, otherwise false
     * @return new instance (alpha is undefined)
     */
    protected ColorRGBA updateCloudsColor(ColorRGBA baseColor, boolean sunUp, boolean moonUp) {

        ColorRGBA cloudsColor;
        float max = MyMath.max(baseColor.r, baseColor.g, baseColor.b);
        if (max <= 0f) {
            cloudsColor = new ColorRGBA(1f, 1f, 1f, baseColor.a);
        } else {
            cloudsColor = baseColor.mult(1f / max);
        }

        if (!sunUp) {
            /*
             * At night, darken the clouds by 15%-75%.
             */
            float cloudBrightness = 0.25f;
            if (moonUp) {
                cloudBrightness += 0.6f * getMoonIllumination();
            }
            cloudsColor.multLocal(cloudBrightness);
        }
        for (int layer = 0; layer < numCloudLayers; layer++) {
            cloudLayers[layer].setColor(cloudsColor);
        }

        return cloudsColor;
    }

    /**
     * Compute the direction to the center of the moon.
     *
     * @return new unit vector in world (horizontal) coordinates
     */
    public Vector3f getMoonDirection() {
        float solarLongitude = sunAndStars.getSolarLongitude();
        float celestialLongitude = solarLongitude + phaseAngle;
        celestialLongitude = (celestialLongitude % FastMath.TWO_PI + FastMath.TWO_PI) % FastMath.TWO_PI;
        Vector3f worldDirection = sunAndStars.convertToWorld(0f, celestialLongitude);

        return worldDirection;
    }

    /**
     * Alter the phase of the moon to a pre-set value.
     *
     * @param newPreset
     *            (or null to hide the moon)
     */
    final public void setPhase(LunarPhase newPreset) {
        if (newPreset == LunarPhase.CUSTOM) {
            return;
        }

        phase = newPreset;
        if (newPreset != null) {
            phaseAngle = newPreset.longitudeDifference();
            String assetPath = newPreset.imagePath();
            topMaterial.addObject(moonIndex, assetPath);
        }
    }

    /**
     * simulation time for cloud layer animations
     */
    private float cloudsAnimationTime = 0f;
    /**
     * rate of motion for cloud layer animations (1 &rarr; standard)
     */
    private float cloudsRelativeSpeed = 1f;

    /**
     * Update the cloud layers. (Invoked once per frame.)
     *
     * @param elapsedTime
     *            since the previous update (in seconds, &ge;0)
     */
    private void updateClouds(float elapsedTime) {
        assert elapsedTime >= 0f : elapsedTime;
        cloudsAnimationTime += elapsedTime * cloudsRelativeSpeed;
        for (int layer = 0; layer < numCloudLayers; layer++) {
            cloudLayers[layer].updateOffset(cloudsAnimationTime);
        }
    }

    // *************************************************************************
    // private methods

    /**
     * Compute where mainDirection intersects the cloud dome in the dome's local
     * coordinates, accounting for the dome's flattening and vertical offset.
     *
     * @param mainDirection
     *            (unit vector with non-negative y-component)
     * @return new unit vector
     */
    private Vector3f intersectCloudDome(Vector3f mainDirection) {
        assert mainDirection != null;
        assert mainDirection.isUnitVector() : mainDirection;
        assert mainDirection.y >= 0f : mainDirection;

        double mx = mainDirection.x;
        double mz = mainDirection.z;
        double cosSquared = mx * mx + mz * mz;
        if (cosSquared == 0.0) {
            /*
             * Special case when the main light is directly overhead.
             */
            return Vector3f.UNIT_Y.clone();
        }

        float deltaY;
        float semiMinorAxis;
        if (cloudsOnlyDome == null) {
            deltaY = 0f;
            semiMinorAxis = 1f;
        } else {
            Vector3f offset = cloudsOnlyDome.getLocalTranslation();
            assert offset.x == 0f : offset;
            assert offset.y <= 0f : offset;
            assert offset.z == 0f : offset;
            deltaY = offset.y;

            Vector3f scale = cloudsOnlyDome.getLocalScale();
            assert scale.x == 1f : scale;
            assert scale.y > 0f : scale;
            assert scale.z == 1f : scale;
            semiMinorAxis = scale.y;
        }
        /*
         * Solve for the most positive root of a quadratic equation in w =
         * sqrt(x^2 + z^2). Use double precision arithmetic.
         */
        double cosAltitude = Math.sqrt(cosSquared);
        double tanAltitude = mainDirection.y / cosAltitude;
        double smaSquared = semiMinorAxis * semiMinorAxis;
        double a = tanAltitude * tanAltitude + smaSquared;
        assert a > 0.0 : a;
        double b = -2.0 * deltaY * tanAltitude;
        double c = deltaY * deltaY - smaSquared;
        double discriminant = b * b - 4.0 * a * c;
        assert discriminant >= 0.0 : discriminant;
        double w = (-b + Math.sqrt(discriminant)) / (2.0 * a);

        double distance = w / cosAltitude;
        if (distance > 1.0) {
            /*
             * Squash rounding errors.
             */
            distance = 1.0;
        }
        float x = (float) (mainDirection.x * distance);
        float y = (float) Math.sqrt(1.0 - w * w);
        float z = (float) (mainDirection.z * distance);
        Vector3f result = new Vector3f(x, y, z);

        assert result.isUnitVector() : result;
        return result;
    }

    /**
     * Compute the clockwise (left-handed) rotation of the moon's texture
     * relative to the sky's texture.
     *
     * @param longitude
     *            the moon's celestial longitude (in radians)
     * @param uvCenter
     *            texture coordinates of the moon's center (not null)
     * @return new unit vector with its x-component equal to the cosine of the
     *         rotation angle and its y-component equal to the sine of the
     *         rotation angle
     */
    private Vector2f lunarRotation(float longitude, Vector2f uvCenter) {
        assert uvCenter != null;
        /*
         * Compute UV coordinates for 0.01 radians north of the center of the
         * moon.
         */
        Vector3f north = sunAndStars.convertToWorld(1f, longitude);
        Vector2f uvNorth = topMesh.directionUV(north);
        if (uvNorth != null) {
            Vector2f offset = uvNorth.subtract(uvCenter);
            assert offset.length() > 0f : offset;
            Vector2f result = offset.normalize();
            return result;
        }
        /*
         * Compute UV coordinates for 0.01 radians south of the center of the
         * moon.
         */
        Vector3f south = sunAndStars.convertToWorld(-1f, longitude);
        Vector2f uvSouth = topMesh.directionUV(south);
        if (uvSouth != null) {
            Vector2f offset = uvCenter.subtract(uvSouth);
            assert offset.length() > 0f : offset;
            Vector2f result = offset.normalize();
            return result;
        }
        assert false : south;
        return null;
    }

    /**
     * Update astronomical objects, sky color, lighting, and stars.
     */
    private void updateAll() {
        sunAndStars.setHour(timeOfDay.getHour());

        Vector3f sunDirection = updateSun();
        /*
         * Daytime sky texture is phased in during the twilight periods before
         * sunrise and after sunset. Update the sky material's clear color
         * accordingly.
         */
        ColorRGBA clearColor = colorDay.clone();
        clearColor.a = FastMath.saturate(1f + sunDirection.y / limitOfTwilight);
        topMaterial.setClearColor(clearColor);

        Vector3f moonDirection = updateMoon();
        updateLighting(sunDirection, moonDirection);

        sunAndStars.orientStarDomes(northDome, southDome);
    }

    /**
     * Update background colors, cloud colors, haze color, sun color, lights,
     * and shadows.
     *
     * @param sunDirection
     *            world direction to the sun (length=1)
     * @param moonDirection
     *            world direction to the moon (length=1 or null)
     */
    private void updateLighting(Vector3f sunDirection, Vector3f moonDirection) {
        assert sunDirection != null;
        assert sunDirection.isUnitVector() : sunDirection;
        if (moonDirection != null) {
            assert moonDirection.isUnitVector() : moonDirection;
        }

        float sineSolarAltitude = sunDirection.y;
        float sineLunarAltitude;
        if (moonDirection != null) {
            sineLunarAltitude = moonDirection.y;
        } else {
            sineLunarAltitude = -1f;
        }
        updateObjectColors(sineSolarAltitude, sineLunarAltitude);
        /*
         * Determine the world direction to the main light source.
         */
        boolean moonUp = sineLunarAltitude >= 0f;
        boolean sunUp = sineSolarAltitude >= 0f;
        float moonWeight = getMoonIllumination();
        Vector3f mainDirection;
        if (sunUp) {
            mainDirection = sunDirection;
        } else if (moonUp && moonWeight > 0f) {
            assert moonDirection != null;
            mainDirection = moonDirection;
        } else {
            mainDirection = starlightDirection;
        }
        assert mainDirection.isUnitVector() : mainDirection;
        assert mainDirection.y >= 0f : mainDirection;
        /*
         * Determine the base color (applied to horizon haze, bottom dome, and
         * viewport backgrounds) using the sun's altitude: + sunlight when ssa
         * >= 0.25, + twilight when ssa = 0, + blend of moonlight and starlight
         * when ssa <= -0.04, with linearly interpolated transitions.
         */
        ColorRGBA baseColor = new ColorRGBA();
        if (sunUp) {
            float dayWeight = FastMath.saturate(sineSolarAltitude / 0.25f);
            baseColor.interpolateLocal(twilight, sunLight, dayWeight);
        } else {
            ColorRGBA blend = new ColorRGBA();
            if (moonUp && moonWeight > 0f) {
                blend.interpolateLocal(starLight, moonLight, moonWeight);
            } else {
                blend.set(starLight);
            }
            float nightWeight = FastMath.saturate(-sineSolarAltitude / 0.04f);
            baseColor.interpolateLocal(twilight, blend, nightWeight);
        }
        topMaterial.setHazeColor(baseColor);
        if (bottomMaterial != null) {
            bottomMaterial.setColor("Color", baseColor);
        }

        ColorRGBA cloudsColor = updateCloudsColor(baseColor, sunUp, moonUp);
        /*
         * Determine what fraction of the main light passes through the clouds.
         */
        float transmit;
        if (cloudModulationFlag && (sunUp || moonUp && moonWeight > 0f)) {
            /*
             * Modulate light intensity as clouds pass in front.
             */
            Vector3f intersection = intersectCloudDome(mainDirection);
            Vector2f texCoord = cloudsMesh.directionUV(intersection);
            transmit = cloudsMaterial.getTransmission(texCoord);

        } else {
            transmit = 1f;
        }
        /*
         * Determine the color and intensity of the main light.
         */
        ColorRGBA main = new ColorRGBA();
        if (sunUp) {
            /*
             * By day, the main light has the base color, modulated by clouds
             * and the cube root of the sine of the sun's altitude.
             */
            float magnitude = FastMath.abs(sineSolarAltitude);
            float exponent = FastMath.ONE_THIRD;
            float rootMagnitude = FastMath.pow(magnitude, exponent);
            float cubeRoot = FastMath.copysign(rootMagnitude, sineSolarAltitude);
            float sunFactor = transmit * cubeRoot;
            main = baseColor.mult(sunFactor);

        } else if (moonUp) {
            /*
             * By night, the main light is a blend of moonlight and starlight,
             * with the moon's portion modulated by clouds and the moon's phase.
             */
            float moonFactor = transmit * moonWeight;
            main.interpolateLocal(starLight, moonLight, moonFactor);

        } else {
            main = starLight.clone();
        }
        /*
         * The ambient light color is based on the clouds color; its intensity
         * is modulated by the "slack" left by strongest component of the main
         * light.
         */
        float slack = 1f - MyMath.max(main.r, main.g, main.b);
        assert slack >= 0f : slack;
        ColorRGBA ambient = cloudsColor.mult(slack);

        updater.update(ambient, baseColor, main, mainDirection);
    }

    /**
     * Update the moon's position and size.
     *
     * @return world direction to the moon (new unit vector) or null if the moon
     *         is hidden
     */
    private Vector3f updateMoon() {
        if (phase == null) {
            topMaterial.hideObject(moonIndex);
            return null;
        }
        /*
         * Compute the UV coordinates of the center of the moon.
         */
        float solarLongitude = sunAndStars.getSolarLongitude();
        float celestialLongitude = solarLongitude + phaseAngle;

        celestialLongitude = (celestialLongitude % FastMath.TWO_PI + FastMath.TWO_PI) % FastMath.TWO_PI;
        Vector3f worldDirection = sunAndStars.convertToWorld(0f, celestialLongitude);
        Vector2f uvCenter = topMesh.directionUV(worldDirection);

        if (uvCenter != null) {
            Vector2f rotation = lunarRotation(celestialLongitude, uvCenter);
            /*
             * Reveal the object and update its texture transform.
             */
            topMaterial.setObjectTransform(moonIndex, uvCenter, moonScale, rotation);
        } else {
            topMaterial.hideObject(moonIndex);
        }

        return worldDirection;
    }

    /**
     * Update the colors of the sun and moon based on their altitudes.
     *
     * @param sineSolarAltitude
     *            (&le;1, &ge:-1)
     * @param sineLunarAltitude
     *            (&le;1, &ge:-1)
     */
    private void updateObjectColors(float sineSolarAltitude, float sineLunarAltitude) {
        assert sineSolarAltitude <= 1f : sineSolarAltitude;
        assert sineSolarAltitude >= -1f : sineSolarAltitude;
        assert sineLunarAltitude <= 1f : sineLunarAltitude;
        assert sineLunarAltitude >= -1f : sineLunarAltitude;
        /*
         * Update the sun's color.
         */
        float green = FastMath.saturate(3f * sineSolarAltitude);
        float blue = FastMath.saturate(sineSolarAltitude - 0.1f);
        ColorRGBA sunColor = new ColorRGBA(1f, green, blue, Constants.alphaMax);
        topMaterial.setObjectColor(sunIndex, sunColor);
        topMaterial.setObjectGlow(sunIndex, sunColor);
        /*
         * Update the moon's color.
         */
        green = FastMath.saturate(2f * sineLunarAltitude + 0.6f);
        blue = FastMath.saturate(5f * sineLunarAltitude + 0.1f);
        ColorRGBA moonColor = new ColorRGBA(1f, green, blue, Constants.alphaMax);
        topMaterial.setObjectColor(moonIndex, moonColor);
    }

    /**
     * Update the sun's position and size.
     *
     * @return world direction to the sun (new unit vector)
     */
    private Vector3f updateSun() {
        /*
         * Compute the UV coordinates of the center of the sun.
         */
        Vector3f worldDirection = sunAndStars.getSunDirection();
        Vector2f uv = topMesh.directionUV(worldDirection);
        if (uv == null) {
            /*
             * The sun is below the horizon, so hide it.
             */
            topMaterial.hideObject(sunIndex);
        } else {
            topMaterial.setObjectTransform(sunIndex, uv, sunScale, null);
        }

        return worldDirection;
    }
}

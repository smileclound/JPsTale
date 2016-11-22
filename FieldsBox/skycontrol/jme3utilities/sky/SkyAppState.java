package jme3utilities.sky;

import java.util.logging.Level;
import java.util.logging.Logger;

import jme3utilities.MyAsset;
import jme3utilities.MySpatial;
import jme3utilities.TimeOfDay;
import jme3utilities.math.MyMath;

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
    /**
     * name for the bottom geometry
     */
    final private static String bottomName = "bottom";
    /**
     * name for the clouds-only geometry
     */
    final private static String cloudsName = "clouds";
    /**
     * name for the northern sky geometry
     */
    final private static String northName = "north";
    /**
     * name for the southern sky geometry
     */
    final private static String southName = "south";
    // *************************************************************************
    // fields
    final private boolean singleDome;
    /**
     * true to create a material and geometry for the hemisphere below the
     * horizon, false to leave this hemisphere to background color (if
     * starMotionFlag==false) or stars (if starMotionFlag==true): set by
     * constructor
     */
    final private boolean bottomDomeFlag;
    /**
     * true to counteract rotation of the controlled node, false to allow
     * rotation
     */
    private boolean stabilizeFlag = false;
    /**
     * true to simulate moving stars, false for fixed stars: set by constructor
     */
    final protected boolean starMotionFlag;
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
     * simulation time for cloud layer animations
     */
    private float cloudsAnimationTime = 0f;
    /**
     * rate of motion for cloud layer animations (1 &rarr; standard)
     */
    private float cloudsRelativeSpeed = 1f;
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
    final private static ColorRGBA colorDay =
            new ColorRGBA(0.4f, 0.6f, 1f, Constants.alphaMax);
    /**
     * light color and intensity for full moonlight: bluish gray
     */
    final private static ColorRGBA moonLight =
            new ColorRGBA(0.4f, 0.4f, 0.6f, Constants.alphaMax);
    /**
     * light color and intensity for moonless night: nearly black
     */
    final private static ColorRGBA starLight =
            new ColorRGBA(0.03f, 0.03f, 0.03f, Constants.alphaMax);
    /**
     * light color and intensity for full sunlight: yellowish white
     */
    final private static ColorRGBA sunLight =
            new ColorRGBA(0.8f, 0.8f, 0.75f, Constants.alphaMax);
    /**
     * color blended in around sunrise and sunset: ruddy orange
     */
    final private static ColorRGBA twilight =
            new ColorRGBA(0.6f, 0.3f, 0.15f, Constants.alphaMax);
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
    final private static Vector3f starlightDirection =
            new Vector3f(1f, 9f, 1f).normalizeLocal();
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
	final private TimeOfDay timeOfDay = new TimeOfDay(21.0f);
	
	public SkyAppState(boolean singleDome) {
		rootNode = new Node("sky node");
		rootNode.setQueueBucket(Bucket.Sky);
		rootNode.setShadowMode(ShadowMode.Off);
		
		this.singleDome = singleDome;
        if (singleDome) {
            starMotionFlag = false; // single dome implies non-moving stars
            bottomDomeFlag = false; // single dome implies exposed background
        } else {
            starMotionFlag = true; // allow stars to move
            bottomDomeFlag = true; // helpful in case the scene has a low horizon
        }
        
        cloudLayers = new CloudLayer[numCloudLayers];
	}
	
	@Override
	protected void initialize(Application app) {
		assetManager = app.getAssetManager();
		camera = app.getCamera();
		viewPort = app.getViewPort();
		
		createSkyDome();
		
        mainLight = new DirectionalLight();
        mainLight.setName("main");

        ambientLight = new AmbientLight();
        ambientLight.setName("ambient");

		updater.addViewPort(viewPort);
		updater.setAmbientLight(ambientLight);
		updater.setAmbientMultiplier(1f);
		updater.setMainLight(mainLight);
		updater.setMainMultiplier(1f);
		
	    timeOfDay.setRate(50f);// 时间流逝速度为现实的50倍
		getStateManager().attach(timeOfDay);
	}

	@Override
	protected void cleanup(Application app) {}

	@Override
	protected void onEnable() {
		((SimpleApplication)getApplication()).getRootNode().attachChild(rootNode);
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
        MySpatial.setWorldLocation(rootNode, cameraLocation);
        /*
         * Scale the sky node so that its furthest geometries are midway
         * between the near and far planes of the view frustum.
         */
        float far = camera.getFrustumFar();
        float near = camera.getFrustumNear();
        float radius = (near + far) / 2f;
        MySpatial.setWorldScale(rootNode, radius);

        if (stabilizeFlag) {
            /*
             * Counteract rotation of the controlled node.
             */
            MySpatial.setWorldOrientation(rootNode, Quaternion.IDENTITY);
        }
        
        updateAll();
	}
	
	private void createSkyDome() {
		/*
         * Create a SkyControl to animate the sky.
         */
        float cloudFlattening;
        if (singleDome) {
            cloudFlattening = 0f; // single dome implies clouds on top dome
        } else {
            cloudFlattening = 0.9f; // overhead clouds 10x closer than horizon
        }
        
		/*
         * Create and initialize the sky material for sun, moon, and haze.
         */
        int topObjects = 2; // a sun and a moon
        boolean cloudDomeFlag = cloudFlattening != 0f;
        int topCloudLayers = cloudDomeFlag ? 0 : numCloudLayers;
        topMaterial = new SkyMaterial(assetManager, topObjects, topCloudLayers);
        topMaterial.initialize();
        topMaterial.addHaze();
        if (!starMotionFlag) {
            topMaterial.addStars();
        }

        if (cloudDomeFlag) {
            /*
             * Create and initialize a separate sky material for clouds only.
             */
            int numObjects = 0;
            cloudsMaterial = new SkyMaterial(assetManager, numObjects, numCloudLayers);
            cloudsMaterial.initialize();
            cloudsMaterial.getAdditionalRenderState().setDepthWrite(false);
            cloudsMaterial.setClearColor(ColorRGBA.BlackNoAlpha);
        } else {
            cloudsMaterial = topMaterial;
        }

        /*
         * Initialize the cloud layers.
         */
        for (int layerIndex = 0; layerIndex < numCloudLayers; layerIndex++) {
            cloudLayers[layerIndex] = new CloudLayer(cloudsMaterial, layerIndex);
        }
        
        if (bottomDomeFlag) {
            bottomMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        } else {
            bottomMaterial = null;
        }

        createSpatials(cloudFlattening);
        if (starMotionFlag) {
            /**
             * star map asset path (or null for none), selected from a popup menu
             * 
             * "Textures/skies/star-maps" 4m
             * "Textures/skies/star-maps/16m"
             */
            setStarMaps("Textures/skies/star-maps");
        }
        
        setPhase(phase);
        
        /**
         * sun color map asset path, selected from a popup menu
         * "Textures/skies/t0neg0d/Sun_L.png"
         * "Textures/skies/suns/chaotic.png"
         * "Textures/skies/suns/disc.png"
         * "Textures/skies/suns/hazy-disc.png"
         * "Textures/skies/suns/rayed.png"
         */
        setSunStyle("Textures/skies/suns/hazy-disc.png");
        
        /**
         * 云层
         */
        setCloudYOffset(0f);
        setCloudiness(0.8f);
        setCloudModulation(false);
        setCloudRate(1f);
        
        sunAndStars.setObserverLatitude(0f);
        setLunarDiameter(0.031f);
        setSolarDiameter(0.031f);
        sunAndStars.setSolarLongitude(0f);
        setTopVerticalAngle(FastMath.HALF_PI);
	}
    // *************************************************************************
    // new methods exposed

    /**
     * Clear the star maps.
     */
    public void clearStarMaps() {
        if (!starMotionFlag) {
            topMaterial.removeStars();
            return;
        }
        /*
         * Don't remove the north/south domes because, then how would you insert
         * them back into the render queue ahead of the top dome?
         * Instead, make the north/south domes fully transparent.
         */
        Material clear = MyAsset.createInvisibleMaterial(assetManager);
        northDome.setMaterial(clear);
        southDome.setMaterial(clear);
    }

    /**
     * Access the indexed cloud layer.
     *
     * @param layerIndex (&lt;numCloudLayers, &ge;0)
     * @return pre-existing instance
     */
    public CloudLayer getCloudLayer(int layerIndex) {
        if (layerIndex < 0 || layerIndex >= numCloudLayers) {
            logger.log(Level.SEVERE, "index={0}", layerIndex);
            throw new IllegalArgumentException("index out of range");
        }

        CloudLayer layer = cloudLayers[layerIndex];

        assert layer != null;
        return layer;
    }

    /**
     * Compute the contribution of the moon to the nighttime illumination mix
     * using its phase, assuming it is above the horizon.
     *
     * @return fraction (&le;1, &ge;0) 1 &rarr; full moon, 0 &rarr; no
     * contribution
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
     * @param newAlpha desired opacity of the cloud layers (&le;1, &ge;0)
     */
    public void setCloudiness(float newAlpha) {
        for (int layer = 0; layer < numCloudLayers; layer++) {
            cloudLayers[layer].setOpacity(newAlpha);
        }
    }

    /**
     * Alter the speed or direction of cloud motion.
     *
     * @param newRate rate relative to the standard (may be negative)
     */
    public void setCloudRate(float newRate) {
        cloudsRelativeSpeed = newRate;
    }

    /**
     * Alter the vertical position of the clouds-only dome. When the scene's
     * horizon lies below the astronomical horizon, it may help to depress the
     * clouds-only dome.
     *
     * @param newYOffset desired vertical offset as a fraction of the dome
     * height (&lt;1, &ge;0 when flattening&gt;0; 0 when flattening=0)
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
            throw new IllegalArgumentException(
                    "offset should be between 0 and 1");
        }

        float deltaY = -newYOffset * cloudsOnlyDome.getLocalScale().y;
        cloudsOnlyDome.setLocalTranslation(0f, deltaY, 0f);
    }

    /**
     * Alter an object's color map texture.
     *
     * @param objectIndex which object (&ge;0)
     * @param newColorMap texture to apply (not null)
     */
    public void setObjectTexture(int objectIndex, Texture newColorMap) {
        topMaterial.addObject(objectIndex, newColorMap);
    }

    /**
     * Alter the stabilize flag.
     *
     * @param newState true to counteract rotation of the controlled node, false
     * to allow rotation
     */
    public void setStabilizeFlag(boolean newState) {
        stabilizeFlag = newState;
    }

    /**
     * Alter the star maps.
     *
     * @param assetPath if starMotion is true: path to an asset folder
     * containing "northern.png" and "southern.png" textures (not null)<br>
     * if starMotion is false: path to a star dome texture asset (not null)
     */
    final public void setStarMaps(String assetPath) {
        if (!starMotionFlag) {
            topMaterial.addStars(assetPath);
            return;
        }

        String northPath = String.format("%s/%sern.png", assetPath, northName);
        Material north =
                MyAsset.createUnshadedMaterial(assetManager, northPath);
        northDome.setMaterial(north);

        String southPath = String.format("%s/%sern.png", assetPath, southName);
        Material south =
                MyAsset.createUnshadedMaterial(assetManager, southPath);
        southDome.setMaterial(south);
    }

    /**
     * Alter the vertical angle of the top dome, which is Pi/2 by default. If
     * the terrain's horizon lies below the horizontal, increase this angle (to
     * values greater than Pi/2) to avoid clipping the sun and moon when they
     * are near the horizontal.
     *
     * @param newAngle desired angle from the zenith to the rim of the top dome
     * (in radians, &lt;1.785, &gt;0)
     */
    public void setTopVerticalAngle(float newAngle) {
        if (!(newAngle > 0f && newAngle < 1.785f)) {
            logger.log(Level.SEVERE, "angle={0}", newAngle);
            throw new IllegalArgumentException(
                    "angle should be between 0 and 1.785");
        }

        topMesh.setVerticalAngle(newAngle);
        topDome.setMesh(topMesh);
        if (bottomDomeFlag) {
            bottomMesh.setVerticalAngle(FastMath.PI - newAngle);
            bottomDome.setMesh(bottomMesh);
        }
    }
    // *************************************************************************
    // protected methods

    /**
     * Apply a modified version of the base color to each cloud layer.
     * <p>
     * The return value is used in calculating ambient light intensity.
     *
     * @param baseColor (not null, unaffected, alpha is ignored)
     * @param sunUp true if sun is above the horizon, otherwise false
     * @param moonUp true if moon is above the horizon, otherwise false
     * @return new instance (alpha is undefined)
     */
    protected ColorRGBA updateCloudsColor(ColorRGBA baseColor, boolean sunUp,
            boolean moonUp) {

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
     * Create and initialize the sky node and all its dome geometries.
     *
     * @param cloudFlattening the oblateness (ellipticity) of the dome with the
     * clouds (&ge; 0, &lt;1, 0 &rarr; no flattening (hemisphere), 1 &rarr;
     * maximum flattening
     */
    private void createSpatials(float cloudFlattening) {
        /*
         * Attach geometries to the sky node from the outside in
         * because they'll be rendered in that order.
         */
        if (starMotionFlag) {
            DomeMesh hemisphere = new DomeMesh(rimSamples, quadrantSamples);
            northDome = new Geometry(northName, hemisphere);
            rootNode.attachChild(northDome);

            southDome = new Geometry(southName, hemisphere);
            rootNode.attachChild(southDome);
        }

        topMesh = new DomeMesh(rimSamples, quadrantSamples);
        topDome = new Geometry("top", topMesh);
        rootNode.attachChild(topDome);
        topDome.setMaterial(topMaterial);

        if (bottomDomeFlag) {
            bottomMesh = new DomeMesh(rimSamples, 2);
            bottomDome = new Geometry(bottomName, bottomMesh);
            rootNode.attachChild(bottomDome);

            Quaternion upsideDown = new Quaternion();
            upsideDown.lookAt(Vector3f.UNIT_X, Vector3f.UNIT_Y.negate());
            bottomDome.setLocalRotation(upsideDown);
            bottomDome.setMaterial(bottomMaterial);
        }

        if (cloudsMaterial != topMaterial) {
            assert cloudFlattening > 0f : cloudFlattening;
            assert cloudFlattening < 1f : cloudFlattening;

            cloudsMesh = new DomeMesh(rimSamples, quadrantSamples);
            cloudsOnlyDome = new Geometry(cloudsName, cloudsMesh);
            rootNode.attachChild(cloudsOnlyDome);
            /*
             * Flatten the clouds-only dome in order to foreshorten clouds
             * near the horizon -- even if cloudYOffset=0.
             */
            float yScale = 1f - cloudFlattening;
            cloudsOnlyDome.setLocalScale(1f, yScale, 1f);
            cloudsOnlyDome.setMaterial(cloudsMaterial);
        } else {
            cloudsMesh = topMesh;
        }
    }
    
    // *************************************************************************
    // new methods exposed

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
     * Access the orientations of the sun and stars.
     *
     * @return pre-existing instance
     */
    public SunAndStars getSunAndStars() {
        return sunAndStars;
    }

    /**
     * Access the updater.
     *
     * @return pre-existing instance
     */
    public Updater getUpdater() {
        assert updater != null;
        return updater;
    }

    /**
     * Alter the cloud modulation flag.
     *
     * @param newValue true for clouds to modulate the main light, false for a
     * steady main light
     */
    public void setCloudModulation(boolean newValue) {
        cloudModulationFlag = newValue;
    }

    /**
     * Alter the angular diameter of the moon.
     *
     * @param newDiameter (in radians, &lt;Pi, &gt;0)
     */
    public void setLunarDiameter(float newDiameter) {
        if (!(newDiameter > 0f && newDiameter < FastMath.PI)) {
            logger.log(Level.SEVERE, "diameter={0}", newDiameter);
            throw new IllegalArgumentException(
                    "diameter should be between 0 and Pi");
        }

        moonScale = newDiameter * topMesh.uvScale / FastMath.HALF_PI;
    }

    /**
     * Alter the phase of the moon to a pre-set value.
     *
     * @param newPreset (or null to hide the moon)
     */
    final public void setPhase(LunarPhase newPreset) {
        if (newPreset == LunarPhase.CUSTOM) {
            setPhaseAngle(phaseAngle);
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
     * Customize the phase angle of the moon for off-screen rendering.
     *
     * @param newAngle (in radians, &le;2*Pi, &ge;0)
     */
    public void setPhaseAngle(float newAngle) {
        if (!(newAngle >= 0f && newAngle <= FastMath.TWO_PI)) {
            logger.log(Level.SEVERE, "angle={0}", newAngle);
            throw new IllegalArgumentException(
                    "angle should be between 0 and 2*Pi");
        }
        phase = LunarPhase.CUSTOM;
        phaseAngle = newAngle;
    }

    /**
     * Alter the angular diameter of the sun.
     *
     * @param newDiameter (in radians, &lt;Pi, &gt;0)
     */
    public void setSolarDiameter(float newDiameter) {
        if (!(newDiameter > 0f && newDiameter < FastMath.PI)) {
            logger.log(Level.SEVERE, "diameter={0}", newDiameter);
            throw new IllegalArgumentException(
                    "diameter should be between 0 and Pi");
        }

        sunScale = newDiameter * topMesh.uvScale
                / (Constants.discDiameter * FastMath.HALF_PI);
    }

    /**
     * Alter the sun's color map.
     *
     * @param assetPath to new color map (not null)
     */
    final public void setSunStyle(String assetPath) {
        assert assetPath != null;
        topMaterial.addObject(sunIndex, assetPath);
    }

    /**
     * Update the cloud layers. (Invoked once per frame.)
     *
     * @param elapsedTime since the previous update (in seconds, &ge;0)
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
     * @param mainDirection (unit vector with non-negative y-component)
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
         * Solve for the most positive root of a quadratic equation
         * in w = sqrt(x^2 + z^2).  Use double precision arithmetic.
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
     * @param longitude the moon's celestial longitude (in radians)
     * @param uvCenter texture coordinates of the moon's center (not null)
     * @return new unit vector with its x-component equal to the cosine of the
     * rotation angle and its y-component equal to the sine of the rotation
     * angle
     */
    private Vector2f lunarRotation(float longitude, Vector2f uvCenter) {
        assert uvCenter != null;
        /*
         * Compute UV coordinates for 0.01 radians north of the center
         * of the moon.
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
         * Compute UV coordinates for 0.01 radians south of the center
         * of the moon.
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
         * Daytime sky texture is phased in during the twilight periods
         * before sunrise and after sunset. Update the sky material's
         * clear color accordingly.
         */
        ColorRGBA clearColor = colorDay.clone();
        clearColor.a = FastMath.saturate(1f + sunDirection.y / limitOfTwilight);
        topMaterial.setClearColor(clearColor);

        Vector3f moonDirection = updateMoon();
        updateLighting(sunDirection, moonDirection);
        if (starMotionFlag) {
            sunAndStars.orientStarDomes(northDome, southDome);
        }
    }

    /**
     * Update background colors, cloud colors, haze color, sun color, lights,
     * and shadows.
     *
     * @param sunDirection world direction to the sun (length=1)
     * @param moonDirection world direction to the moon (length=1 or null)
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
         * viewport backgrounds) using the sun's altitude:
         *  + sunlight when ssa >= 0.25,
         *  + twilight when ssa = 0,
         *  + blend of moonlight and starlight when ssa <= -0.04,
         * with linearly interpolated transitions.
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
             * By day, the main light has the base color, modulated by
             * clouds and the cube root of the sine of the sun's altitude.
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
         * The ambient light color is based on the clouds color;
         * its intensity is modulated by the "slack" left by
         * strongest component of the main light.
         */
        float slack = 1f - MyMath.max(main.r, main.g, main.b);
        assert slack >= 0f : slack;
        ColorRGBA ambient = cloudsColor.mult(slack);
        /*
         * Compute the recommended shadow intensity as the fraction of
         * the total light which is directional.
         */
        float mainAmount = main.r + main.g + main.b;
        float ambientAmount = ambient.r + ambient.g + ambient.b;
        float totalAmount = mainAmount + ambientAmount;
        assert totalAmount > 0f : totalAmount;
        float shadowIntensity = FastMath.saturate(mainAmount / totalAmount);
        /*
         * Determine the recommended bloom intensity using the sun's altitude.
         */
        float bloomIntensity = 6f * sineSolarAltitude;
        bloomIntensity = FastMath.clamp(bloomIntensity, 0f, 1.7f);

        updater.update(ambient, baseColor, main, bloomIntensity, shadowIntensity, mainDirection);
    }
    
    /**
     * Update the moon's position and size.
     *
     * @return world direction to the moon (new unit vector) or null if the moon
     * is hidden
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
            topMaterial.setObjectTransform(moonIndex, uvCenter, moonScale,
                    rotation);
        } else {
            topMaterial.hideObject(moonIndex);
        }

        return worldDirection;
    }

    /**
     * Update the colors of the sun and moon based on their altitudes.
     *
     * @param sineSolarAltitude (&le;1, &ge:-1)
     * @param sineLunarAltitude (&le;1, &ge:-1)
     */
    private void updateObjectColors(float sineSolarAltitude,
            float sineLunarAltitude) {
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
        ColorRGBA moonColor =
                new ColorRGBA(1f, green, blue, Constants.alphaMax);
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

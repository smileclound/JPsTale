package org.pstale.test;

import static com.jme3.scene.plugins.smd.SMDTYPE.STAGE3D_COLLISION;
import static com.jme3.scene.plugins.smd.SMDTYPE.STAGE3D_VISUAL;

import java.nio.FloatBuffer;

import org.pstale.app.TestBase;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.MeshCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.ChaseCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.plugins.smd.SmdKey;
import com.jme3.scene.shape.Box;

/**
 * 测试地形的碰撞检测
 * 
 * @author yanmaoyuan
 *
 */
public class TestCollision extends TestBase {
    private final static String LEFT = "left";
    private final static String RIGHT = "right";
    private final static String FORWARD = "forward";
    private final static String BACKWARD = "backward";
    private final static String JUMP = "jump";
    private final static String DEBUG = "debug";// 是否显示bullet网格
    private final static String CHASE = "chase";// 在第三人称之间切换
    private final static float scale = 0.1f;

    /**
     * 地图模型
     */
    private Spatial visual;// 地图的可视部分
    private Mesh mesh;// 参加检测的网格

    /**
     * 玩家模型
     */
    private Spatial character;// 使用一个Cylinder来代替吧。

    /********
     * 射线检测
     */
    private Vector3f orgin = new Vector3f();// 射线检测的原点
    // 射线，初始化为从原点出发，方向垂直向下。
    private Ray ray = new Ray(orgin, new Vector3f(0, -1, 0));
    // 检测结果
    private CollisionResults results = new CollisionResults();

    /**
     * Bullet物理引擎对象
     */
    private BulletAppState bullet;
    private PhysicsRigidBody terrain;
    private CharacterControl player;

    /**
     * 玩家的一些物理参数
     */
    private float radius = 8f * scale;// 半径
    private float height = 30f * scale;// 身高
    private float halfH = height * 0.5f;// 这个变量代表胶囊体一半的高度，用于射线检测。
    private float mass = 10f * scale;// 质量
    private float jumpSpeed = 0f * scale;// 跳跃速度
    private float fallSpeed = 400f * scale;// 摔落速度
    private float gravity = 400f * scale;// 重力加速度

    /**
     * 运动参数
     */
    private float moveSpeed = 4f * scale;
    private boolean left = false, right = false, forward = false, backward = false;
    private Vector3f camDir = new Vector3f();
    private Vector3f camLeft = new Vector3f();
    private Vector3f walkDirection = new Vector3f();
    private Vector3f viewDirection = new Vector3f();

    /**
     * 摄像机
     */
    private ChaseCamera chaseCamera;// 第三人称摄像机

    @Override
    public void init() {
        /**
         * 初始化bullet引擎
         */
        bullet = new BulletAppState();
        bullet.setDebugEnabled(false);
        stateManager.attach(bullet);

        /**
         * 地形的可视部分
         */
        String model = "Field/forest/fore-2.smd";
        visual = (Node) assetManager.loadAsset(new SmdKey(model, STAGE3D_VISUAL));
        visual.scale(scale);
        rootNode.attachChild(visual);

        /**
         * 地形的碰撞形状
         */
        mesh = (Mesh) assetManager.loadAsset(new SmdKey(model, STAGE3D_COLLISION));
        scale(mesh);
        MeshCollisionShape shape = new MeshCollisionShape(mesh);
        terrain = new PhysicsRigidBody(shape, 0);
        bullet.getPhysicsSpace().add(terrain);

        /**
         * 玩家的可视部分
         */
        Box box = new Box(radius, height / 2 + radius, radius);
        character = new Geometry("player", box);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Yellow);
        character.setMaterial(mat);
        rootNode.attachChild(character);

        /**
         * 玩家的碰撞网格
         */
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(radius, height, 1);
        player = new CharacterControl(capsuleShape, mass);
        player.setJumpSpeed(jumpSpeed);
        player.setFallSpeed(fallSpeed);
        player.setGravity(gravity);
        bullet.getPhysicsSpace().add(player);

        setPlayerLocation();
        character.addControl(player);

        // 初始化输入监听器
        inputManager.addMapping(LEFT, new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping(RIGHT, new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping(FORWARD, new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping(BACKWARD, new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping(JUMP, new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping(DEBUG, new KeyTrigger(KeyInput.KEY_F1));
        inputManager.addMapping(CHASE, new KeyTrigger(KeyInput.KEY_F2));
        inputManager.addListener(myListener, LEFT, RIGHT, FORWARD, BACKWARD, JUMP, DEBUG, CHASE);

        // 第三人称摄像机
        chaseCamera = new ChaseCamera(cam, character, inputManager);
        chaseCamera.setInvertVerticalAxis(true);

        // 默认使用第一人称摄像机
        chaseCamera.setEnabled(false);
        flyCam.setEnabled(true);
        inputManager.setCursorVisible(false);
    }

    /**
     * 按键事件监听器
     */
    private ActionListener myListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals(LEFT)) {
                left = isPressed;
            } else if (name.equals(RIGHT)) {
                right = isPressed;
            } else if (name.equals(FORWARD)) {
                forward = isPressed;
            } else if (name.equals(BACKWARD)) {
                backward = isPressed;
            } else if (name.equals(JUMP)) {
                if (player != null)
                    player.jump();
            } else if (name.equals(DEBUG) && isPressed) {
                if (bullet.isDebugEnabled()) {
                    bullet.setDebugEnabled(false);
                    rootNode.attachChild(visual);
                } else {
                    bullet.setDebugEnabled(true);
                    rootNode.detachChild(visual);
                }
            } else if (name.equals(CHASE) && isPressed) {
                if (chaseCamera.isEnabled()) {
                    chaseCamera.setEnabled(false);
                    flyCam.setEnabled(true);
                    inputManager.setCursorVisible(false);
                } else {
                    chaseCamera.setEnabled(true);
                    flyCam.setEnabled(false);
                    inputManager.setCursorVisible(true);
                }
            }
        }
    };

    /**
     * 为了防止玩家掉到地图外，因此用一个变量记录玩家上一次所在的位置。 若玩家掉到地图外了，就把他拉回来。
     */
    Vector3f lastOnGroundLoc = new Vector3f(0, 0, 0);

    public void simpleUpdate(float tpf) {

        // 人物行走
        camDir.set(cam.getDirection()).multLocal(0.6f);
        camLeft.set(cam.getLeft()).multLocal(0.4f);
        walkDirection.set(0, 0, 0);
        if (left) {
            walkDirection.addLocal(camLeft);
        }
        if (right) {
            walkDirection.addLocal(camLeft.negate());
        }
        if (forward) {
            walkDirection.addLocal(camDir);
        }
        if (backward) {
            walkDirection.addLocal(camDir.negate());
        }

        if (walkDirection.length() != 0) {
            viewDirection.set(walkDirection);
        } else {
            viewDirection.set(cam.getDirection());
        }
        viewDirection.y = 0;
        viewDirection.normalizeLocal();

        walkDirection.y = 0;
        walkDirection.normalizeLocal().multLocal(moveSpeed);

        player.setWalkDirection(walkDirection);
        player.setViewDirection(viewDirection);

        /**
         * 检测玩家有无掉出地图
         */
        // 设置原点
        orgin.set(player.getPhysicsLocation());
        // 我们检测的是玩家脚下的点，所以Y坐标要减去胶囊体一半的高度。
        orgin.y -= halfH;

        CollisionResult result = getCollisionResult(orgin);
        if (result == null) {
            player.setPhysicsLocation(lastOnGroundLoc);
        } else {
            lastOnGroundLoc.set(player.getPhysicsLocation());
        }

        // 移动摄像机，使摄像机跟随胶囊体。
        if (!chaseCamera.isEnabled()) {
            orgin.set(lastOnGroundLoc);
            orgin.y += halfH;
            cam.setLocation(orgin);
        }
    }

    /**
     * 设置原点，发出一条垂直向下的射线与地形的网格进行碰撞检测。
     * 
     * @param orgin
     *            原点
     * @return
     */
    private CollisionResult getCollisionResult(Vector3f orgin) {
        ray.setOrigin(orgin);
        results.clear();
        mesh.collideWith(ray, Matrix4f.IDENTITY, mesh.getBound(), results);
        return results.getClosestCollision();
    }

    /**
     * 缩放网格
     */
    private void scale(Mesh mesh) {
        // 将网格缩小
        FloatBuffer fb = (FloatBuffer) mesh.getBuffer(Type.Position).getData();
        for (int i = 0; i < fb.limit(); i++) {
            fb.put(i, fb.get(i) * scale);
        }
        mesh.updateBound();
    }

    /**
     * 计算玩家的坐标
     * 
     * @param center
     */
    private void setPlayerLocation() {
        orgin.set(mesh.getBound().getCenter());
        orgin.y = 1000 / scale;
        CollisionResult result = getCollisionResult(orgin);
        if (result != null) {
            orgin.set(result.getContactPoint());
            // 玩家碰撞形状是一个胶囊体，坐标在胶囊体的中心。为了让胶囊体出现在地表，需要将坐标上移一半个高度。
            orgin.y += halfH;
            player.setPhysicsLocation(orgin);
            lastOnGroundLoc.set(orgin);
        }
    }

    public static void main(String[] args) {
        TestCollision app = new TestCollision();
        app.start();
    }

}

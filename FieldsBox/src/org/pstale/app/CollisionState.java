package org.pstale.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.MeshCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Matrix4f;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Mesh;

import static org.pstale.app.SubAppState.scale;

/**
 * 碰撞检测模块
 * 
 * @author yanmaoyuan
 * 
 */
public class CollisionState extends BaseAppState {

	static Logger log = Logger.getLogger(CollisionState.class);
	
	private final static String LEFT = "left";
	private final static String RIGHT = "right";
	private final static String FORWARD = "forward";
	private final static String BACKWARD = "backward";
	private final static String JUMP = "jump";

	/**
	 * 射线检测
	 */
	List<Mesh> meshes;// 参加检测的网格
	Vector3f orgin = new Vector3f();// 射线检测的原点
	// 射线，初始化为从原点出发，方向垂直向下。
	Ray ray = new Ray(orgin, new Vector3f(0, -1, 0));
	// 检测结果
	CollisionResults results = new CollisionResults();
	
	// 所有地形的网格
	List<PhysicsRigidBody> rigids;
	BulletAppState bullet;
	CharacterControl player;
	// 玩家的一些物理参数
	float radius = 8f * scale;// 半径
	float height = 30f * scale;// 身高
	float halfH = height * 0.5f;// 这个变量代表胶囊体一半的高度，用于射线检测。
	float mass = 10f * scale;// 质量
	float jumpSpeed = 140f * scale;// 跳跃速度
	float fallSpeed = 400f * scale;// 摔落速度
	float gravity = 400f * scale;// 重力加速度
	float moveSpeed = 4f * scale;
	
	Camera cam;
	
	/**
	 * 是否启动碰撞检测。
	 */
	boolean enable = true;

	// 运动逻辑
	private boolean left = false, right = false, forward = false, backward = false;
	private Vector3f camDir = new Vector3f();
	private Vector3f camLeft = new Vector3f();
	private Vector3f walkDirection = new Vector3f();

	public CollisionState(int fieldCnt) {
		meshes = new ArrayList<Mesh>(fieldCnt);
		rigids = new ArrayList<PhysicsRigidBody>(fieldCnt);
		bullet = new BulletAppState();
		bullet.setDebugEnabled(false);
	}

	@Override
	protected void initialize(Application app) {
		cam = app.getCamera();
		
		InputManager inputManager = app.getInputManager();

		inputManager.addMapping(LEFT, new KeyTrigger(KeyInput.KEY_A));
		inputManager.addMapping(RIGHT, new KeyTrigger(KeyInput.KEY_D));
		inputManager.addMapping(FORWARD, new KeyTrigger(KeyInput.KEY_W));
		inputManager.addMapping(BACKWARD, new KeyTrigger(KeyInput.KEY_S));
		inputManager.addMapping(JUMP, new KeyTrigger(KeyInput.KEY_SPACE));
		inputManager.addListener(myListener, LEFT, RIGHT, FORWARD, BACKWARD, JUMP);
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
			}
		}
	};

	@Override
	protected void cleanup(Application app) {
	}

	@Override
	protected void onEnable() {
		getStateManager().attach(bullet);
	}

	@Override
	protected void onDisable() {
		getStateManager().detach(bullet);
	}

	/**
	 * 为了防止玩家掉到地图外，因此用一个变量记录玩家上一次所在的位置。
	 * 若玩家掉到地图外了，就把他拉回来。
	 */
	Vector3f lastOnGroundLoc = new Vector3f(0, 0, 0);
	public void update(float tpf) {
		if (player == null)
			return;
		
		if (!enable) {// 不适用碰撞检测的时候，让play的位置跟随摄像机。
			
			// 设置碰撞检测的原点
			orgin.set(cam.getLocation());
			orgin.y = 1000 / scale;
			
			CollisionResult result = getCollisionResult(orgin);
			if (result != null) {
				orgin.set(result.getContactPoint());
				// 玩家碰撞形状是一个胶囊体，坐标在胶囊体的中心。为了让胶囊体出现在地表，需要将坐标上移一半个高度。
				orgin.y += halfH;
				player.setPhysicsLocation(orgin);
				lastOnGroundLoc.set(orgin);
			}
			
		} else {
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
			walkDirection.y = 0;
			walkDirection.normalizeLocal().multLocal(moveSpeed);
			player.setWalkDirection(walkDirection);
			
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
				//player.setWalkDirection(Vector3f.ZERO);
			} else {
				lastOnGroundLoc.set(player.getPhysicsLocation());
			}
			
			// 移动摄像机，使摄像机跟随胶囊体。
			orgin.set(lastOnGroundLoc);
			orgin.y += halfH;
			cam.setLocation(orgin);
		}
	}
	
	/**
	 * 设置原点，发出一条垂直向下的射线与地形的网格进行碰撞检测。
	 * @param orgin 原点
	 * @return
	 */
	CollisionResult getCollisionResult(Vector3f orgin) {
		ray.setOrigin(orgin);
		results.clear();
		// TODO 这里参加检测的面太多了，可以根据坐标来拣选网格。
		for(int i=0; i<meshes.size(); i++) {
			Mesh mesh = meshes.get(i);
			mesh.collideWith(ray, Matrix4f.IDENTITY, mesh.getBound(), results);
		}
		return results.getClosestCollision();
	}

	/**
	 * 获得玩家的坐标
	 * @return
	 */
	public Vector3f getPlayerLocation() {
		return orgin;
	}
	
	public void addMesh(Mesh mesh) {
		if (meshes.contains(mesh))
			return;

		if (getStateManager().hasState(bullet)) {
			MeshCollisionShape shape = new MeshCollisionShape(mesh);

			PhysicsRigidBody rigid = new PhysicsRigidBody(shape, 0);
			bullet.getPhysicsSpace().add(rigid);

			meshes.add(mesh);
			rigids.add(rigid);

			if (player == null) {
				CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(radius, height, 1);
				player = new CharacterControl(capsuleShape, mass);
				player.setJumpSpeed(jumpSpeed);
				player.setFallSpeed(fallSpeed);
				player.setGravity(gravity);
				player.setPhysicsLocation(cam.getLocation());
				bullet.getPhysicsSpace().add(player);
			}
		}
	}

	public void setPlayerLocation(Vector3f center) {
		orgin.set(center);
		orgin.y = 1000 / scale;
		
		CollisionResult result = getCollisionResult(orgin);
		if (result != null) {
			orgin.set(result.getContactPoint());
			// 玩家碰撞形状是一个胶囊体，坐标在胶囊体的中心。为了让胶囊体出现在地表，需要将坐标上移一半个高度。
			orgin.y += halfH;
			if (player != null) {
				player.setPhysicsLocation(orgin);
				lastOnGroundLoc.set(orgin);
			}
		}
		cam.setLocation(orgin);
	}

	public void toggle(Boolean enable) {
		this.enable = enable;
	}

	public void debug(Boolean debug) {
		bullet.setDebugEnabled(debug);
	}

}

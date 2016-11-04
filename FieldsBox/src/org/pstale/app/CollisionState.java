package org.pstale.app;

import java.util.ArrayList;
import java.util.List;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.MeshCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.input.CameraInput;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Mesh;

/**
 * 碰撞检测模块
 * 
 * @author yanmaoyuan
 * 
 */
public class CollisionState extends BaseAppState {

	private final static String LEFT = "left";
	private final static String RIGHT = "right";
	private final static String FORWARD = "forward";
	private final static String BACKWARD = "backward";
	private final static String JUMP = "jump";

	List<Mesh> meshes;
	List<PhysicsRigidBody> rigids;

	BulletAppState bullet;
	CharacterControl player;

	Camera cam;
	
	boolean enable = true;

	// 运动逻辑
	private boolean left = false, right = false, forward = false,
			backward = false, trigger = false, bomb = false;
	private Vector3f camLoc = new Vector3f();
	private Vector3f camDir = new Vector3f();
	private Vector3f camLeft = new Vector3f();
	private Vector3f walkDirection = new Vector3f();
	private float moveSpeed = 2f;

	public CollisionState(int fieldCnt) {
		meshes = new ArrayList<Mesh>(fieldCnt);
		rigids = new ArrayList<PhysicsRigidBody>(fieldCnt);
		bullet = new BulletAppState();
		bullet.setDebugEnabled(true);
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

	public void update(float tpf) {
		if (!enable)
			return;
		
		if (player == null)
			return;
		
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
		
		cam.setLocation(player.getPhysicsLocation());
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
				CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(
						4f, 15f, 1);
				player = new CharacterControl(capsuleShape, 5);
				player.setJumpSpeed(70);
				player.setFallSpeed(200);
				player.setGravity(200);
				player.setPhysicsLocation(cam.getLocation());

				bullet.getPhysicsSpace().add(player);
			}
		}
	}

	public void setPlayerLocation(Vector3f center) {
		if (player != null) {
			player.setPhysicsLocation(center);
		}

	}

	public void toggle(Boolean enable) {
		this.enable = enable;
	}

	public void debug(Boolean debug) {
		bullet.setDebugEnabled(debug);
	}

}

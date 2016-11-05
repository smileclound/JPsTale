package org.pstale.app;

import java.util.concurrent.Callable;

import org.pstale.fields.Field;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.texture.Texture;
import com.simsilica.lemur.Action;
import com.simsilica.lemur.ActionButton;
import com.simsilica.lemur.Axis;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Checkbox;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.DefaultRangedValueModel;
import com.simsilica.lemur.FillMode;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.LayerComparator;
import com.simsilica.lemur.ListBox;
import com.simsilica.lemur.Panel;
import com.simsilica.lemur.Slider;
import com.simsilica.lemur.component.QuadBackgroundComponent;
import com.simsilica.lemur.component.SpringGridLayout;
import com.simsilica.lemur.core.VersionedList;
import com.simsilica.lemur.core.VersionedReference;
import com.simsilica.lemur.event.CursorEventControl;
import com.simsilica.lemur.event.DragHandler;
import com.simsilica.lemur.style.ElementId;

/**
 * 主界面
 * @author yanmaoyuan
 *
 */
public class HudState extends BaseAppState {

	private ListBox<String> listBox;
	private VersionedList<String> fieldList = new VersionedList<String>();

    private VersionedReference<Boolean> showAxisRef;
    private VersionedReference<Boolean> showMeshRef;
    private VersionedReference<Boolean> collisionRef;
    private VersionedReference<Double> speedRef;
    
	private float width;// 屏幕宽度
	private float height;// 屏幕高度
	
	private Node guiNode;
	
	public HudState() {
		guiNode = new Node("LemurGUI");
	}
	@Override
	protected void initialize(Application app) {
		// 记录屏幕高宽
		Camera cam = app.getCamera();
		width = cam.getWidth();
		height = cam.getHeight();
		
		/**
		 * 地区列表窗口
		 */
		createFieldListBox();
		/**
		 * 配置面板
		 */
		createOptionPanel();
		/**
		 * 小地图
		 */
		createMiniMap();
	}

	@Override
	protected void cleanup(Application app) {}
	
	@Override
	protected void onEnable() {
		SimpleApplication simpleApp = (SimpleApplication)getApplication();
		simpleApp.getGuiNode().attachChild(guiNode);
	}

	@Override
	protected void onDisable() {
		guiNode.removeFromParent();
	}
	
	public void update(float tpf) {
		if( showAxisRef.update() ) {
			AxisAppState axis = getStateManager().getState(AxisAppState.class);
			if (axis != null) {
				axis.setEnabled(showAxisRef.get());
			}
        }
        if( showMeshRef.update() ) {
        	CollisionState collision = getStateManager().getState(CollisionState.class);
        	if (collision != null) {
        		collision.debug( showMeshRef.get() );
        	}
        }
        if( collisionRef.update() ) {
        	CollisionState collision = getStateManager().getState(CollisionState.class);
        	if (collision != null) {
        		collision.toggle( collisionRef.get() );
        	}
        }
        if( speedRef.update() ) {
        	double value = speedRef.get();
        	SimpleApplication app = (SimpleApplication) getApplication();
        	if (app.getFlyByCamera() != null) {
        		app.getFlyByCamera().setMoveSpeed((float)value);
        	}
        }
	}
	
	/**
	 * 将小地图的标题和内容做成变量，这样就可以通过
	 * {@code public void setMiniMap(Texture titleRes, Texture mapRes)}
	 * 方法来修改小地图了。
	 */
	private Container title;
	private Container map;
	/**
	 * 创建小地图面板
	 */
	private void createMiniMap() {
		Container window = new Container("glass");
		guiNode.attachChild(window);
		
		// 使其可以拖拽
		CursorEventControl.addListenersToSpatial(window, new DragHandler());

		// 标题
		// 地图的Title
		
		title = new Container("glass");
		title.setPreferredSize(new Vector3f(160, 24, 1));
		window.addChild(title);
		
		// 地图的图片
		map = new Container("glass");
		map.setPreferredSize(new Vector3f(160, 160, 1));
		window.addChild(map);
		
		// 限制窗口的最小宽度
        Vector3f hudSize = new Vector3f(160,160,0);
        hudSize.maxLocal(window.getPreferredSize());
        window.setPreferredSize( hudSize );
        
        // 将窗口添加到屏幕右下角。
     	window.setLocalTranslation(width - 20 - hudSize.x, hudSize.y + 20, 0);
	}
	
	/**
	 * 设置小地图
	 * @param titleRes
	 * @param mapRes
	 */
	public void setMiniMap(Texture titleRes, Texture mapRes) {
		if (titleRes != null) {
			title.setBackground( new QuadBackgroundComponent(titleRes,5,5, 0.02f, false) );
		} else {
			title.setBackground( new QuadBackgroundComponent(new ColorRGBA(0.1f,0.1f,0.1f,0.5f),5,5, 0.02f, false) );
		}
		
		if (mapRes != null) {
			map.setBackground( new QuadBackgroundComponent(mapRes,5,5, 0.02f, false) );
		} else {
			map.setBackground( new QuadBackgroundComponent(new ColorRGBA(0.1f,0.1f,0.1f,0.5f),5,5, 0.02f, false) );
		}
	}
	/**
	 * 创建区域列表
	 */
	private void createFieldListBox() {
		/**
		 * 地区列表窗口
		 */
		Container window = new Container("glass");
		// 标题
		window.addChild(new Label("所有区域列表", new ElementId("title"), "glass"));
		// 初始化列表数据
		final DataState dataState = getStateManager().getState(DataState.class);
		if (dataState != null) {
			Field[] fields = dataState.getFields();
			for (int i = 0; i < fields.length; i++) {
				fieldList.add(fields[i].getId() + ":" + fields[i].getTitle());
			}
		}

		// 创建一个ListBox控件，并添加到窗口中
		listBox = new ListBox<String>(fieldList, "glass");
		listBox.setVisibleItems(12);
		window.addChild(listBox);

		// 载入按钮
		final Action load = new Action("载入地图") {
			@Override
			public void execute(Button b) {
				Integer selected = listBox.getSelectionModel().getSelection();
				if (selected != null && selected < fieldList.size()) {
					if (dataState != null) {
						
						// 获得被选中的区域
						Field[] fields = dataState.getFields();
						final Field field = fields[selected];
						
						// 载入
						getApplication().enqueue(new Callable<Void>() {
							public Void call() {
								LoaderAppState state = getStateManager().getState(LoaderAppState.class);
								if (state != null) {
									state.loadModel(field);
								}
								return null;
							}
						});
					}
				}
			}
		};
		
		// 编辑地图
		final Action edit = new Action("Modify") {
			@Override
			public void execute(Button b) {
				// not support yet
			}
		};

		// 创建按钮面板
		Container buttons = new Container(new SpringGridLayout(Axis.X, Axis.Y, FillMode.Even, FillMode.Even));
		window.addChild(buttons);
		buttons.addChild(new ActionButton(load, "glass"));
		buttons.addChild(new ActionButton(edit, "glass"));

		// 限制窗口的最小宽度
        Vector3f hudSize = new Vector3f(160,0,0);
        hudSize.maxLocal(window.getPreferredSize());
        window.setPreferredSize( hudSize );
        
        // 将窗口添加到屏幕右上角。
     	window.setLocalTranslation(width - 20 - hudSize.x, height - 20, 0);
     	
		// 使其可以拖拽
		CursorEventControl.addListenersToSpatial(window, new DragHandler());
		
		guiNode.attachChild(window);
	}

	/**
	 * Create a top panel for some stats toggles.
	 */
	private void createOptionPanel() {
		Container window = new Container("glass");
		window.setLocalTranslation( 5, height - 20, 0 );
		guiNode.attachChild(window);
		
		// 使其可以拖拽
		CursorEventControl.addListenersToSpatial(window, new DragHandler());
				
		window.setBackground(new QuadBackgroundComponent(new ColorRGBA(0,0f,0f,0.5f),5,5, 0.02f, false));
        window.addChild( new Label( "Settings", new ElementId("header"), "glass" ) );
        window.addChild( new Panel( 2, 2, ColorRGBA.White, "glass" ) ).setUserData( LayerComparator.LAYER, 2 );

        // Adding components returns the component so we can set other things
        // if we want.
        Checkbox temp = window.addChild( new Checkbox( "显示坐标系" ) );
        temp.setChecked(true);
        showAxisRef = temp.getModel().createReference();
        
        temp = window.addChild( new Checkbox( "显示网格线" ) );
        temp.setChecked(true);
        showMeshRef = temp.getModel().createReference();
        
        temp = window.addChild( new Checkbox( "Collision" ) );
        temp.setChecked(true);
        collisionRef = temp.getModel().createReference();
        
        window.addChild( new Label( "摄像机速度:" ) );
        DefaultRangedValueModel model = new DefaultRangedValueModel(0, 1000, 50);
        final Slider redSlider = new Slider(model, "glass");
        redSlider.setBackground(new QuadBackgroundComponent(new ColorRGBA(0.5f,0.1f,0.1f,0.5f),5,5, 0.02f, false));
        speedRef = window.addChild( redSlider ).getModel().createReference();
        
        // 限制窗口的最小宽度
        Vector3f hudSize = new Vector3f(200,0,0);
        hudSize.maxLocal(window.getPreferredSize());
        window.setPreferredSize( hudSize );
	}
}

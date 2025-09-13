package com.r4l;


import com.r4l.simple_2d_engine.ecs.components.HitboxComponent;
import com.r4l.simple_2d_engine.ecs.components.RenderComponent;
import com.r4l.simple_2d_engine.gui.Screen;
import com.r4l.simple_2d_engine.gui.entities.Button;
import com.r4l.simple_2d_engine.gui.entities.Text;

@SuppressWarnings("serial")
public class TestScreen extends Screen{
	
	private Button b;
	
	private Text t;
	
	public TestScreen() {
		super();
	}
	/*
	 * Executes ones before the Screen is Created;
	 */
	@Override
	public void Init() {
		super.Init();
		b = new Button("0", "Text", 10, 10, 100, 100, "/assets/ss.png", "/assets/ss1.png", "/assets/ss2.png", 2);
		b.GetComponent(RenderComponent.class).setOpacity(0.5f);
		t = new Text("1", "Text", 300, 300, 7, 3);
		t.setOpacity(0.5f);
		ecs.addEntity(b);
		ecs.addEntity(t);
	}
	
	/*
	 * Executes ones when the Screen is Opened;
	 */
	@Override
	public void onOpen() {
		super.onOpen();
	}
	
	/*
	 * Executes every frame, afrter onOpen();
	 */
	@Override
	public void Update() {
		super.Update();
	}
	
	
	@Override
	public void onPress(HitboxComponent hitbox) {
		super.onPress(hitbox);
	}
	@Override
	public void onRelease(HitboxComponent hitbox) {
		super.onRelease(hitbox);
	}
	@Override
	public void onHover(HitboxComponent hitbox) {
		super.onHover(hitbox);
	}
	@Override
	public void onUnhover(HitboxComponent hitbox) {
		super.onUnhover(hitbox);
	}
}

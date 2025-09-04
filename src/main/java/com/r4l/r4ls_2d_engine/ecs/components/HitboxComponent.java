package com.r4l.r4ls_2d_engine.ecs.components;

import com.r4l.r4ls_2d_engine.ecs.Component;
import com.r4l.r4ls_2d_engine.util.HitboxType;
import com.r4l.r4ls_2d_engine.util.Reference;

public class HitboxComponent extends Component{

	private int width;
	
	private int height;
	
	private HitboxType hitboxType;
	
	public HitboxComponent(String name, int width, int height, HitboxType hitboxType) {
		super(name);
		setWidth(width);
		setHeight(height);
		setHitboxType(hitboxType);
	}
	
	public HitboxComponent(String name, String group, int width, int height, HitboxType hitboxType) {
		super(name, group);
		setWidth(width);
		setHeight(height);
		setHitboxType(hitboxType);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width * Reference.SCALE;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height * Reference.SCALE;
	}

	public HitboxType getHitboxType() {
		return hitboxType;
	}

	public void setHitboxType(HitboxType hitboxType) {
		this.hitboxType = hitboxType;
	}
}

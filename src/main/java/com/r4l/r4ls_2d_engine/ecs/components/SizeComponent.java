package com.r4l.r4ls_2d_engine.ecs.components;

import com.r4l.r4ls_2d_engine.ecs.Component;
import com.r4l.r4ls_2d_engine.util.Reference;

public class SizeComponent extends Component{

	private int width;
	
	private int height;
	
	public SizeComponent(int width, int height) {
		super("Size");
		setWidth(width);
		setHeight(height);
	}
	
	public SizeComponent(String group, int width, int height) {
		super("Size", group);
		setWidth(width);
		setHeight(height);
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

}

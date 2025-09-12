package com.r4l.simple_2d_engine.ecs.components;


import com.r4l.simple_2d_engine.ecs.Component;
import com.r4l.simple_2d_engine.util.Reference;

public class SizeComponent extends Component{

	private int width;
	
	private int height;
	
	public SizeComponent(int width, int height) {
		super("DefaultSize");
		setWidth(width);
		setHeight(height);
	}
	
	public SizeComponent(String name, int width, int height) {
		super(name);
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

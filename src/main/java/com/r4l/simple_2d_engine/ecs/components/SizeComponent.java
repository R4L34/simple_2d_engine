package com.r4l.simple_2d_engine.ecs.components;


import com.r4l.simple_2d_engine.ecs.Component;
import com.r4l.simple_2d_engine.util.Reference;

public class SizeComponent extends Component{

	private double width;
	
	private double height;
	
	public SizeComponent(double width, double height) {
		super("DefaultSize");
		setWidth(width);
		setHeight(height);
	}
	
	public SizeComponent(String name, double width, double height) {
		super(name);
		setWidth(width);
		setHeight(height);
	}

	public double getScaledWidth() {
		return width;
	}
	
	public double getWidth() {
		return width / Reference.SCALE;
	}
	
	public double getHeight() {
		return height / Reference.SCALE;
	}

	public void setWidth(double width) {
		this.width = width * Reference.SCALE;
	}

	public double getScaledHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height * Reference.SCALE;
	}

}

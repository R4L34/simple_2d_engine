package com.r4l.simple_2d_engine.ecs.components;

import com.r4l.simple_2d_engine.ecs.Component;
import com.r4l.simple_2d_engine.util.Reference;

public class PositionComponent extends Component{

	private double x;
	
	private double y;
	
	public PositionComponent(double x, double y) {
		super("DefaultPosition");
		setX(x);
		setY(y);
	}
	
	public PositionComponent(String name, double x, double y) {
		super(name);
		setX(x);
		setY(y);
	}


	public double getScaledX() {
		return x;
	}
	
	public double getX() {
		return x / Reference.SCALE;
	}
	
	public double getY() {
		return y / Reference.SCALE;
	}

	public void setX(double x) {
		this.x = x * Reference.SCALE;
	}

	public double getScaledY() {
		return y;
	}

	public void setY(double y) {
		this.y = y * Reference.SCALE;
	}

}

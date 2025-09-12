package com.r4l.simple_2d_engine.ecs.components;

import com.r4l.simple_2d_engine.ecs.Component;
import com.r4l.simple_2d_engine.util.Reference;

public class PositionComponent extends Component{

	private int x;
	
	private int y;
	
	public PositionComponent(int x, int y) {
		super("DefaultPosition");
		setX(x);
		setY(y);
	}
	
	public PositionComponent(String name, int x, int y) {
		super(name);
		setX(x);
		setY(y);
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x * Reference.SCALE;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y * Reference.SCALE;
	}

}

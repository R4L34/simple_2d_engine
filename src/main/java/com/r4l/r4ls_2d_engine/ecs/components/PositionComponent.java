package com.r4l.r4ls_2d_engine.ecs.components;

import com.r4l.r4ls_2d_engine.ecs.Component;
import com.r4l.r4ls_2d_engine.util.Reference;

public class PositionComponent extends Component{

	private int x;
	
	private int y;
	
	public PositionComponent(int x, int y) {
		super("Position");
		setX(x);
		setY(y);
	}
	
	public PositionComponent(int x, int y, String group) {
		super("Position", group);
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

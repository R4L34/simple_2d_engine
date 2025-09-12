package com.r4l.simple_2d_engine.util;

import java.awt.Graphics2D;

public interface Renderable {
	
public int getZOrder();
	
	public void setZOrder(int zOrder);
	
	public void render(Graphics2D g2);

}

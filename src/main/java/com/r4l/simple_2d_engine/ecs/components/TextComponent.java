package com.r4l.simple_2d_engine.ecs.components;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.r4l.simple_2d_engine.ecs.Component;
import com.r4l.simple_2d_engine.util.Reference;
import com.r4l.simple_2d_engine.util.Renderable;

public class TextComponent extends Component implements Renderable{
	
	private volatile String text;
	
	private Font font;
	
	private int zOrder;
	
	private String posComponentName;
	
	private PositionComponent pos;

	public TextComponent(String text, String fontName, int fontStyle, int fontSize, int zOrder) {
		super("DefaultText");
		addDependency(PositionComponent.class);
		posComponentName = "DefaultPosition";
		setText(text);
		setFont(new Font(fontName, fontStyle, fontSize * Reference.SCALE));
		setZOrder(zOrder);
	}
	
	public TextComponent(String name, String posComponentName, String text, String fontName, int fontStyle, int fontSize, int zOrder) {
		super("DefaultText");
		this.posComponentName = posComponentName;
		addDependency(PositionComponent.class);
		setText(text);
		setFont(new Font(fontName, fontStyle, fontSize * Reference.SCALE));
		setZOrder(zOrder);
	}
	
	
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public int getzOrder() {
		return zOrder;
	}


	public void setzOrder(int zOrder) {
		this.zOrder = zOrder;
	}


	
	public int getZOrder() {
		return zOrder;
	}


	
	public void setZOrder(int zOrder) {
		this.zOrder = zOrder;
		
	}


	public Font getFont() {
		return font;
	}


	public void setFont(Font font) {
		this.font = font;
	}


	public void render(Graphics2D g2) {
		pos = getEntity().GetComponent(posComponentName);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setFont(font);
		g2.drawString(text, pos.getX(), pos.getY());
	}
	
}

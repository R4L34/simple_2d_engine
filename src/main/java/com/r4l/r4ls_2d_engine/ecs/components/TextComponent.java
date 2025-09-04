package com.r4l.r4ls_2d_engine.ecs.components;

import java.awt.Font;

import com.r4l.r4ls_2d_engine.ecs.Component;
import com.r4l.r4ls_2d_engine.util.Reference;

public class TextComponent extends Component{
	
	private volatile String text;
	
	private Font font;
	
	private int zOrder;

	public TextComponent(String text, String fontName, int fontStyle, int fontSize, int zOrder) {
		super("Text");
		setText(text);
		setFont(new Font(fontName, fontStyle, fontSize * Reference.SCALE));
		setZOrder(zOrder);
	}
	
	public TextComponent(String group, String text, String fontName, int fontStyle, int fontSize, int zOrder) {
		super("Text", group);
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


}

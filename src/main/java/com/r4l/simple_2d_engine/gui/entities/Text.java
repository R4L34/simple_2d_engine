package com.r4l.simple_2d_engine.gui.entities;

import java.awt.Font;

import com.r4l.simple_2d_engine.ecs.Entity;
import com.r4l.simple_2d_engine.ecs.components.PositionComponent;
import com.r4l.simple_2d_engine.ecs.components.TextComponent;
import com.r4l.simple_2d_engine.util.Reference;

public class Text extends Entity{
	public Text(String name, String text, int x, int y, int size, int zOrder) {
		super(name, "Sprite");
		
		addComponent(new PositionComponent(x, y));
		addComponent(new TextComponent(text, "Tahoma", Font.BOLD, size, zOrder));
	}
	
	private TextComponent getTextComponent() {
        return this.GetComponent(TextComponent.class);
    }
	
	
	public String getFontName() {
		return getTextComponent().getFont().getFontName();
	}
	
	public void setFontName(String fontName) {
		Font f = getTextComponent().getFont();
        getTextComponent().setFont(new Font(fontName, f.getStyle(), f.getSize()));
	}
	
	public int getFontStyle() {
		return getTextComponent().getFont().getStyle();
	}
	
	public void setFontStyle(int fontStyle) {
		Font f = getTextComponent().getFont();
        getTextComponent().setFont(new Font(f.getFontName(), fontStyle, f.getSize()));
	}
	
	public int getTextSize() {
		return getTextComponent().getFont().getSize() / Reference.SCALE;
	}
	
	public void setTextSize(int size) {
            Font f = getTextComponent().getFont();
            getTextComponent().setFont(new Font(f.getFontName(), f.getStyle(), size * Reference.SCALE));
    }
	
	public float getOpacity() {
		return getTextComponent().getOpacity();
	}

	public void setOpacity(float opacity) {
		getTextComponent().setOpacity(opacity);
	}
}

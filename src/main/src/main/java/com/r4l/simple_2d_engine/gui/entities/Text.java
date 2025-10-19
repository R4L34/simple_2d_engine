package com.r4l.simple_2d_engine.gui.entities;

import java.awt.Font;

import com.r4l.simple_2d_engine.ecs.Entity;
import com.r4l.simple_2d_engine.ecs.components.PositionComponent;
import com.r4l.simple_2d_engine.ecs.components.TextComponent;
import com.r4l.simple_2d_engine.util.Alignment;
import com.r4l.simple_2d_engine.util.Reference;

public class Text extends Entity{
	public Text(String name, String text, int x, int y, int size, int zOrder) {
		super(name, "Text");
		
		addComponent(new PositionComponent(x, y));
		addComponent(new TextComponent(text, new Font(Reference.Text.DEFAULT_FONT, Reference.Text.DEFAULT_FONT_STYLE, size), zOrder, Reference.Text.DEFAULT_ALIGNMENT ));
	}
	
	public Text(String name, String text, int x, int y, int size, int zOrder, Alignment a) {
		super(name, "Text");
		
		addComponent(new PositionComponent(x, y));
		addComponent(new TextComponent(text, new Font(Reference.Text.DEFAULT_FONT, Reference.Text.DEFAULT_FONT_STYLE, size), zOrder, a));
	}
	
	public PositionComponent getPos() {
        return this.GetComponent(PositionComponent.class);
    }
	
	public TextComponent getTextComponent() {
        return this.GetComponent(TextComponent.class);
    }
}

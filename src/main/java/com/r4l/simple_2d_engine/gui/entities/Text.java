package com.r4l.simple_2d_engine.gui.entities;

import java.awt.Font;

import com.r4l.simple_2d_engine.ecs.Entity;
import com.r4l.simple_2d_engine.ecs.components.PositionComponent;
import com.r4l.simple_2d_engine.ecs.components.TextComponent;

public class Text extends Entity{
	public Text(String name, String text, int x, int y, int size, int zOrder) {
		super(name, "Sprite");
		
		addComponent(new PositionComponent(x, y));
		addComponent(new TextComponent(text, new Font("Tahoma", Font.BOLD, size), zOrder));
	}
	
	public PositionComponent getPos() {
        return this.GetComponent(PositionComponent.class);
    }
	
	public TextComponent getTextComponent() {
        return this.GetComponent(TextComponent.class);
    }
}

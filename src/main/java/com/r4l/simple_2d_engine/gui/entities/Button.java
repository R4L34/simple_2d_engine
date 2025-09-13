package com.r4l.simple_2d_engine.gui.entities;

import java.awt.Font;

import com.r4l.simple_2d_engine.Engine;
import com.r4l.simple_2d_engine.ecs.Entity;
import com.r4l.simple_2d_engine.ecs.components.HitboxComponent;
import com.r4l.simple_2d_engine.ecs.components.PositionComponent;
import com.r4l.simple_2d_engine.ecs.components.RenderComponent;
import com.r4l.simple_2d_engine.ecs.components.SizeComponent;
import com.r4l.simple_2d_engine.ecs.components.TextComponent;
import com.r4l.simple_2d_engine.event.events.ButtonEvent;
import com.r4l.simple_2d_engine.util.HitboxType;
import com.r4l.simple_2d_engine.util.Reference;

public class Button extends Entity {
    
    private String rcMainSprite, rcHoverSprite, rcPressSprite;
    
    protected PositionComponent pos;
    protected SizeComponent size;
    protected RenderComponent render;
    
    private int count = 0;

    public Button(String name, String text, int x, int y, int width, int height, String rcMainSprite, String rcHoverSprite, String rcPressSprite, int zOrder) {
        super(name, "Button");

        this.pos = new PositionComponent(x, y);
        this.size = new SizeComponent(width, height);
        this.render = new RenderComponent(rcMainSprite, zOrder);

        this.addComponent(pos);
        this.addComponent(size);
        this.addComponent(render);

        this.addComponent(new HitboxComponent("HOVER", HitboxType.HOVER));
        this.addComponent(new HitboxComponent("PRESS", HitboxType.PRESS));
        this.addComponent(new HitboxComponent("RELEASE", HitboxType.RELEASE));
        
        this.rcMainSprite = rcMainSprite;
        this.rcHoverSprite = rcHoverSprite;
        this.rcPressSprite = rcPressSprite;

        if (!text.equals("")) {
            PositionComponent textPos = new PositionComponent("TextPos", x + width / 4, y + height / 2);
            this.addComponent(textPos);
            this.addComponent(new TextComponent("Text", "TextPos", text, "Tahoma", Font.PLAIN, 7, zOrder));
        }
    }

    public void onClick() {
        this.GetComponent(RenderComponent.class).setImage(rcMainSprite);
        Engine.EVENT_BUS.post(new ButtonEvent.Click(getECS().getScreen(), this));
    }
    
    public void onPress() {
        count++;
        this.GetComponent(RenderComponent.class).setImage(rcPressSprite);
        Engine.EVENT_BUS.post(new ButtonEvent.Press(getECS().getScreen(), this));
    }
    
    public void onRelease() {
        HitboxComponent h = GetComponent("HOVER");
        if (h.isHovered() && count > 0) {
            onClick();
        } else {
            this.GetComponent(RenderComponent.class).setImage(rcMainSprite);
        }
        
        count = 0; // the only reset point needed
        Engine.EVENT_BUS.post(new ButtonEvent.Release(getECS().getScreen(), this));
    }

    public void onHover() {
        this.GetComponent(RenderComponent.class).setImage(rcHoverSprite);
        Engine.EVENT_BUS.post(new ButtonEvent.Hover(getECS().getScreen(), this));
    }
    
    public void onUnhover() {
        this.GetComponent(RenderComponent.class).setImage(rcMainSprite);
        Engine.EVENT_BUS.post(new ButtonEvent.Unhover(getECS().getScreen(), this));
    }

    public String getRcMainSprite() {
        return rcMainSprite;
    }

    public void setRcMainSprite(String rcMainSprite) {
        this.rcMainSprite = rcMainSprite;
    }

    public String getRcHoverSprite() {
        return rcHoverSprite;
    }

    public void setRcHoverSprite(String rcHoverSprite) {
        this.rcHoverSprite = rcHoverSprite;
    }

    public String getRcPressSprite() {
        return rcPressSprite;
    }

    public void setRcPressSprite(String rcPressSprite) {
        this.rcPressSprite = rcPressSprite;
    }
    
    public TextComponent getTextComponent() {
        if (!hasComponent("Text")) { return null; }
        return this.GetComponent("Text");
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

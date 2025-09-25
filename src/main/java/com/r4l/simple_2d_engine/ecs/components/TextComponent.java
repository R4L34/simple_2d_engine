package com.r4l.simple_2d_engine.ecs.components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.r4l.simple_2d_engine.ecs.Component;
import com.r4l.simple_2d_engine.util.Alignment;
import com.r4l.simple_2d_engine.util.Reference;
import com.r4l.simple_2d_engine.util.Renderable;

public class TextComponent extends Component implements Renderable {

    private volatile String text;
    
    private Font font;
    
    private int zOrder;
    
    private String posComponentName;
    
    private PositionComponent pos;

    private Color color = Color.BLACK;
    
    private float opacity;
    
    private Alignment alignment;
    
    private int textWidth;
    
    private int textHeight;

    public TextComponent(String text, Font font, int zOrder, Alignment alignment) {
        super("DefaultText");
        opacity = 1.0f;
        addDependency(PositionComponent.class);
        posComponentName = "DefaultPosition";
        setText(text);
        this.font = font.deriveFont((float) (font.getSize() * Reference.SCALE));
        setColor(Color.BLACK);
        setZOrder(zOrder);
        this.alignment =alignment;
    }

    public TextComponent(String name, String posComponentName, String text, Font font, int zOrder, Alignment alignment) {
        super(name);
        opacity = 1.0f;
        this.posComponentName = posComponentName;
        addDependency(PositionComponent.class);
        this.font = font.deriveFont((float) (font.getSize() * Reference.SCALE));
        setColor(Color.BLACK);
        setZOrder(zOrder);
        this.alignment = alignment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getZOrder() {
        return zOrder;
    }

    public void setZOrder(int zOrder) {
        this.zOrder = zOrder;
    }



    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = Math.max(0.0f, Math.min(1.0f, opacity));
    }
    

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}
    
    ///////////////////////////////
    ///
    public Font getFont() {
        return font.deriveFont((float) (font.getSize2D() / Reference.SCALE));
    }

    public void setFont(Font font) {
        this.font = font.deriveFont((float) (font.getSize() * Reference.SCALE));
    }

    public String getFontName() {
        return font.getFontName();
    }

    public void setFontName(String fontName) {
        this.font = new Font(fontName, font.getStyle(), font.getSize()).deriveFont((float) (font.getSize() * Reference.SCALE));
    }

    public int getFontStyle() {
        return font.getStyle();
    }

    public void setFontStyle(int style) {
        this.font = new Font(font.getFontName(), style, font.getSize()).deriveFont((float) (font.getSize() * Reference.SCALE));
    }

    public int getFontSize() {
        return (int) (font.getSize2D() / Reference.SCALE);
    }

    public void setFontSize(int size) {
        this.font = font.deriveFont((float) (size * Reference.SCALE));
    }
    
    
    public int getTextWidth() {
		return textWidth;
	}
    
    
    public int getTextHeight() {
		return textHeight;
	}
    
    //Used in render methd
    private int getWidth(Graphics2D g2) {
    	textWidth = g2.getFontMetrics(font).stringWidth(text);
        return textWidth;
    }

    private int getHeight(Graphics2D g2) {
    	textHeight = g2.getFontMetrics(font).getHeight();
        return textHeight;
    } 


    @Override
    public void render(Graphics2D g2) {
        pos = getEntity().GetComponent(posComponentName);

        Composite oldComposite = g2.getComposite();

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(font);
        g2.setColor(color);
        ///
        
        int x = pos.getX();
        int y = pos.getY();
        
        
        switch (alignment) {
        case LEFT:
        	g2.drawString(text, x, y);
            break;
        case CENTER:
        	g2.drawString(text, x - getWidth(g2) / 2, y);
            break;
        case RIGHT:
        	g2.drawString(text, x - getWidth(g2), y);
            break;
        
        default:
        	g2.drawString(text, x, y);
    }
        
        getHeight(g2);

        g2.setComposite(oldComposite);
    }
}

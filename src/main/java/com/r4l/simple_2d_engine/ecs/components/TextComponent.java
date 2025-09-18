package com.r4l.simple_2d_engine.ecs.components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import com.r4l.simple_2d_engine.ecs.Component;
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

    public TextComponent(String text, Font font, int zOrder) {
        super("DefaultText");
        opacity = 1.0f;
        addDependency(PositionComponent.class);
        posComponentName = "DefaultPosition";
        setText(text);
        setFont(new Font(font.getFontName(), font.getStyle(), font.getSize() * Reference.SCALE));
        setColor(Color.BLACK);
        setZOrder(zOrder);
    }

    public TextComponent(String name, String posComponentName, String text, Font font, int zOrder) {
        super(name);
        opacity = 1.0f;
        this.posComponentName = posComponentName;
        addDependency(PositionComponent.class);
        setText(text);
        setFont(new Font(font.getFontName(), font.getStyle(), font.getSize() * Reference.SCALE));
        setColor(Color.BLACK);
        setZOrder(zOrder);
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

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
    
    
    public String getFontName() {
        return font.getFontName();
    }

    public void setFontName(String fontName) {
        font = new Font(fontName, font.getStyle(), font.getSize());
    }

    public int getFontStyle() {
        return font.getStyle();
    }

    public void setFontStyle(int style) {
        font = new Font(font.getFontName(), style, font.getSize());
    }

    public int getFontSize() {
        return font.getSize() / Reference.SCALE;
    }

    public void setFontSize(int size) {
        font = new Font(font.getFontName(), font.getStyle(), size * Reference.SCALE);
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

    @Override
    public void render(Graphics2D g2) {
        pos = getEntity().GetComponent(posComponentName);

        Composite oldComposite = g2.getComposite();

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(font);
        g2.setColor(color);
        g2.drawString(text, pos.getX(), pos.getY());

        g2.setComposite(oldComposite);
    }
}

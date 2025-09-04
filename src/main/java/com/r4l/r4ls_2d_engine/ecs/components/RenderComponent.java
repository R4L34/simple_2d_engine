package com.r4l.r4ls_2d_engine.ecs.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.r4l.r4ls_2d_engine.ecs.Component;

public class RenderComponent extends Component{
	
	private BufferedImage image;
	
    private volatile String resourceLocation;
    
    private int zOrder;
    
    public RenderComponent(String resourceLocation) {
		super("Render");
		this.resourceLocation = resourceLocation;
		setZOrder(0);
	}
    
    public RenderComponent(String group, String resourceLocation) {
		super("Render", group);
		this.resourceLocation = resourceLocation;
		setZOrder(0);
	}
    
    public RenderComponent(String resourceLocation, int zOrder) {
		super("Render");
		this.resourceLocation = resourceLocation;
		setZOrder(zOrder);
	}
    
    public RenderComponent(String group, String resourceLocation, int zOrder) {
		super("Render", group);
		this.resourceLocation = resourceLocation;
		setZOrder(zOrder);
	}
    
    private void loadImage() {
        try {
            BufferedImage loaded = ImageIO.read(getClass().getResourceAsStream(resourceLocation));
            if (loaded != null) {
                if (loaded.getType() != BufferedImage.TYPE_INT_ARGB) {
                    BufferedImage converted = new BufferedImage(
                        loaded.getWidth(),
                        loaded.getHeight(),
                        BufferedImage.TYPE_INT_ARGB
                    );
                    Graphics2D g = converted.createGraphics();
                    g.drawImage(loaded, 0, 0, null);
                    g.dispose();
                    this.image = converted;
                } else {
                    this.image = loaded;
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
    
	public BufferedImage getImage() {
		loadImage();
		return image;
	}
	public void setImage(String rc) {
		resourceLocation = rc;
		loadImage();
	}

	public int getZOrder() {
		return zOrder;
	}

	public void setZOrder(int zOrder) {
		this.zOrder = zOrder;
	}
	

}

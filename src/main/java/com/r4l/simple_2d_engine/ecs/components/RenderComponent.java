package com.r4l.simple_2d_engine.ecs.components;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.r4l.simple_2d_engine.ecs.Component;
import com.r4l.simple_2d_engine.util.Renderable;
public class RenderComponent extends Component implements Renderable{
	
	private BufferedImage image;
	
    private volatile String resourceLocation;
    
    private int zOrder;
    
    private float opacity;
    
    //Components
    
    private String posComponentName;
    
    private String sizeComponentName;
    
    private PositionComponent pos;
    
    private SizeComponent size;
    
    
    public RenderComponent(String resourceLocation, int zOrder) {
		super("DefaultRender");
		addDependencies();
		posComponentName = "DefaultPosition";
		sizeComponentName = "DefaultSize";
		this.resourceLocation = resourceLocation;
		opacity = 1.0f;
		setZOrder(zOrder);
		loadImage();
	}
    
    public RenderComponent(String name, String posComponentName, String sizeComponentName, String resourceLocation, int zOrder) {
 		super(name);
 		addDependencies();
 		this.posComponentName = posComponentName;
 		this.sizeComponentName = sizeComponentName;
 		this.resourceLocation = resourceLocation;
 		opacity = 1.0f;
 		setZOrder(zOrder);
 		loadImage();
 	}
    
    private void addDependencies() {
    	addDependency(PositionComponent.class);
    	addDependency(SizeComponent.class);
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
	
    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = Math.max(0.0f, Math.min(1.0f, opacity));
    }
	
	
	public void render(Graphics2D g2) {
		pos = getEntity().GetComponent(posComponentName);
		size = getEntity().GetComponent(sizeComponentName);
        if (image == null) return;


        Composite oldComposite = g2.getComposite();

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2.drawImage(image, pos.getScaledX(), pos.getScaledY(), size.getScaledWidth(), size.getScaledHeight(), null);

        // Restore old composite
        g2.setComposite(oldComposite);
	}
	

}

package com.r4l.simple_2d_engine.ecs.components;

import com.r4l.simple_2d_engine.ecs.Component;
import com.r4l.simple_2d_engine.util.HitboxType;

public class HitboxComponent extends Component{
	
	private HitboxType hitboxType;
	
	private boolean isHovered = false;
	
	private boolean isEnabled = true;
	
	//Components
	
	private String posComponentName, sizeComponentName;
    
    private PositionComponent pos;
    
    private SizeComponent size;
	
	public HitboxComponent(String name, HitboxType hitboxType) {
		super(name);
		addDependencies();
		setHitboxType(hitboxType);
		posComponentName = "DefaultPosition";
		sizeComponentName = "DefaultSize";
	}
	
	public HitboxComponent(String name, String posComponentName, String sizeComponentName, HitboxType hitboxType) {
		super(name);
		addDependencies();
		setHitboxType(hitboxType);
	}
	
	private void addDependencies() {
		addDependency(PositionComponent.class);
		addDependency(SizeComponent.class);
	}


	public HitboxType getHitboxType() {
		return hitboxType;
	}

	public void setHitboxType(HitboxType hitboxType) {
		this.hitboxType = hitboxType;
	}

	public boolean isHovered() {
		return isHovered;
	}

	public void setHovered(boolean isHovered) {
		this.isHovered = isHovered;
	}

	public PositionComponent getPos() {
		return pos;
	}

	public SizeComponent getSize() {
		return size;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void Enable() {
		this.isEnabled = true;
	}
	
	public void Disable() {
		this.isEnabled = false;
	}
	
	public void initialise() {
		pos = getEntity().GetComponent(posComponentName);
		size = getEntity().GetComponent(sizeComponentName);
	}
}


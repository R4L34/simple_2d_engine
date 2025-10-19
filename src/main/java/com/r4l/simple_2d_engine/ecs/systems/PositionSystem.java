package com.r4l.simple_2d_engine.ecs.systems;

import com.r4l.simple_2d_engine.ecs.Entity;
import com.r4l.simple_2d_engine.ecs.components.PositionComponent;
import com.r4l.simple_2d_engine.util.MovementType;

public class PositionSystem {
	
	public static void changePosition(Entity e, String name, double x, double y, MovementType type) {
		PositionComponent pos;
		
		if (e.hasComponent(PositionComponent.class)) {
			System.out.println("Cant change position. There is no PosComponent!");
			return;
		}

		if (name == null) {
			pos = e.GetComponent(PositionComponent.class);
		} else if (e.hasComponent(name)){
			pos = e.GetComponent(name);
		} else {
			System.out.println("Cant change position. There is no PosComponent with that name!");
			return;
		}
		
		if (type == MovementType.MOVE_TO) {
			pos.setX(x);
			pos.setY(y);
			
		} else if (type == MovementType.MOVE_BY) {
			pos.setX(pos.getX() + x);
			pos.setY(pos.getY() + y);
		}
		
	}

}

package com.r4l.simple_2d_engine.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

public class Reference {
	
	public static Dimension BASE_RESOLUTION = new Dimension(640, 360);
	
	public static double SCALE = 0;
	
	public static Dimension FINAL_RESOLUTION;
	
	public static int TICKRATE = 240;
	
	public static Color MAIN_SCREEN_BG_COLOR = Color.WHITE;
	
	public static boolean isFULLSCREEN = false;
	
	public static Image ICON = null;
	
	//Technical
	
	public static Dimension SCREEN_SIZE;

	public static final int NANOSECOND = 1_000_000_000;
	
	
	
	//Text vars
	public static class Text{
		
		public static String DEFAULT_FONT = "Tahoma"; 
		
		public static int DEFAULT_FONT_STYLE = Font.BOLD;
		
		public static Alignment DEFAULT_ALIGNMENT = Alignment.CENTER;
	}

}

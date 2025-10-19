package com.r4l.simple_2d_engine.util;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TextManager {
	
	public static List<String> wrapText(String text, Font font, double maxWidth){
		
		List<String> lines = new ArrayList<>();
		
		
		Font f = font.deriveFont((float) (font.getSize() * Reference.SCALE));
		// Create temporary graphics for width measurement
        BufferedImage tmpImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = tmpImg.createGraphics();
        g2d.setFont(f);
        
        maxWidth = maxWidth * Reference.SCALE;
        
        
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            String testLine = currentLine.length() == 0 ? word : currentLine + " " + word;
            int testWidth = g2d.getFontMetrics().stringWidth(testLine);

            if (testWidth > maxWidth) {
                // Commit the current line and start a new one
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            } else {
                currentLine = new StringBuilder(testLine);
            }
        }

        // Add last line
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        g2d.dispose();
        return lines;
	}
	
	
	
public static List<String> wrapText(String text, int fontSize, double maxWidth){
		
		List<String> lines = new ArrayList<>();
		
		
		Font f = new Font(Reference.Text.DEFAULT_FONT, Reference.Text.DEFAULT_FONT_STYLE, (int) (fontSize * Reference.SCALE));
		// Create temporary graphics for width measurement
        BufferedImage tmpImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = tmpImg.createGraphics();
        g2d.setFont(f);
        
        maxWidth = maxWidth * Reference.SCALE;
        
        
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            String testLine = currentLine.length() == 0 ? word : currentLine + " " + word;
            int testWidth = g2d.getFontMetrics().stringWidth(testLine);

            if (testWidth > maxWidth) {
                // Commit the current line and start a new one
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            } else {
                currentLine = new StringBuilder(testLine);
            }
        }

        // Add last line
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        g2d.dispose();
        return lines;
	}

}

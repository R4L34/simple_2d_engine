package com.r4l.simple_2d_engine.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHandler {

	public static boolean reg_match(String str, String regex) {
		
		Pattern MY_PATTERN = Pattern.compile(regex);
		Matcher m = MY_PATTERN.matcher(str);
		
		return m.find();
		
	}
	
	public static String reg_replace(String str, String regex, String replacement) {
		return str.replaceAll(regex, replacement);
	}

}

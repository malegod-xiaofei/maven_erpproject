package com.tledu.zyf.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties jdbcProperties = null;
	
	private PropertiesUtil(){
		
	}
	
	public static Properties getProperties(){
		if (jdbcProperties == null) {
			jdbcProperties = new Properties();
			try {
				jdbcProperties.load(DBUtil.class.getClassLoader().getResourceAsStream(
						"jdbc.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return jdbcProperties;
	}
}

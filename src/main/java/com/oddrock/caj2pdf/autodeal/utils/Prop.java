package com.oddrock.caj2pdf.autodeal.utils;

import java.io.File;
import java.io.IOException;

import com.oddrock.common.prop.PropertiesReader;

public class Prop {
	private static final PropertiesReader PR = new PropertiesReader();
	static{
		load();
	}
	
	private static void load(){
		PR.addFilePath("autodeal.properties");
		PR.loadProperties();
		File dir = new File(Prop.get("props.dirpath"));
		for(File file : dir.listFiles()){
			if(file.isFile() && file.getName().endsWith("properties")){
				try {
					PR.addFilePath(file.getCanonicalPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				PR.loadProperties();
			}
		}
	}
	
	public static String get(String key){
		return PR.getValue(key);
	}
	
	public static int getInt(String key){
		return PR.getIntValue(key);
	}
	
	public static boolean getBool(String key){
		return PR.getBooleanValue(key);
	}
	
	public static String get(String key, String defaultValue) {
		return PR.getValue(key, defaultValue);
	}
}
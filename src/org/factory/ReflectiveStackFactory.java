package org.factory;

import java.io.FileInputStream;
import java.util.Properties;

public class ReflectiveStackFactory {
public static Stack getInstance() {
		
		Stack s=null;
		Properties p=new Properties();
		try {
			
			p.load(new FileInputStream("C:\\Users\\Admin\\git\\DesignPattern\\src\\resources\\config.properties"));
			String className=p.getProperty("StackImpl");
			Class<?> clazz = Class.forName(className);
			Object stackImpl = clazz.newInstance();			;
			s=(Stack)stackImpl;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;		
	}
	
}

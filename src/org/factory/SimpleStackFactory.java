package org.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SimpleStackFactory {

	public static Stack getInstance(int i) {
		// TODO Auto-generated method stub
		Stack s=null;
		switch (i)
		{
			case 1:s=new StackUsingLinkedList();
					 break;
			case 2:s=new StackUsingQueue();
			 break;
			 
			default :s=new StackUsingQueue();
			 break;
		}
		return s;
	}

	public static Stack getInstance() {
		// TODO Auto-generated method stub
		Stack s=null;
		Properties p=new Properties();
		try {
			p.load(new FileInputStream("C:\\Users\\Admin\\git\\DesignPattern\\src\\resources\\config.properties"));
			int q=Integer.parseInt(p.getProperty("QOS"));
			s=getInstance(q);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	
}

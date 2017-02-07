package org.composite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
   Composite pattern lets you to  manipulate composites exactly the same 
   way we manipulate primitive/individual objects. Composite objects are objects having other object. 
   Primitive objects don't have any object.
   
   
   Java developers need the Composite pattern because we often must manipulate composites exactly 
   the same way we manipulate primitive objects. For example, graphic primitives such as 
   lines or text must be drawn, moved, and resized. But we also want to perform the same 
   operation on composites, such as drawings, that are composed of those primitives. 
   Ideally, we'd like to perform operations on both primitive objects and composites in exactly 
   the same manner, without distinguishing between the two. If we must distinguish between primitive 
   objects and composites to perform the same operations on those two types of objects, our code 
   would become more complex and more difficult to implement, maintain, and extend.
   
   The real life example is java.io.File
   This File represents file as well as directory.So we can say that File is File and File has File.
   
   This pattern can benefit us tremendously when we use it with 'RECURSION'.
   
    
   Suppose we have to list down all the files & directories in a given directory then its program is as follows:  
*/ 
public class CompositePatternDemo 
{

	public static void main(String[] args) 
	{
		String path="D:\\Users\\aniparan\\Desktop\\test";
		File file=new File(path);
		List<File> files=new ArrayList<File>();		
		List<File> filesSystem=getFilesAndFolders(file,files);
			
		printFilesAndFolders(filesSystem);
			
	}
	
	public static List<File> getFilesAndFolders(File file,List<File> files)
	{
		files.add(file);
		if(file.isDirectory())
		{
			File[] list=file.listFiles();
			for(File f:list)
				getFilesAndFolders(f, files);
		}
		return files;
	}
	private static void printFilesAndFolders(List<File> filesSystem) {
		for(File f:filesSystem)
		{
			System.out.println(f.getName()+" Folder:"+f.isDirectory());
		}		
	}

}

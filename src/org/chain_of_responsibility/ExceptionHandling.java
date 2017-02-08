package org.chain_of_responsibility;

/*Lets consider another example.Here we will design exception handling mechanism. First exception is matched by the first block. If it is
 able to handle it then flow continues, otherwise it then passed to the second block and so on.If last block also not able to handle it then
 exception is translated back to calling program.
 
 In this example we are going to implement chain of responsibility bit different way.
*/
class ExceptionHandler
{
	ExceptionHandler next;
	public void handleException(ExceptionHandler e)
	{		
		System.out.println("Exception is propogated back to calling method.");
	}
	
	
}
class Throwable extends ExceptionHandler
{
	
}
class Exception extends ExceptionHandler
{	
	@Override
	public void handleException(ExceptionHandler e) 
	{		
		if(e instanceof Exception)
		{
			System.out.println("Exception handled by Exception block");
		}
		else
		{
			next.handleException(e);
		}
	}
	
}
class IOException extends Exception
{
	@Override
	public void handleException(ExceptionHandler e) 
	{		
		if(e instanceof IOException)
		{
			System.out.println("Exception handled by IOException block");
		}
		else
		{
			next.handleException(e);
		}
	}
}
class FileNotFoundException extends IOException
{
	@Override
	public void handleException(ExceptionHandler e) 
	{		
		if(e instanceof FileNotFoundException)
		{
			System.out.println("Exception handled by FileNotFoundException block");
		}
		else
		{
			next.handleException(e);
		}
	}
}


public class ExceptionHandling 
{

	public static void main(String[] args) 
	{
		ExceptionHandler haldler=new ExceptionHandlingFactory().getInstance();
		
		FileNotFoundException f=new FileNotFoundException();	
		haldler.handleException(f);
		
		IOException i=new IOException();	
		haldler.handleException(i);
		
		Exception e=new Exception();	
		haldler.handleException(e);
		
		Throwable th=new Throwable();
		haldler.handleException(th);
	}

}
class ExceptionHandlingFactory
{
	ExceptionHandler firstHandler=null;
	ExceptionHandlingFactory()
	{
		//Instantiate handlers
		FileNotFoundException f=new FileNotFoundException();
		IOException i=new IOException();
		Exception e=new Exception();
		ExceptionHandler h=new ExceptionHandler();
		
		//Set the handlers chain
		f.next=i;
		i.next=e;
		e.next=h;	
		
		//Set the handler who handles the request first.
		firstHandler=f;
				
	}
	public ExceptionHandler getInstance()
	{
		return firstHandler;
	}
}

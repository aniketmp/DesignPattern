/*
 * There are many objects we only need on of: auditorium, cache, registry settings, objects used for logging etc. In fact,
 * for many of these type of object , if we were to instantiate more than one we'd run into all sort of problems like 
 * incorrect program behavior,overuse of resources or inconsistent result.   
 *  
 *  In many ways , the singleton pattern is a convenient for ensuring one and only one object is instantiated for a given class.
 */
package org.singleton;

public class Singleton {
	private static Singleton uniqueInstance;//We have static variable to hold the instance of the class.	
	
	private Singleton() //private constructor , so this class cannot be instantiated from outside the class.But it can still instantiated by the code within class itself.
	{
		
	}
	//-------------------Stage 1---------------------------
	/*
	public static Singleton getInstance()
	{
		
		if(uniqueInstance==null) //we are checking whether we have already instantiated this object or not.
		{
			uniqueInstance=new Singleton();
		}
				
		return uniqueInstance;
	}
	*/
	/*Stage 1 has a bug. Suppose in multi-threading environment if thread1 & thread2 encountered if condition simultaneously, then their call stack is as follows.
	 * thread1:if(uniqueInstance==null): true--> enter if condition--> thread1 goes to runnable state from running state.
	 * thread2:if(uniqueInstance==null): true--> enter if condition--> thread2 goes to runnable state from running state & thread 1 backs to running state.
	 * thread1:Since thread1 already entered into if condition created uniqueInstance Object.
	 * thread1:thread2 is also already entered into if condition it will also create uniqueInstance Object.
	 * 
	 * Hence there will be 2 objects.
	 */	
	//-------------------Stage 2---------------------------	
	/*
	 * By adding synchronized keyword to getInstance(), we force every thread to wait its turn before it can enter the method. That is 
	 * no two threads may enter the method at the same time.
	 */
	/*
	public static synchronized Singleton getInstance()
	{
			
		if(uniqueInstance==null) 
		{
			uniqueInstance=new Singleton();
		}
				
		return uniqueInstance;
	}
	*/
	
	/*Stage 2 also has a efficiency problem.
	 * It solves the problem we faced in stage1 but 'synchronization' is expensive.Here the only time synchronization is relevant is the 
	 * first time through this method. In other words, once we've set the uniqueInstance variable to instance of Singleton, we have no
	 * further need to synchronize this method. After the first time through, synchronization is totally unneeded overhead! 
	 * 
	 */	
	
	//-------------------Stage 3---------------------------			
	/*Solution 1:Move to eagerly created instance rather than lazily created one.
	 * private static Singleton uniqueInstance=new Singleton();
	 * public static Singleton getInstance()
	 * {
	 * 	return uniqueInstance;
	 * }
	 *
	 * 
	 * Solution 2:Use double checking locking to reduce the use of synchronization in getInstance()
	 */
	
	
	public static synchronized Singleton getInstance()
	{
		if(uniqueInstance==null)  //check for instance & if there isn't one, enter the synchronized block.Note:We only synchronize the first time through! 
		{
			synchronized(Singleton.class)
			{
				if(uniqueInstance==null) //Once enter the block ,check again and if still null,create an instance.
				{
					uniqueInstance=new Singleton();
				}
			}
		}
		return uniqueInstance;
	}
	public static void main(String[] args) {
		Singleton singletonObj=Singleton.getInstance();
		
	}
}

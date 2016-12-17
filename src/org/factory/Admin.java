package org.factory;

public class Admin {
	public static void main(String arg[])
	{
		//Step1: Admin is creating the object of stack services i.e StackUsingLinkedList and StackUsingQueue
		System.out.println("------------Step1-----------");
		Client client=new Client();
		client.useStack(new StackUsingLinkedList());
		System.out.println();
		
		
		
		/*Problem:If we want to change the service then we have to change the existing code like this.*/		
		client=new Client();
		client.useStack(new StackUsingQueue());
		System.out.println();
		
		/*
		Step2: Hence Factory comes in.Admin is getting services from Factory object hence client is not dependent on any implementation of stack.
		Now StackFactory has to decide which service is to be provided based on the Quality of service parameters sent by the client*/
		System.out.println("------------Step2:Simple Stack Factory with QOS-----------");
		client=new Client();
		client.useStack(SimpleStackFactory.getInstance(2));
		System.out.println();
		
		/*Problem: In step2 ,if we want to change the parameter of QOS then we have to recompile to source code. 
		 Step3: So tomake it independent of code change we can shift the QOS logic to factory itself.*/ 
		
		System.out.println("------------Step3-----------");
		client=new Client();
		client.useStack(SimpleStackFactory.getInstance());//This method will read QOS from config.properties file and then use it to create service instance 
		System.out.println();
		
		/*Problem: In step 3 all the creational logic is shifted to Factory but factory is still not freed from creating service object.
		 * Hence tomorror if new implementation of stack comes e.g StackUsingStructure then we have to add one more switch case.Hence againg  
		 * we have to change/ recompile source code of factory which is problem.
		 * Step4:This is where Refelctive factory comes in and allows to create object of class dynamically through reflection.
		 */
		
		System.out.println("------------Step4:Reflective Stack Factory-----------");
		client=new Client();
		client.useStack(ReflectiveStackFactory.getInstance());//This method will read QOS from config.properties file and then use it to create service instance 
		System.out.println();
		
		
	}
	
}

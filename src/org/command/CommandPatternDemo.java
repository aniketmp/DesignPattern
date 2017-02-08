package org.command;
/*Commands are an object-oriented replacement for callbacks.
 *It allows you to decouples the requester of an action from the object that actually performs the action.
 *
 Let's design Remote control which control various device given by the client. 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

enum Action
{
	ON,OFF;
}
interface Command
{
	public void execute();
	public void revert();
}
//Client has 3 devices
class Light
{
	public void turnON()
	{
		System.out.println("Turning the Lights ON.");
	}
	public void turnOFF()
	{
		System.out.println("Turning the Lights OFF.");
	}
	
}
class Fan
{
	public void switchON()
	{
		System.out.println("Switching the Fan ON.");
	}
	public void switchOFF()
	{
		System.out.println("Switching the Fan OFF.");
	}
	
}
class AC
{
	public void powerON()
	{
		System.out.println("Starting the AC power.");
	}
	public void powerOFF()
	{
		System.out.println("Shutting down the AC power.");
	}
	
}

class FanCommand implements Command
{
	Action a;
	FanCommand(Action a)
	{
		this.a=a;
	}
	Fan fan=new Fan();
	@Override
	public void execute() {
		if(Action.ON==a)
		{
			fan.switchON();
		}
		else
		{
			fan.switchOFF();
		}
		
	}
	@Override
	public void revert() {
		if(Action.ON==a)
		{
			fan.switchOFF();
		}
		else
		{
			fan.switchON();
		}
		
	}
	
}
class LightCommand implements Command
{
	Action a;
	LightCommand(Action a)
	{
		this.a=a;
	}
	Light light=new Light();
	@Override
	public void execute() {
		if(Action.ON==a)
		{
			light.turnON();
		}
		else
		{
			light.turnOFF();
		}
		
	}
	@Override
	public void revert() {
		if(Action.ON==a)
		{
			light.turnOFF();
		}
		else
		{
			light.turnON();
		}
		
	}
	
}
class ACCommand implements Command
{
	Action a;
	ACCommand(Action a)
	{
		this.a=a;
	}
	AC ac=new AC();
	@Override
	public void execute() {
		if(Action.ON==a)
		{
			ac.powerON();
		}
		else
		{
			ac.powerOFF();
		}
		
	}
	@Override
	public void revert() {
		if(Action.ON==a)
		{
			ac.powerOFF();
		}
		else
		{
			ac.powerON();
		}
		
	}
	
}
class RemoteControl 
{	
	int slot;
	Command[] command=new Command[6];
	public void buttPressed(int i)
	{
		slot=i-1;
		if(command[slot]==null)
		{
			System.out.println("No command recognized!");
			return;
		}
		//Server then execute command at certain point of time as a result of either event happens or time elapsed.
		command[slot].execute();
	}
	//Server can undu this command.
	public void undoAction()
	{		
		if(command[slot]==null)
		{
			System.out.println("No command initiated previously!");
			return;
		}		
		command[slot].revert();
	}
	
	public void setCommand(int i,Command command) 
	{		
		this.command[i-1]=command;
	}
}
public class CommandPatternDemo 
{
	static int  choice=7;
	static RemoteControl remoteControl=new RemoteControl();
	
	
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		
		for(int i=1;i<7;i++)
		{
			 //Client creates command
			 Command command=createCommand(i);
		     //and pass it to server//
		     remoteControl.setCommand(i,command);
		     
		}
		 do
	      {        
		        System.out.print("========Home Automation Remote Control.============ \n");
		        System.out.print("            1. Light ON  \n");
		        System.out.print("            2. Light OFF   \n");  
		        System.out.print("            3. Fan ON \n");  
		        System.out.print("            4. Fan OFF \n");
		        System.out.print("            5. AC ON \n");
		        System.out.print("            6. AC OFF \n");
		        System.out.print("            7. Exit\n");		        
		        System.out.println("Note:press 0 to undu action!"); 
		        System.out.print("Press the button: ");  
		        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));   
		        choice=Integer.parseInt(br.readLine());
		        if(choice==0)
		        {
		        	remoteControl.undoAction();
		        	continue;
		        }
		        
		       
		      //server executes command
			     remoteControl.buttPressed(choice);
		       
		   }while(choice!=7);  
	}
	public static Command createCommand(int choice)
	{
		Command command=null;
		switch(choice)
		{			
			case 1:command=new LightCommand(Action.ON);break;
			case 2:command=new LightCommand(Action.OFF);break;
			case 3:command=new FanCommand(Action.ON);break;
			case 4:command=new FanCommand(Action.OFF);break;
			case 5:command=new ACCommand(Action.ON);break;
			case 6:command=new ACCommand(Action.OFF);break;
			default:command=null;break;
		}
		return command;
	}

}

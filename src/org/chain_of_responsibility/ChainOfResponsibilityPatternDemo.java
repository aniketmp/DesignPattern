package org.chain_of_responsibility;

/*
   Chain Of Responsibility is used when you want to give a chance to more than one object to handle 
   the request.
   
*   Chain of responsibility pattern is used to achieve loose coupling in software design where a 
    request from client is passed to a chain of objects to process them. Then the object in 
    the chain will decide themselves who will be processing the request and whether the 
    request is required to be sent to the next object in the chain or not.
    
    
*   Let’s see the example of chain of responsibility pattern in JDK and then we will proceed to 
    implement a real life example of this pattern. We know that we can have multiple catch 
    blocks in a try-catch block code. Here every catch block is kind of a processor to process 
    that particular exception.
    
*	So when any exception occurs in the try block, its send to the first catch block to process. 
	If the catch block is not able to process it, it forwards the request to next object in 
	chain i.e next catch block. If even the last catch block is not able to process it, the 
	exception is thrown outside of the chain to the calling program.
	
	Lets implement this example of E-mail system. First any email arrived comes to span handler, then 
	it goes to any filter created by user, if it is managed to pass through then finally it lands up
	in the inbox.
 */
//It represents Chain of Filters including last one 
class MailChain
{
	Mailbox[] mailBox;
	int index=0;
	public MailChain()
	{
		mailBox=new Mailbox[3];
		mailBox[0]=new Spam();
		mailBox[1]=new JobSearch();
		mailBox[2]=new Inbox();
	}
	public void handle(String mailStream)
	{
		if(index==mailBox.length)
		{
			index=0;
			return;
		}
		mailBox[index++].handle(mailStream,this);
	}	
	public void handledSuccessfully()
	{
		index=0;
	}
}
interface Mailbox
{
	public void handle(String emailContent,MailChain mailChain) ;
}

class Spam implements Mailbox
{
	
	public void handle(String emailContent,MailChain mailChain) 
	{		
		System.out.println("Checking content for Spam...");
		if(emailContent.contains("Lottery")||emailContent.contains("Jackpot"))
		{
			System.out.println("This is spam mail.");	
			mailChain.handledSuccessfully();
			return;
		}
		mailChain.handle(emailContent);
	}	
}
class JobSearch implements Mailbox
{	
	public void handle(String emailContent,MailChain mailChain) 
	{		
		System.out.println("Checking content for JobSearch...");
		if(emailContent.contains("Nokari")||emailContent.contains("Monster"))
		{
			System.out.println("This is Job related mail.");	
			mailChain.handledSuccessfully();
			return;
		}
		mailChain.handle(emailContent);
	}
}
class Inbox implements Mailbox
{	
	public void handle(String emailContent,MailChain mailChain) 
	{	
		if(!emailContent.isEmpty())
		{
			System.out.println("This is Job related mail.");	
			mailChain.handledSuccessfully();
			return;
		}		
		mailChain.handledSuccessfully();//Design of any filter should be identical...because tomorrow if I change their arrangement in MailChain, their logic should not be affected.
	}
}

public class ChainOfResponsibilityPatternDemo 
{
	public static void main(String[] args) 
	{
		
		MailChain m=new MailChain();
		String emailContent="Hello..how are you?";
		System.out.println("Sending mail 1:"+emailContent+"\n");
		m.handle(emailContent);
				
		
		emailContent="21 Jobs waiting for you in Nokari.com";
		System.out.println("\n\nSending mail 2:"+emailContent+"\n");
		m.handle(emailContent);
		
		emailContent="Congrats..you won a Jackpot !";
		System.out.println("\n\nSending mail 3:"+emailContent+"\n");
		m.handle(emailContent);
	}
}

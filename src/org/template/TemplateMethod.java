package org.template;
/*The Template method defines the skeleton/template/steps of an algorithm in the method and allows subclass to 
 * provide the implementation for one or more steps.
 *   
 *  
 * 
 * We call this template method pattern because 
 * 1)Its method after all
 * 2)It serves as a template for an algorithm.
 * 3)It defines sequence of steps ,each represented by a method.
 * 4)Some metods are handled by template class and some are handled by its child class
 *
 */
//some advantages are:
//It reduces duplicate code.
//Boilerplate codes are moved to common base class.
//code is open for expansion.

public abstract class TemplateMethod {

	//1)Its method after all Here's the template method. It's declared final to prevent subclass from tamper in the sequence of steps in algorithm.	
    //2)It serves as a template for an algorithm.
	final public void createAccount()
	{
		//3)It defines sequence of steps ,each represented by a method.
		verifyApplicationDetails(); // 4)Some metods are handled by template class and some are handled by its child class
		verifyIdentificationDetails(); //
		generateAccountNumber();
		if(appliedForCreditCard()) //Another important aspect is it can have hook() method. hook() method either do nothing or do default things.Here its returning default 'false'.Subclasses are free to override it or ignore it. 
		{
			sendCreditCard();
			sendCreditCardPin();
		}
			
	}
	public boolean appliedForCreditCard()
	{
		return false;
	}
	abstract public void verifyApplicationDetails();
	
	public void verifyIdentificationDetails()
	{
		System.out.println("Identification details verified.");
	}
	abstract public void generateAccountNumber();
	
	
	public void sendCreditCard()
	{
		System.out.println("Credit card sent by courrier.");
	}
	
	public void sendCreditCardPin()
	{
		System.out.println("Credit card pin sent by email.");
	}
	
	public static void main(String args[])
	{
		TemplateMethod t=new BusinessAccount();
		t.createAccount();
		System.out.println("------------------------------------------");
		t=new PersonalAccount();
		t.createAccount();
	}
}
class BusinessAccount extends TemplateMethod
{

	public boolean appliedForCreditCard() //hook() method.
	{
		return true;
	}
	
	//child class is free to override implemented method to add functionality.
	@Override
	public void sendCreditCard() {
		super.sendCreditCard();
		System.out.println("Gift voucher sent");
		
	}

	@Override
	public void verifyApplicationDetails() {
		System.out.println("Bussineess application details verified.");
		
	}

	@Override
	public void generateAccountNumber() {
		System.out.println("Bussineess account number generated.");
		
	}	
}
class PersonalAccount extends TemplateMethod
{

	@Override
	public void verifyApplicationDetails() {
		System.out.println("Personal application details verified.");
	}

	@Override
	public void generateAccountNumber() {
		System.out.println("Personal account number generated.");
		
	}
	
	@Override
	public void sendCreditCard() {
		super.sendCreditCard();
		System.out.println("Movie tickets sent");  		
	}
}


package org.decorater;

/*
 * 1)Decorator design pattern is used to enhance the functionality of a particular object at run-time or dynamically.
 * 2)The decorator pattern applies when there is a need to dynamically add as well as remove responsibilities to a class, and when subclassing would be impossible due to the large number of subclasses that could result.
 * 3)Critical requirement of Decorator pattern is that a decorated object can stand in place of original object i.e. it can be passed when a method expects original object.
 * 4)Decorator adds functionality before or after delegating the task to the original object
 * 
 * In java all the InputStream/OutputStream are the implementation of decorator pattern.
 * eg new ObjectInputStream(new FileInputStream("")).readObject();
 * This is another benifit of decorator that you can decorate all the objects in one line syntax.   
 *   
 * In order to show you, how to implement Decorator pattern, let me first explain requirements. Do we need to create software for calculating the price for a Sandwich, yummy... no? Since the customer can customize sandwich by asking extra cheese or extra fillings, you also need to include the cost of those items in the final price of Sandwich. Since this customization can vary a lot among different customers and offer from a shop, creating classes for different types of Sandwich with different fillings or extras e.g. BrownBreadSandWithCheese or WhiteBreaSandwitchWithCheeseAndTomato will just clutter the code with lots of endless small classes. 
   Now this problem looks a natural fit for applying Decorator pattern because we have a base object Sandwich, which can be decorated with extra cheese and fillings. By using Decorator pattern, you can extend the functionality of Sandwich at runtime, based upon customer's request, which is impossible with Inheritance until you have a specific class for every possible customer request. 

*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//1)Here Decorator design pattern is used to enhance the functionality of sandwich object at run-time or dynamically.
interface Sandwich
{  
    public String prepareFood();  
    public double foodPrice();
    
}// End of the Food interface.

class VegSandwich implements Sandwich 
{  
    public String prepareFood(){  
         return "Veg Sandwich";  
    }  
  
    public double foodPrice(){  
        return 50.0;  
    }
  
}
abstract class SandwichDecorator implements Sandwich
{  
    private Sandwich sandwich;  
    private SandwichDecorator previousIngredient;
    public SandwichDecorator(Sandwich sandwich)  
    {  
        this.sandwich=sandwich;  
    }  
    @Override  
    public String prepareFood()
    {  
        return sandwich.prepareFood();   
    }  
    public double foodPrice()
    {  
        return sandwich.foodPrice();  
    }  
  //2)The decorator pattern applies when there is a need to dynamically add as well as remove Ingredients.
    public Sandwich addIngredients(SandwichDecorator addDecorator)
    {
    	addDecorator.sandwich=this;
    	return addDecorator;
    }
 
    public Sandwich removeIngredient(SandwichDecorator removeDecorator,SandwichDecorator mainDecorator)
    {
    	if (this.getClass().getName().equals(removeDecorator.getClass().getName()))
    	{
    		if(previousIngredient!=null)
    		{
    			previousIngredient.sandwich=sandwich;
    			return mainDecorator;
    		}
    		else
    		{
    			return sandwich;
    		}
    			
    	}
    	else
    	{
    		if( sandwich instanceof VegSandwich)
    		{
    			System.out.println("Ingredient not found!");
    			return mainDecorator;
    		}
    		else
    		{    			
    			((SandwichDecorator)sandwich).previousIngredient=this;
        		return ((SandwichDecorator)sandwich).removeIngredient(removeDecorator,mainDecorator);	
    		}
    		    		
    	}
    }
}  

class ChieeseDecorator extends SandwichDecorator
{    
    public ChieeseDecorator(Sandwich sandwich) {  
        super(sandwich);  
    }  
    public String prepareFood(){
    	//4)Here Decorator adds functionality after the original object.
        return super.prepareFood() +" With extra cheese..";   
    }  
    public double foodPrice()   {  
        return super.foodPrice()+20.0;  
    }
	  
}
class RemoveChieeseDecorator extends SandwichDecorator
{    
    public RemoveChieeseDecorator(Sandwich sandwich) {  
        super(sandwich);  
    }  
    public String prepareFood(){  
        return super.prepareFood();   
    }  
    public double foodPrice()   {  
        return super.foodPrice();  
    }  
}  
class MayonnaiseDecorator extends SandwichDecorator
{  
    public MayonnaiseDecorator(Sandwich sandwich)    
    {  
       super(sandwich);  
    }  
	public String prepareFood()
	{  
	    return super.prepareFood() +" With little mayo..";   
	}  
	public double foodPrice()   
	{  
	    return super.foodPrice()+10.0;  
	}  
}  
class ToastDecorator extends SandwichDecorator
{  
    public ToastDecorator(Sandwich sandwich)    
    {  
       super(sandwich);  
    }  
	public String prepareFood()
	{  
		//4)Here Decorator adds functionality before the original object.
	    return "Toast "+super.prepareFood();   
	}  
	public double foodPrice()   
	{  
	    return super.foodPrice()+5;  
	}  
} 
class ButterDecorator extends SandwichDecorator
{  
    public ButterDecorator(Sandwich sandwich)    
    {  
       super(sandwich);  
    }  
	public String prepareFood()
	{  
		//4)Here Decorator adds functionality before the original object.
	    return super.prepareFood()+" having some butter on both sides of bread..";   
	}  
	public double foodPrice()   
	{  
	    return super.foodPrice()+5;  
	}  
} 
public class DecoratorDemo 
{
	static Sandwich sandwich=new VegSandwich(); //This is original object.
	 private static int  choice; 
	 public static void main(String args[]) throws NumberFormatException, IOException    
	 {  
	       do
	       {        
		        System.out.print("========Add your favorite ingredients/Recipes to Regular Sandwich Menu.============ \n");
		        System.out.print("            1. Cheese  \n");
		        System.out.print("            2. Mayoneese   \n");  
		        System.out.print("            3. Butter \n");  
		        System.out.print("            4. Toast Sandwich \n");
		        System.out.print("            5. Exit\n");
		        System.out.print("Note:add minus '-' sign to any ingredient above to remove it.\n");
		         
		        System.out.print("Enter your choice: ");  
		        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));   
		        choice=Integer.parseInt(br.readLine());  
		        switch (choice) 
		        {  
			      
			        case 1:
			        {   
			        	 //3)Critical requirement of Decorator pattern is that a decorated object ChineeseDecorator can stand in place of original object Sandwich i.e. it can be passed when a method expects original object.
			        	 sandwich=new ChieeseDecorator(sandwich);  
			              System.out.println(sandwich.prepareFood());  
			              System.out.println(sandwich.foodPrice());  
			        }  
			        break;  
		              
	                case 2:
	                {  
	                	sandwich=new MayonnaiseDecorator(sandwich);  
	                    System.out.println(sandwich.prepareFood());  
	                    System.out.println( sandwich.foodPrice());  
	                }  
		            break;    
	                case 3:
	                {  
	                	sandwich=new ButterDecorator(sandwich);  
	                    System.out.println(sandwich.prepareFood());  
	                    System.out.println(sandwich.foodPrice());  
		            }  
	                break;
	                case 4:
	                {  
	                	sandwich=new ToastDecorator(sandwich);  
	                    System.out.println(sandwich.prepareFood());  
	                    System.out.println(sandwich.foodPrice());  
		            } 
		            break;
	                case 5:
		            {    
		            	System.out.println("Thanks for visiting Sandwich Corner!");  
		            } 
		            break;
	                case -1:
			        {   
			        	 if(sandwich instanceof SandwichDecorator)
			        	 {
			        		 sandwich=((SandwichDecorator) sandwich).removeIngredient(new ChieeseDecorator(null),(SandwichDecorator) sandwich);
			        		 System.out.println(sandwich.prepareFood());  
			                 System.out.println(sandwich.foodPrice());  
			        	 }
			        	 else
			        	 {
			        		 System.out.println("Already all ingredients are removed!");
			        	 }
			        } 
			        break;
	                case -2:
			        {   
			        	 if(sandwich instanceof SandwichDecorator)
			        	 {
			        		 sandwich=((SandwichDecorator) sandwich).removeIngredient(new MayonnaiseDecorator(null),(SandwichDecorator) sandwich);							
			        		 System.out.println(sandwich.prepareFood());  
			                 System.out.println(sandwich.foodPrice());  
			        	 }
			        	 else
			        	 {
			        		 System.out.println("Already all ingredients are removed!");
			        	 }
			        } 
			        break;
	                case -3:
			        {   
			        	 if(sandwich instanceof SandwichDecorator)
			        	 {
			        		 sandwich=((SandwichDecorator) sandwich).removeIngredient(new ButterDecorator(null),(SandwichDecorator) sandwich);							
			        		 System.out.println(sandwich.prepareFood());  
			                 System.out.println(sandwich.foodPrice());  
			        	 }
			        	 else
			        	 {
			        		 System.out.println("Already all ingredients are removed!");
			        	 }
			        }
			        break;
	                case -4:
			        {   
			        	 if(sandwich instanceof SandwichDecorator)
			        	 {
			        		sandwich=((SandwichDecorator) sandwich).removeIngredient(new ToastDecorator(null),(SandwichDecorator) sandwich);							
			        		 System.out.println(sandwich.prepareFood());  
			                 System.out.println(sandwich.foodPrice());  
			        	 }
			        	 else
			        	 {
			        		 System.out.println("Already all ingredients are removed!");
			        	 }
			        } 
			        break;
	                default:
		            {    
		            	System.out.println("Sorry....Other Ingredients are not available!");
		            	break;
		            }  
		            
	                        		              
		        }//end of switch  
	          
	       }while(choice!=5);  
	    }  

}

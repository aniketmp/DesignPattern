package org.builder;

/*
 *Builder Pattern is used to encapsulate the construction of a product and allow it to be constructed in steps.
 
 -------Actors----
 *Builder 
-specifies an abstract interface for creating parts of a Product
object.

* Concrete builder
-constructs and assembles parts of the product by implementing the Builder interface
-defines and keeps track of the representation it creates.
-provides an interface for retrieving the product 

* Director (RTFReader)
- constructs an object using the Builder interface.

* Product 
- represents the complex object under construction. 
- ConcreteBuilder builds the product's internal representation and defines the process by which it's assembled.
- includes classes that define the constituent parts, including
  interfaces for assembling the parts into the final result.
  
   -----Process----
* The client creates the Director object and configures it with the desired Builder object.
* Director notifies the builder whenever a part of the product should be built.
* Builder handles requests from the director and adds parts to the product.
* The client retrieves the product from the builder.



Each ConcreteBuilder contains all the code to create and assemble a
particular kind of product. The code is written once; then different
Directors can reuse it to build Product variants from the same set of parts.


Best example of director is chef. Food builder gets the food for him/her. He makes the food. Food made by chef 1 can differs from chef 2.

 */
public class BuilderPatternDemo 
{

	public static void main(String[] args) {
		
		Director director=new Director(new ComputerBuilder("100GB", "8GB","Core 2d"));
		System.out.println(director.createComputer());
	}

}
class Director
{
	Builder builder;
	Director(Builder builder)
	{
		this.builder=builder;
	}
	public Computer createComputer()
	{
		builder.setBluetoothEnabled(true);
		return builder.build();
	}
}
class Computer 
{
	
	//required parameters
	private String HDD;
	private String RAM;
	private String processor;
	
	//optional parameters
	private boolean isGraphicsCardEnabled;
	private boolean isBluetoothEnabled;
	

	public String getHDD() {
		return HDD;
	}

	public String getRAM() {
		return RAM;
	}

	public boolean isGraphicsCardEnabled() {
		return isGraphicsCardEnabled;
	}

	public boolean isBluetoothEnabled() {
		return isBluetoothEnabled;
	}
	
	Computer(ComputerBuilder builder) {
		this.HDD=builder.HDD;
		this.RAM=builder.RAM;
		this.processor=builder.processor;
		this.isGraphicsCardEnabled=builder.isGraphicsCardEnabled;
		this.isBluetoothEnabled=builder.isBluetoothEnabled;
		
	}

	@Override
	public String toString() {
		String str=isGraphicsCardEnabled?" with Graphic Card":"";
			   str+=isGraphicsCardEnabled&&isBluetoothEnabled?" and":"";
			   str+=isBluetoothEnabled?"with Bluetooth Card":"";
		return "Computer with " + HDD + " hard disk , " + RAM + " RAM  having " + processor +" processor"+ str+ "";
	}
	
	
}
//Builder Class
interface Builder
{
	public void setHDD(String hDD);
	public void setRAM(String rAM) ;
	public void setProcessor(String processor) ;
	public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) ;
	public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) ;
	public Computer build();
}
class ComputerBuilder implements Builder
{

		// required parameters
		String HDD;
		String RAM;
		String processor;

		// optional parameters
		boolean isGraphicsCardEnabled;
		boolean isBluetoothEnabled;
		
		public ComputerBuilder(String hdd, String ram, String processor){
			this.HDD=hdd;
			this.RAM=ram;
			this.processor=processor;
		}

		public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
			this.isGraphicsCardEnabled = isGraphicsCardEnabled;
			return this;
		}

		public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
			this.isBluetoothEnabled = isBluetoothEnabled;
			return this;
		}
		
		
		@Override
		public void setHDD(String hDD) {
		this.HDD=hDD;
			
		}

		@Override
		public void setRAM(String rAM) {
			this.RAM=rAM;
			
		}

		@Override
		public void setProcessor(String processor) {
		this.processor=processor;
			
		}

		public Computer build(){
			return new Computer(this);
		}


	}
package org.visitor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 
 Visitor represent an operation to be performed on the elements of an object structure.
 Visitor lets you define a new operation without changing the classes of the elements
 on which it operates.
 
 
 *Step1:In this step, our aim is to print the Node of MyLinkedList class which represents Linked List.
  So it has printList() function which iterates over all the Nodes and calls their respective 
  print() method which each of its child has overridden.
  
  There is little problem in this. Tomorrow if we want to perform save() operation, then we have to make
  changes to 3 places.
  1)Need to add saveList() method in MyLinkedList class.
  2)Need to add save() method in abstract Node.
  3)Need to implement save() method in all of the child's of Node.
  
  This tight coupling creates complexity in design as the number of operations increases.
  And suppose if tomorrow if we add the Node say for e.g FourDNode then this FourDNode has to implement all
  the Node methods. Hence we have modified MyLinkList and child's of Node classes to try to make it decoupled.
  
   stage2:We have added saveList() method in MyLinkedList which gets all the values from the Nodes and 
   then save those values. Now each child Node return values which they want to save.
   
   The problem we were facing in stage 1 is now solved. So now if we want to perform any operation on the list then instead of adding it to more than 3 places ,now we just
   have to add the operation in the MyLinkedList which iterates over its list and get the values and perform operation on it. We can verify it by adding another function
   i.e squareList() which squares all the values in the node.
   
   Upto now everything is going fine.But suddenly new requirement comes in stage 3.....
   
   Suppose we want to perform operation based on their Type of Class for e.g the printList() method prints values of TwoDNode in 2 dimension form and 
   ThreeDNode in 3 dimension form then the printList() methods starts becoming complex. So tomorrow we want to save the Nodes 
   into different systems for eg. save TwoDNode in File system,ThreeDNode in database then again saveList() become more complex.Another thing to be noticed here
   that the loop we are using is inevitable in every method.So there is repeated code introduces in our class.
    
   Hence we have to seek another way.....
   
   stage 3:Visitor pattern to rescue..
   
   stage 4:Further improved version of Visitor pattern.
   
   with the help of visitor pattern , we can easily add new operation or modify existing operation 
   without recompiling existing code.
  
*/ 
class MyLinkedList implements /*stage 4*/ Visitable
{
	Node head;
	public MyLinkedList() {
		
		head=new TwoDNode(2, 4);
		Node n=head;		
		n.next=new ThreeDNode(5, 6, 7);
		n=n.next;		
		n.next=new ThreeDNode(9, 10, 11);
		n=n.next;
		n.next=new TwoDNode(13, 15);		
	}
	public void printList()
	{
		System.out.println("==========Printing the linked ist======");
		Node n=head;
		while(n!=null)
		{
			n.print();
			n=n.next;
		}
	}
	//stage 2
	public void saveList()
	{
		System.out.println("==========Saving the linked ist======");
		Node n=head;
		while(n!=null)
		{
			Map hm=n.getValues();
	        Iterator it = hm.entrySet().iterator();
	        while (it.hasNext()) {
	            Map.Entry<String,Integer> pair = (Map.Entry) it.next();
	            System.out.println(pair.getKey() + " = " + pair.getValue());	           
	        }
		
			n=n.next;
		}
		
	}
	//stage 2
	public void squareList()
	{
		System.out.println("==========Squaring the linked ist======");
		Node n=head;
		while(n!=null)
		{
			Map<String, Integer> hm=n.getValues();
	        Iterator it = hm.entrySet().iterator();
	        while (it.hasNext()) {
	            Map.Entry<String,Integer> pair = (Map.Entry) it.next();
	            System.out.println(pair.getKey() + " = " + pair.getValue()*pair.getValue());		           
	        }
		
			n=n.next;
		}
	}
	//stage 3
	public void acceptVisitor(Visitor visitor)
	{
		System.out.println("==========Registering visitors======");		
		Node n=head;
		while(n!=null)
		{
			n.acceptVisitor(visitor);
			n=n.next;
		}
	}
}
//stage 3
interface Visitable
{
	public void acceptVisitor(Visitor visitor);
}
//stage 3
interface Visitor
{
	public void visit(TwoDNode twoDNode);
	public void visit(ThreeDNode twoDNode);
	
	
}
abstract class Node /*stage 3 */ implements Visitable
{
	Node next;	

	abstract public void print() ;

	//stage 2
	abstract public Map<String,Integer> getValues() ;
}
class TwoDNode extends Node
{
	
	private int a;
	private int b;
	Map<String,Integer> hm=new LinkedHashMap<String,Integer>();
	
	public TwoDNode(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}
	public int getA() 
	{
		return a;
	}
	public int getB() 
	{
		return b;
	}
	@Override
	public void print() {
		
		System.out.println("TwoDNode [a=" + a + ", b=" + b + "]");
	}
	 //stage 2
	public Map<String,Integer> getValues() {
		if(hm.isEmpty())
		{
			hm.put("A",a);
			hm.put("B",b);
			return hm;
		}
		return hm;
	}
	@Override
	public String toString() {
		return "TwoDNode [a=" + a + ", b=" + b + "]";
	}
	//stage 3
	@Override
	public void acceptVisitor(Visitor v) {
		v.visit(this);
		
	}
	
	
}
class ThreeDNode extends Node
{
	private int x;
	private int y;
	private int z;
	Map<String,Integer> hm=new LinkedHashMap<String,Integer>(); //stage 2
	
	public ThreeDNode(int x, int y, int z) 
	{		
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public int getX() 
	{
		return x;
	}
	public int getY() 
	{
		return y;
	}
	public int getZ() 
	{
		return z;
	}
	
	@Override
	public void print() {
		System.out.println("ThreeDNode [x=" + x + ", y=" + y + ", z=" + z + "]");
		
	}
	 //stage 2
	public Map<String,Integer> getValues() {
		
		if(hm.isEmpty())
		{
			hm.put("X",x);
			hm.put("Y",y);
			hm.put("Z",z);
			return hm;
		}
		return hm;
	}
	@Override
	public String toString() {
		return "ThreeDNode [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	//stage 3
	@Override
	public void acceptVisitor(Visitor v) {
		v.visit(this);
		
	}
	
	
}
public class VisitorPatternDemo 
{
	
	public static void main(String[] args) 
	{
		MyLinkedList m=new MyLinkedList();
//		m.printList();
//		m.saveList();//stage 2
//		m.squareList();//stage 2
		
		m.acceptVisitor(new Print()); //stage 3:For printing operation
		m.acceptVisitor(new Save()); //stage 3:For printing operation
		
		addPrintFunctionilityToVisitable(m); //stage 4
		addSaveFunctionilityToVisitable(m);  //stage 4
	}
	//stage 4
	public static void addPrintFunctionilityToVisitable(Visitable visitable)
	{
		visitable.acceptVisitor(new Print());
	}
	//stage 4
	public static void addSaveFunctionilityToVisitable(Visitable visitable)
	{
		visitable.acceptVisitor(new Save());
	}
}
//stage 3
class Print implements Visitor
{

	@Override
	public void visit(TwoDNode twoDNode) {
		System.out.println("Printing in 2D fashion");
		
	}

	@Override
	public void visit(ThreeDNode twoDNode) {	
		System.out.println("Printing in 3D fashion");
	}

	
	
}
class Save implements Visitor
{

	@Override
	public void visit(TwoDNode twoDNode) {
		System.out.println("Saving in 2D Node in file systems");
		
	}

	@Override
	public void visit(ThreeDNode twoDNode) {	
		System.out.println("Saving in 3D Node in database");
	}

	
	
}
package org.visitor;
/*
 Visitor represent an operation to be performed on the elements of an object structure.
 Visitor lets you define a new operation without changing the classes of the elements
 on which it operates.
*/ 
class TwoDNode
{
	private int x;
	private int y;
	
	public TwoDNode(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() 
	{
		return x;
	}
	public int getY() 
	{
		return y;
	}
	
}
public class VisitorPatternDemo 
{
	

}

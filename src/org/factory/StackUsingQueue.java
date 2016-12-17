package org.factory;

public class StackUsingQueue implements Stack{
	public void push(int v)
	{
		System.out.println("Pushed "+v+" into StackUsingQueue");
	}
	public void pop()
	{
		System.out.println("popped from StackUsingQueue");
	}
}

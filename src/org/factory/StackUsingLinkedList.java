package org.factory;

public class StackUsingLinkedList implements Stack{

	public void push(int v)
	{
		System.out.println("Pushed "+v+" into StackUsingLinkedList");
	}
	public void pop()
	{
		System.out.println("Popped from StackUsingLinkedList");
	}
}

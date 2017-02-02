package org.iterator;

import java.util.ArrayList;
import java.util.List;

//Iterator provides a way to access the elements of an aggregate object without exposing its underlying representation.

//Iterator interface. Here Remote Control acts as an iterator.
interface RemoteControl
{

	public String nextChannel();
	public String prevChannel();
	public boolean hasNext();
	public boolean hasPrev();
}
//Aggregate interface 
interface TV
{
	public RemoteControl getIterator();//other TV methods
}

//Concrete Aggregator
class ConcreteTV implements TV
{
	private RemoteControl iterator; 
	private List<String> channels; 
	public ConcreteTV()
	{
		channels=new ArrayList<String>();
		channels.add("Discovery");
		channels.add("BBC news");
		channels.add("Zee TV");
		iterator = new ConcreteChannelIterator(channels);
	}
	public RemoteControl getIterator()
	{
		return iterator;
	}
}

//Concrete Iterator  
class ConcreteChannelIterator implements RemoteControl
{
	private List<String> channels; 
	private int currentPos = 0; 
	public ConcreteChannelIterator(List<String> channels)
	{
		this.channels = channels;
	}
	public boolean hasNext()
	{
		if(currentPos < channels.size())
		{
			return true;
		}
		return false;
	}
	public String nextChannel()
	{		
		return channels.get(currentPos++);
	}
	@Override
	public boolean hasPrev() {
		if(currentPos>0)
		{
			return true;
		}
		return false;
	}
	public String prevChannel()
	{		
		return channels.get(--currentPos);
	}
	
	
	
}
public class IteratorDemo 
{

	public static void main(String[] args) 
	{
		TV tv=new ConcreteTV();
		//Here I am not asking TV to give its channels instead I am asking TV to only give your Iterator so that We can Iterate over its channels.
		RemoteControl itr=tv.getIterator();
		System.out.println("==========Moving channels forward============");
		while(itr.hasNext())
		{
			System.out.println(itr.nextChannel());
		}
		System.out.println("==========Moving channels backward============");
		while(itr.hasPrev())
		{
			System.out.println(itr.prevChannel());
		}
	}

}
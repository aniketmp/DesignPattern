package org.adapter;

import java.util.Enumeration;
import java.util.Iterator;


//Adapter Convert the interface of a class into another interface clients expect. Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.
///An adapter helps two incompatible interfaces to work together
interface Enumerable
{
	public Enumeration<String> element();
}
class MyEnumerator implements Enumeration<String>
{
	private int ptr;
	private String[] countries;
	MyEnumerator(String[] countries)
	{
		this.countries=countries;
	}
	@Override
	public boolean hasMoreElements() {		
		if(ptr<countries.length-1)
			return true;
		return false;
	}

	@Override
	public String nextElement() {			
		return countries[ptr++];
	}
	
}
class MyIterator implements Iterator<String>
{
	private String[] countries;
	private int ptr;
	MyIterator(String[] countries)
	{
		this.countries=countries;
	}
	
	@Override
	public boolean hasNext() {
		if(ptr<countries.length-1)
			return true;
		return false;
	}

	@Override
	public String next() {
		return countries[ptr++];
	}

	@Override
	public void remove() {
		System.out.println("Remove operation is not supported yet!");
		
	}
	
	
}
//This class acts as IteratorAdapter.Its a "wrapper" class that can "impedance match" the adaptee to the client.
//It implements interface client expects and it also has legacy interface
class IteratorAdapter implements Iterator<String>
{
	private String[] countries;
	Enumeration<String> e;

	IteratorAdapter(String[] countries)
	{
		this.countries=countries;
		e=new MyEnumerator(countries);
	}
	@Override
	public boolean hasNext() 
	{
		return e.hasMoreElements();
	}
	
	@Override
	public String next() {
		return e.nextElement();
	}

	@Override
	public void remove() {
		System.out.println("Remove operation is not supported yet!");
		
	}
	
	
}
//CountryList is a service client is using.
class CountryList implements Enumerable, Iterable<String>
{
	private String[] countries=new String[]{"India","Nepal","China","US","Brazil","Africa","UK","Germeny"};	
	//Enumeration is an interface which client is expecting.There is method element() which gives Enumeration interface.
	
	
	//stage 1
	@Override
	public Enumeration<String> element() {	
		return new MyEnumerator(countries);
	}
	@Override
	public Iterator<String> iterator() {
		//stage 2
		//return new MyIterator(countries);
		//stage 3
		return new IteratorAdapter(countries);

	}
	
}
public class AdapterPatternDemo {

	
	public static void main(String[] args) {
		CountryList countryList=new CountryList();
		
		//Stage1:
		//Client1(Old client) is using long way back the Enumeration interface because he/she knows how to use it to iterate over its underlying list.
		OldClient c1=new OldClient(countryList);
		c1.useIt();
		
		//Stage2:
		//Client2(New Client) is expecting the Iterator interface because he/she knows how to use it to iterate over its underlying list.
		//Hence in stage 1 we have created Iterator interface which does almost the same thing as Enumeration.
		NewClient c2=new NewClient(countryList);
		//c2.useIt();
		
		//Stage3:
		// Instead of this we could use 'ADAPTER' which can be seen in stage 2
		//fulfill clients requirement and also eliminates code duplicate.
		c2.useIt();
		
	}

}
class OldClient
{
	private CountryList list;	
	OldClient(CountryList list)
	{
		this.list=list;
	}
	public void useIt()
	{
		Enumeration<String> e=list.element();
		System.out.println("==============Iterating using Enumeration==================");
		while(e.hasMoreElements())
		{
			System.out.println(e.nextElement());
		}
	}
	
}
class NewClient
{
	private CountryList list;	
	NewClient(CountryList list)
	{
		this.list=list;
	}
	public void useIt()
	{
		Iterator<String> e=list.iterator();
		if(e instanceof IteratorAdapter)
		{
			System.out.println("==============Iterating using IteratorAdapter==================");
		}
		else
		{
			System.out.println("==============Iterating using Iterator==================");
		}
			
		while(e.hasNext())
		{
			System.out.println(e.next());
		}
	}
}


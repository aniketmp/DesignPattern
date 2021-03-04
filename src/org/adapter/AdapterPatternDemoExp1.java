package org.adapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class AdapterPatternDemoExp1 {

	public static void main(String[] args) {
		List<String> features=Arrays.asList("NEFT ","RTGS","Phone Banking","Credit Card","UPI","FasTag");
		//Vendor Bank has two different versions for their two client backed by the same feature list.
		//Old version supports old client with enumeration for traversing.
		//New version supports new client with iterator for traversing.
		VendorBankFeatureListEnumeratedVersion oldVersion=new VendorBankFeatureListEnumeratedVersion(features);
		VendorBankFeatureListIteratorVersion newVersion=new VendorBankFeatureListIteratorVersion(features);
		
		OldEnumeratorClient c1=new OldEnumeratorClient(oldVersion);
		c1.useIt();
		
		NewIteratorClient c2=new NewIteratorClient(newVersion);
		c2.useIt();
		
		
		//Is it possible to introduce Iterator to old client without affecting its code?
		// OldEnumeratorClient c1=new OldEnumeratorClient(newVersion); //You cannot do this..
		//But its possible Using IteratorAdapter
		VendorBankFeatureListIteratorAdapter adapterVersion=new VendorBankFeatureListIteratorAdapter(features);
		c1=new OldEnumeratorClient(adapterVersion);
		c1.useIt();
	}
}

class VendorBankFeatureListEnumeratedVersion  implements Enumeration<String>
{
	final Enumeration<String> enumeration;
	VendorBankFeatureListEnumeratedVersion(List<String> features)
	{
		enumeration=Collections.enumeration(features);
	}

	public boolean hasMoreElements() {
		return enumeration.hasMoreElements();
	}

	@Override
	public String nextElement() {
		return enumeration.nextElement();
	}
	
	public Enumeration<String> elements()
	{
		return this;
	}

	
}


class VendorBankFeatureListIteratorVersion  implements Iterator<String>
{
	final Iterator<String> iterator;
	VendorBankFeatureListIteratorVersion(List<String> features)
	{
		iterator=features.iterator();
	}

	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public String next() {
		return iterator.next();
	}
	
	public Iterator<String> elements()
	{
		return this;
	}

	
}


class OldEnumeratorClient
{
	private Enumeration<String> list;	
	OldEnumeratorClient(Enumeration<String> list)
	{
		this.list=list;
	}
	public void useIt()
	{
		System.out.println("============== Old client iterating using Enumeration ==================");
		while(list.hasMoreElements())
		{
			System.out.println(list.nextElement());
		}
	}
	
}

class NewIteratorClient
{
	private Iterator<String> list;	
	NewIteratorClient(Iterator<String> list)
	{
		this.list=list;
	}
	public void useIt()
	{
		System.out.println("============== New client iterating using Iterator ==================");
		while(list.hasNext())
		{
			System.out.println(list.next());
		}
	}
}










class VendorBankFeatureListIteratorAdapter  implements Enumeration<String>
{
	final Iterator<String> iterator;
	VendorBankFeatureListIteratorAdapter(List<String> features)
	{
		iterator=features.iterator();
	}

	public boolean hasMoreElements() {
		return iterator.hasNext();
	}

	@Override
	public String nextElement() {
		return iterator.next();
	}
	
	public Enumeration<String> elements()
	{
		return this;
	}

	
}









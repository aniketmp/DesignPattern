package org.observer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
   Define a one-to-many dependency between objects so that when one object changes
   state, all its dependents are notified and updated automatically.
   
   Publisher + Subscriber = Observer Pattern
   Here Publisher is subject which changes its state and Subscriber is Observer which 
   gets notified when subject changes.
    
   *When the two objects are loosely coupled, they can interact, but have very little knowledge 
    about each other
   *The Observer Pattern provides an object design where the subject and observers are loosely coupled.
   *Hence we can add new observer.We can remove existing added observer. We can also replace any 
    observer runtime.  
    
    Let's design Google Whether Broadcaster which senses the temperature,humidity,pressure and then
    broadcast those to who so ever News channel is interested in.   
 */
interface Subject
{
	public void registeredObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
interface Observer
{
	public void update(int temp,int humidity,int pressure);
}
//Here Google Whether Broadcaster is Subject.
class GoogleWhetherBroadcaster implements Subject
{
	int temp,humidity,pressure;
	List<Observer> observers=new ArrayList<>();
	@Override
	public void registeredObserver(Observer o) 
	{		
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) 
	{
		observers.remove(o);
	}

	@Override
	public void notifyObservers() 
	{
		System.out.println("");
		System.out.println("==============================Broadcasting whether info on:"+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+"=========================");
		System.out.println("");
		for(Observer o:observers)
		{			
			o.update(temp, humidity, pressure);			
		}
	}
	public void setWhetherCondition(int temp,int humidity,int pressure)
	{
		this.temp=temp;
		this.humidity=humidity;
		this.pressure=pressure;		
		measurementChanged();
	}
	public void measurementChanged()
	{
		notifyObservers();
	}
}
//Different channels are Observers
class BBCWhetherReport implements Observer
{

	@Override
	public void update(int temp, int humidity, int pressure) {	
		System.out.println("BBC Whether News Channel reporing  temp:"+temp+"  humidity:"+humidity+"  pressure:"+pressure);
	}
	
}
class NationalWhetherService implements Observer
{

	@Override
	public void update(int temp, int humidity, int pressure) {	
		System.out.println("National whether service providing info...  temp:"+temp+"  humidity:"+humidity+"  pressure:"+pressure);
	}
	
}
class ZeeNewsWhetherForecast implements Observer
{

	@Override
	public void update(int temp, int humidity, int pressure) {	
		System.out.println("ZeeNews Whether Forecast Station forecasting  temp:"+temp+"  humidity:"+humidity+"  pressure:"+pressure);
	}
	
}
public class ObserverPatternDemo 
{
	public static void main(String[] args) throws InterruptedException {		
		Subject whetherBroadcaster=new GoogleWhetherBroadcaster();
		
		Observer bbcWhetherReport=new BBCWhetherReport();
		Observer nationalWhetherService=new NationalWhetherService();
		Observer zeeNewsWhetherForecast=new ZeeNewsWhetherForecast();
		
		whetherBroadcaster.registeredObserver(bbcWhetherReport);
		whetherBroadcaster.registeredObserver(nationalWhetherService);
		whetherBroadcaster.registeredObserver(zeeNewsWhetherForecast);
		
		((GoogleWhetherBroadcaster)whetherBroadcaster).setWhetherCondition(42, 62, 34);
		Thread.currentThread().sleep(3000);
		((GoogleWhetherBroadcaster)whetherBroadcaster).setWhetherCondition(32, 58, 31);
		Thread.currentThread().sleep(3000);
		((GoogleWhetherBroadcaster)whetherBroadcaster).setWhetherCondition(45, 66, 44);
		
		
	}
}

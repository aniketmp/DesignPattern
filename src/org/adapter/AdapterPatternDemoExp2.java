package org.adapter;

public class AdapterPatternDemoExp2 {

	public static void main(String[] args) {
		
		//In our company we have share monitoring system where the input shares feed is coming from 
		//XML based SharePriceFeed then we display it onto StandardChartDislplay UI which displays shares prices in charts.
		SharePriceFeed xmlFeed=new SharePriceFeed();
		StandardChartDislplay oldDisplay=new StandardChartDislplay(xmlFeed);
		oldDisplay.display();
		//Suppose we bought new advance display unit AdvanceGraphicDisplay which displays the 
		//information in various chats and graphs.But the problem is it takes JSON as a input.
		//AdvanceGraphDislplay newDisplay=new StandardChartDislplay(feed); //We cannot pass same feed since its in XML.
		//Hence we need JsonAdapter...
		JsonAdapter jsonFeed=new JsonAdapter(xmlFeed);
		AdvanceGraphDislplay advDisplay=new AdvanceGraphDislplay(jsonFeed);
		advDisplay.display();
		
	}
	
}

interface XmlDataSource
{
	public String getXmlData();
}
class SharePriceFeed implements XmlDataSource
{

	@Override
	public String getXmlData() {
		return "<Shares>..</Shares>";
	}
	
}

class StandardChartDislplay
{
	XmlDataSource dataSource;
	StandardChartDislplay(XmlDataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	public void display()
	{
		//process XML data and display
		System.out.println("Displaying charts from XML");
		System.out.println(dataSource.getXmlData());
	}
}

interface JsonDataSource
{
	public String getJsonData();
}




class AdvanceGraphDislplay
{
	JsonDataSource dataSource;
	AdvanceGraphDislplay(JsonDataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	public void display()
	{
		//process XML data and display
		System.out.println("Displaying various charts and graphs from JSON");
		System.out.println(dataSource.getJsonData());
	}
}


class JsonAdapter implements JsonDataSource //It extends JsonDataSource and has XmlDataSource
{

	XmlDataSource dataSource;
	JsonAdapter(XmlDataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	
	@Override
	public String getJsonData() {
		return convertXmlToJson(dataSource.getXmlData());
	}

	private String convertXmlToJson(String xmlData) {
		System.out.println("Converting XML to JSON");
		return "{'shares':'..'}";
	}
	
}
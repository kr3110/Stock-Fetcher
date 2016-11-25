/** 
 * @author kr3110
 * 
 * This java class will retrieve different Stock 
 * information using Yahoo CSV finance API
 * 
 * for more info about data codes go to:
 * http://wern-ancheta.com/blog/2015/04/05/getting-started-with-the-yahoo-finance-api/
 * or
 * http://meumobi.github.io/stocks%20apis/2016/03/13/get-realtime-stock-quotes-yahoo-finance-api.html
 * 
 */



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



public class StockinfoYahoo {
	
			
	private final String YahooURL = "http://finance.yahoo.com/d/quotes.csv?s=";
	private final String queryParam = "&f=";
	Scanner input=new Scanner(System.in);
	
	private final HashMap<String,String> pricing= new HashMap<String, String>(){
		{
			put("a","ask");
			put("b","bid");
			put("b2","realtime ask");
			put("b3","realtime bid");
			put("p","previous close");
			put("o","open");
		}
	};
	private final HashMap<String,String> dividends= new HashMap<String, String>(){
		{
			put("y","dividend yield");
			put("d","dividend per share");
			put("r1","dividend pay date");
			put("q","ex-dividend date");
		}
	};	
	private final HashMap<String,String> date= new HashMap<String, String>(){
		{
			put("c1","change");
			put("c","change & percentage change");
			put("c6","change (realtime)");
			put("k2","change percent");
			put("p2","change in percent");
			put("d1","last trade date");
			put("d2","trade date");
			put("t1","last trade time");
		}
	};	
	
	//52 Week Pricing
	private final HashMap<String,String> _52weekpricing= new HashMap<String, String>(){
		{

			put("k","52 week high");
			put("j","52 week low");
			put("j5","change from 52 week low");
			put("k4","change from 52 week high");
			put("j6","percent change from 52 week low");
			put("k5","percent change from 52 week high");
			put("w","52 week range");
		}
	};	
	

			
	
	
	
	
	public StockinfoYahoo()
	{
		System.out.println("Enter your Stock Symbol:");
		String stock=input.nextLine();
		System.out.println("1)Single data query");
		System.out.println("2)Multiple data query");
		int x=input.nextInt();
		if(x==2)
		{
			System.out.println("Working on it");
		}
		else
		dataDisplay(getData(UrlConstructor(stock)));
	}
	private URL UrlConstructor(String stock )
	{
		String url= YahooURL+stock+queryParam;
		url+=choice();
		URL URL=null;
		try{
			URL = new URL(url);
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}
		return URL;
	}
	
	private String choice()
	{
		String tag="";
		System.out.println("Select from which category");
		System.out.println("1)Pricing");
		System.out.println("2)dividends");
		System.out.println("3)52 week pricing");
		System.out.println("4)Date");
		int choice=input.nextInt();
		System.out.println("Select your choice");
		
		if(choice==1)
		{
			Set set=pricing.entrySet();
			Iterator i=set.iterator();
			while(i.hasNext())
			{
				  Map.Entry me = (Map.Entry)i.next();
			       System.out.print(me.getKey() + ") ");
			       System.out.println(me.getValue());
			}
		}
		else if(choice==2)
		{
			Set set=dividends.entrySet();
			Iterator i=set.iterator();
			while(i.hasNext())
			{
				 Map.Entry me = (Map.Entry)i.next();
			       System.out.print(me.getKey() + ") ");
			       System.out.println(me.getValue());
			}
		}
		else if(choice==3)
		{
			Set set=_52weekpricing.entrySet();
			Iterator i=set.iterator();
			while(i.hasNext())
			{
				 Map.Entry me = (Map.Entry)i.next();
			       System.out.print(me.getKey() + ") ");
			       System.out.println(me.getValue());
			}
		}
		else
		{
			Set set=date.entrySet();
			Iterator i=set.iterator();
			while(i.hasNext())
			{
				 Map.Entry me = (Map.Entry)i.next();
			     System.out.print(me.getKey() + ") ");
			     System.out.println(me.getValue());
			}
		}
		tag=input.next();
		return tag;
	}
	private void dataDisplay(String data)
	{
		System.out.println(data);
	}
	

	private String getData(URL url)
	{
		InputStream response=null;
		String data=null;
		BufferedReader reader;
		try
		{
			String line;
			response=url.openStream();
			reader= new BufferedReader(new InputStreamReader(response));
			while((line=reader.readLine())!=null)
			{
				if(data==null)
					data=line+"\n";
				else
					data+=line+"\n";
			}
			reader.close();
		}
		catch(Exception e)
		{
			System.out.println("Connection problems.");
		}
		
		
		return data;
	}
}

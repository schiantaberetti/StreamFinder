package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.htmlparser.NodeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;

import view.Gui;

public class SearchEngine {
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.Gui inst = Gui.getGui();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
		
		//printCollectors(streamSearch("v+per+vendetta+streaming+ita"));
		//view.Window w = new view.Window();
	}
	public static void printInfo(String s){
		Gui.getGui().setStatus("STATUS: "+s);
		Gui.getGui().repaint();
		System.out.println(s);
	}
	public static ArrayList<model.LinkCollector> streamSearch(String key){
		key=key.replace(" ", "+").trim().toLowerCase();
		printInfo("googling...");
		String page = getWebPage("http://www.google.com/search?q=streaming+"+key);
		printInfo("retrieving relevant links..");
		//System.out.println(page);
		ArrayList<model.Link> links_sities = getHTTPLinks(page);
		printInfo("processing collected data..");
		ArrayList<model.LinkCollector> collectors = buildLinksCollectors(links_sities);
		printInfo("deleting useless data..");
		collectors = linksPruner(collectors);
		
		printCollectors(collectors);
		printInfo("search completed.");
		return collectors;
	}
	public static void printCollectors(ArrayList<model.LinkCollector> collectors){ 
		/* Text function
		for(model.LinkCollector coll : collectors){
			System.out.println(coll.getName());
			for(model.Link l:coll.getLinks()){
				System.out.println("\t"+l.getName()+"\t"+l.getUrl());
			}
		}*/
		String[] sites = new String[collectors.size()];
		int i=0;
		for(model.LinkCollector coll : collectors){
			sites[i]=coll.getName();
			i++;
		}
		Gui.getGui().setSites(sites);
		Gui.getGui().repaint();
		Gui.getGui().setCollectors(collectors);
	}
	private static ArrayList<model.Link> testHost(ArrayList<model.Link> links, ArrayList<model.Link> hosts){
		/*Returns only the links with the specified hosts*/
		ArrayList<model.Link> c = new ArrayList<model.Link>();
		for(model.Link l1 : links)
			for(model.Link l2 : hosts)
				if(l1.getUrl().contains(l2.getUrl()))
					c.add(l1);
		return c;
	}
	public static ArrayList<model.LinkCollector> linksPruner(ArrayList<model.LinkCollector> collectors){
		ArrayList<model.LinkCollector> result = new ArrayList<model.LinkCollector>();
		ArrayList<model.Link> allowed = XMLStuff.parseTrustedLinks();
		printInfo("retrieved "+allowed.size()+" allowed sites.");
		for (model.LinkCollector coll: collectors){
			coll.setLinks(testHost(coll.getLinks(), allowed));
			if(coll.getLinks().size()>0)
				result.add(coll);
		}
		return result;
	}

	public static ArrayList<model.LinkCollector> buildLinksCollectors(ArrayList<model.Link> links){
		/*for each link in input create a structure collector of the links inside the page addressed*/
		ArrayList<model.LinkCollector> collectors=new ArrayList<model.LinkCollector>();
		model.LinkCollector neo;
		for(model.Link l:links){
			printInfo("surfing "+l.getName());
			neo = new model.LinkCollector(l.getUrl());
			neo.setLinks(getHTTPLinks(getWebPage(l.getUrl())));
			collectors.add(neo);
		}
		return collectors;
	}
	public static ArrayList<model.Link> getHTTPLinks(String page){
		/*Returns the HTTP links in an HTTP page (contained in one String) excluding those which points to google*/
		ArrayList<model.Link> links = new ArrayList<model.Link>();
		model.Link neo;
		org.htmlparser.Parser parser;
        NodeFilter  filter= new NodeClassFilter (LinkTag.class);
        org.htmlparser.util.NodeList list;
		
		try{
			parser = new org.htmlparser.Parser(page);
            list = parser.extractAllNodesThatMatch (filter);
            for (int i = 0; i < list.size (); i++)
            	
            		{
            				
            				String[] items=list.elementAt (i).toHtml().split("href=");
            				if(items.length>1){
            					String[] pieces=items[1].split("\"");
            					if(pieces.length>1){
            						String addr=pieces[1];
            						if((!addr.contains("google"))&&(addr.startsWith("http"))){
            			        	 //System.out.println (list.elementAt (i).toPlainTextString());
            			        	 //System.out.println(addr);
            							neo = new model.Link(list.elementAt (i).toPlainTextString(),addr);
            							links.add(neo);
            						}
            					}
            				}
            			}
		}catch(Exception e){e.printStackTrace();}
		
		return links;
	}
	public  static String getWebPage(String urlo){
		/*Given an HTTP url (e.g. "http://www.google.it", returns the page in one string
		 */
		 //ArrayList<String> page = new ArrayList<String>();
		String page = "";
		try { 
           URL url = new URL(urlo);
           URLConnection conn = url.openConnection();
           conn.setRequestProperty("User-Agent", "Firefox/3.0.15");
           BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           String line = br.readLine();
           while(line!=null){
           	//page.add(line);
           	//System.out.println(line);
           	page = page+line;
        	line = br.readLine();
           }
           
           //url.getContent();
           //Thread.sleep(3000);
       } catch (MalformedURLException me) { 
           System.out.println(me); 
   
       } catch (Exception ioe) { 
           System.out.println(ioe); 
       }
       return page;
	}
}

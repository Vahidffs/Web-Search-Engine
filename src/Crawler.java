package mypackage;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Scanner;


public class Crawler 
{
    private static final int MAX_DEPTH = 2;
    private HashSet<String> links;
    private static int file_name = 0;

    public Crawler()
    {
        links = new HashSet<>();
    }

    public void getPageLinks(String URL, int depth) throws IOException 
    {
    	
    	
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
           // fw.write(">> Depth: " + depth + " [" + URL + "]");
        	//System.out.println(">> Depth: " + depth + " [" + URL + "]");
        	
        	
    		
        	try 
        	{
        		Document doc = Jsoup.connect(URL).get();
        		String content = doc.text();
        		
        		
        		BufferedWriter writer = new BufferedWriter(new FileWriter("/tmp/" + Integer.toString(file_name++)));
            	
                writer.write(content);
                 
                writer.close();
        		
        	}
        	catch ( Exception ex ) 
        	{
        	    ex.printStackTrace();
        	}
        	
        	
            
            try {
            	
            	
            	
                links.add(URL);
                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");
                

                depth++;
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"), depth);
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }
}



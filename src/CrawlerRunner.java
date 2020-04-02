package mypackage;

import java.io.IOException;


public class Runner {

	public static void main(String[] args) throws IOException 
	{

		Crawler clw = new Crawler();
		clw.getPageLinks("https://www.imdb.com/", 0);
	}

}

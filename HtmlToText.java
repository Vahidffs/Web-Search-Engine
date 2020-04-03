
import java.io.*;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import textprocessing.*;

/**
 * This Class converts the accesses the various HTML web-pages and converts them
 * to corresponding text files. These files are used for searching and page
 * ranking.
 */
public class HtmlToText {

    public void convertToText(String fileDirectory) {

        ArrayList<String> urlList = new ArrayList<>();

        // Use URL of local directory while running on local computer
        // In in = new In(
        // "/home/najeeba/eclipse-workspace/ACC-Web-Search-Engine-master/src/accwebsearchengine/websites.txt");
        In in = new In(fileDirectory);

        while (!in.isEmpty()) {

            String myText = in.readLine();
            urlList.add(myText);

        }

        for (int i = 0; i < urlList.size(); i++) {

            try {

                org.jsoup.nodes.Document doc = Jsoup.connect(urlList.get(i)).get();
                String text = doc.text();
                String filepath = System.getProperty("user.dir") + "/resources/html_text/" + (i) + ".txt";
                PrintWriter out = new PrintWriter(filepath);
                out.println(urlList.get(i));
                out.println(text);
                out.close();

            } catch (Exception e) {

                System.out.println("Exception, cannot be converted to text: " + urlList.get(i));
            }

        }
    }

}

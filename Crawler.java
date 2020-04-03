import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Crawler {

    private static final int MAX_DEPTH = 2;
    private ArrayList<String> links;

    public Crawler() {
        links = new ArrayList<String>();
    }

    public void getPageLinks(String URL, int depth) throws IOException {

        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
            
            try {

                links.add(URL);
                Document doc = Jsoup.connect(URL).get();
                Elements linksOnPage = doc.select("a[href]");
                depth++;

                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"), depth);
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }

    public void storeWebLinks(String filepath) {

        try {
            File output = new File(filepath);
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));

            for (String s : links) {
                bw.write(s + "\n");
            }

            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
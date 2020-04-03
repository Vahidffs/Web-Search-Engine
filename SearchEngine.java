import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SearchEngine {

    public static void main(String[] args) {

        String directory = System.getProperty("user.dir");

        if (!new File(directory + "/resources").exists()) {
            System.out.println("\'resources\' directory not found, creating new directory.");
            File file = new File(directory + "/resources");
            if (file.mkdir()) {
                System.out.println("\'resources\'Directory created successfully.");
                if (!new File(directory + "/resources/html_text").exists()) {
                    file = new File(directory + "/resources/html_text");
                    file.mkdir();
                }
            }
        }

        String searchString = "Masters of Applied Computing";

        Crawler crwl = new Crawler();
        try {
            System.out.println("Crawling the web");
            crwl.getPageLinks("http://www.uwindsor.ca", 0);
            crwl.storeWebLinks(directory + "/resources/websites.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        HtmlToText ht = new HtmlToText();
        ht.convertToText(directory + "/resources/websites.txt");

        GetKeywords key = new GetKeywords();
        String[] keywords = key.getKeywords(searchString, directory + "/stop_words.txt");
        System.out.println();

        WordFrequency wf = new WordFrequency();

        HashMap<Integer, String> urlIndex = new HashMap<Integer, String>();
        urlIndex = wf.indexURLS();

        HashMap<Integer, Integer> freqList = new HashMap<Integer, Integer>();
        freqList = wf.getFreqList(keywords);

        RankWebPages rw = new RankWebPages();
        freqList = rw.sortHashMap(freqList);

        System.out.println("Top Ten Search Results for \"" + searchString + "\" are:\n");
        int j = 0;

        for (HashMap.Entry<Integer, Integer> entry : freqList.entrySet()) {

            if (j < 10 && entry.getValue() == 0) {
                System.out.println("Could not find any instances of keywords in: \n" + urlIndex.get(entry.getKey()));
                break;
            }

            if (j < 10) {

                int urlKey = entry.getKey();
                System.out.println("Found " + entry.getValue() + " instances of keywords in: ");
                System.out.println(urlIndex.get(urlKey) + "\n");
                j++;

            } else {

                break;
            }

        }
    }
}

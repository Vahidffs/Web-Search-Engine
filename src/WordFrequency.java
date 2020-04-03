import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import textprocessing.In;
import textprocessing.TST;

public class WordFrequency {

    /**
     * This methods is responsible for indexing URLs by fetching URLs from file and
     * inserting each URL into Hashmap.
     * 
     * @return UrlIndex
     */
    public HashMap<Integer, String> indexURLS() {

        int i = 0;
        HashMap<Integer, String> UrlIndex = new HashMap<Integer, String>();
        In in = new In(System.getProperty("user.dir") + "/resources/websites.txt");

        while (!in.isEmpty()) {

            String text = in.readLine();
            UrlIndex.put(i, text);
            i++;
        }

        return UrlIndex;
    }

    /**
     * This method is responsible for creating TST of each text file.
     * 
     * @param finalPath
     * @return tst
     */
    public TST<Integer> makeTST(String finalPath) {
        int j = 0;
        TST<Integer> tst = new TST<Integer>();
        In in = new In(finalPath);

        while (!in.isEmpty()) {
            String text = in.readLine();
            if (j == 0) {

                j = 1;
                continue;

            } else if (j == 1) {
                j = 0;

                StringTokenizer st = new StringTokenizer(text, " ");
                while (st.hasMoreTokens()) {

                    String word = st.nextToken();
                    word = word.toLowerCase();
                    // System.out.println(word);

                    if (tst.contains(word)) {

                        tst.put(word, tst.get(word) + 1);

                    } else {

                        tst.put(word, 1);
                    }
                }
            }
        }

        return tst;
    }

    /**
     * This method is responsible to find the the occurrence of the keywords in each
     * text file and get the count. This word count is used to rank the web-pages.
     * 
     * @param keyWords
     * @return freqList
     */
    public HashMap<Integer, Integer> getFreqList(String[] keyWords) {

        // Map each text file to its corresponding number into an arraylist
        ArrayList<String> textList = new ArrayList<>();
        HashMap<Integer, Integer> freqList = new HashMap<Integer, Integer>();

        File folder = new File(System.getProperty("user.dir") + "/resources/html_text/");
        File[] files = folder.listFiles();

        for (File file : files) {

            String myURL = file.getName();
            textList.add(myURL);

        }

        for (int i = 0; i < textList.size(); i++) {

            String filePath = System.getProperty("user.dir") + "/resources/html_text/";
            String fileName = textList.get(i);
            String finalPath = filePath + fileName;

            String tempFileIndex = fileName.substring(0, (fileName.length() - 4));
            int fileIndex = Integer.parseInt(tempFileIndex);

            TST<Integer> tst = new TST<Integer>();
            tst = makeTST(finalPath);

            int counter = 0;

            for (String str : keyWords) {
                if (tst.contains(str)) {

                    int count = tst.get(str);
                    counter = counter + count;
                }
            }

            freqList.put(fileIndex, counter);
        }

        return freqList;
    }

}

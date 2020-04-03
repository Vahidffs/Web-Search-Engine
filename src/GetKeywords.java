import java.util.StringTokenizer;

import sorting.Sort;
import textprocessing.In;

public class GetKeywords {

    /**
     * This method is responsible for removing stop words from the input keywords
     * 
     * @param inputStr
     * @return keyWords
     */
    public String[] getKeywords(String inputStr, String stopWordsDir) {
        
        int i = 0;
//        In in = new In(
//                "/home/najeeba/eclipse-workspace/ACC-Web-Search-Engine-master/src/accwebsearchengine/stop-words.txt");
        In in  = new In(stopWordsDir);
        inputStr = inputStr.toLowerCase();

        while (!in.isEmpty()) {

            String text = in.readLine();
            text = text.toLowerCase();
            text = "\\b" + text + "\\b";
            inputStr = inputStr.replaceAll(text, "");
        }

        StringTokenizer st = new StringTokenizer(inputStr, " ");
        String[] keywords = new String[st.countTokens()];

        while (st.hasMoreTokens()) {
            keywords[i] = st.nextToken();
            i++;
        }

        Sort.mergeSort(keywords);
        return keywords;
    }

}

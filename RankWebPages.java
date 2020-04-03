import static java.util.stream.Collectors.toMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RankWebPages {

    /**
     * This method is responsible to sort hashmap in descending order of the values.
     * This order is the ranking of the web-pages.
     * 
     * @param freqList
     * @return sortedFreqList
     */
    public HashMap<Integer, Integer> sortHashMap(HashMap<Integer, Integer> freqList) {
        HashMap<Integer, Integer> sortedFreqList = freqList.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

        return sortedFreqList;
    }

}

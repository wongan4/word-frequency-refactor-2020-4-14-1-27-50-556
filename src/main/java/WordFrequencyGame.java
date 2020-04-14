import java.util.*;

public class WordFrequencyGame {
    private static final String DELIMITER_FORMAT = "\\s+";


    public String getResult(String inputSentence) {
        try {

            //split the input string with 1 to n pieces of spaces
            String[] arr = inputSentence.split(DELIMITER_FORMAT);

            List<WordInfo> wordInfoList = new ArrayList<>();
            for (String s : arr) {
                WordInfo wordInfo = new WordInfo(s, 1);
                wordInfoList.add(wordInfo);
            }

            //get the map for the next step of sizing the same word
            Map<String, List<WordInfo>> map = getListMap(wordInfoList);

            List<WordInfo> list = new ArrayList<>();
            for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
                WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                list.add(wordInfo);
            }
            wordInfoList = list;

            wordInfoList.sort((w1, w2) -> w2.getCount() - w1.getCount());

            StringJoiner joiner = new StringJoiner("\n");
            for (WordInfo w : wordInfoList) {
                String s = w.getWord() + " " + w.getCount();
                joiner.add(s);
            }
            return joiner.toString();
        } catch (Exception e) {
            return "Calculate Error";
        }

    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInfo.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getWord(), arr);
            } else {
                map.get(wordInfo.getWord()).add(wordInfo);
            }
        }
        return map;
    }
}

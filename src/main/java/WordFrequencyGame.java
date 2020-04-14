import java.util.*;

public class WordFrequencyGame {
    private static final String DELIMITER_FORMAT = "\\s+";

    private List<WordInfo> wordInfoMapper(String inputSentence) {
        String[] words = inputSentence.split(DELIMITER_FORMAT);
        List<WordInfo> wordCountMapperOutput = new ArrayList<>();
        
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordCountMapperOutput.add(wordInfo);
        }

        return wordCountMapperOutput;
    }


    public String getResult(String inputSentence) {
        try {
            List<WordInfo> wordCountMapperOutput = wordInfoMapper(inputSentence);

            //get the map for the next step of sizing the same word
            Map<String, List<WordInfo>> map = getListMap(wordCountMapperOutput);

            List<WordInfo> list = new ArrayList<>();
            for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
                WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                list.add(wordInfo);
            }
            wordCountMapperOutput = list;

            wordCountMapperOutput.sort((w1, w2) -> w2.getCount() - w1.getCount());

            StringJoiner joiner = new StringJoiner("\n");
            for (WordInfo w : wordCountMapperOutput) {
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

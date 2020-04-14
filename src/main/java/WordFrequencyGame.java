import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private static final String DELIMITER_FORMAT = "\\s+";

    private List<WordCountEntry> wordInfoMapper(String inputSentence) {
        String[] words = inputSentence.split(DELIMITER_FORMAT);
        List<WordCountEntry> wordCountMapperOutput = new ArrayList<>();
        
        for (String word : words) {
            WordCountEntry wordCountEntry = new WordCountEntry(word, 1);
            wordCountMapperOutput.add(wordCountEntry);
        }

        return wordCountMapperOutput;
    }

    private Map<String, Integer> wordCountReducer(List<WordCountEntry> wordCountReducerInput) {
        Map<String, Integer> wordCountReducerOutput = wordCountReducerInput.stream()
                .collect(Collectors.toMap(WordCountEntry::getWord, WordCountEntry::getCount, Integer::sum));

        return wordCountReducerOutput;
    }

    private String toOutputFormat(Map<String, Integer> wordCountMap) {
        return wordCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(wordCountEntry -> wordCountEntry.getKey() + " " + wordCountEntry.getValue())
                .collect(Collectors.joining("\n"));
    }


    public String countWordFrequency(String inputSentence) {
        try {
            List<WordCountEntry> wordCountEntryMapperOutput = wordInfoMapper(inputSentence);
            Map<String, Integer> wordCountReducerOutput = wordCountReducer(wordCountEntryMapperOutput);
            return toOutputFormat(wordCountReducerOutput);
        } catch (Exception e) {
            return "Calculate Error";
        }

    }

}

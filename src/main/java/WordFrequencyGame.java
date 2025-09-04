import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
        if (words.length == 1) {
            return inputStr + " 1";
        } else {
            try {
                List<WordFrequency> frequencies = countFrequencies(words);
                frequencies.sort((w1, w2) -> w2.count() - w1.count());
                return composeOutput(frequencies);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private static String composeOutput(List<WordFrequency> frequencies) {
        return frequencies.stream()
                .map(w -> w.value() + " " + w.count())
                .collect(Collectors.joining("\n"));
    }

    private List<WordFrequency> countFrequencies(String[] words) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue().intValue()))
                .collect(Collectors.toList());
    }
}

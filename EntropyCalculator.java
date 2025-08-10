import java.util.*;

public class EntropyCalculator {

    public static double calculateEntropy(List<String> items) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (String item : items) {
            freqMap.put(item, freqMap.getOrDefault(item, 0) + 1);
        }

        double entropy = 0.0;
        int total = items.size();

        for (int count : freqMap.values()) {
            double p = (double) count / total;
            entropy -= p * (Math.log(p) / Math.log(2));
        }

        return entropy;
    }
}


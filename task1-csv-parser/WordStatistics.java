import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

public class WordStatistics {
    private List<Entry<String, Integer>> sortedFrequencyTable;

    private int totalWords = 0;

    private HashMap<String, Integer> createFrequencyMap(Reader reader) throws IOException {
        totalWords = 0;
        HashMap<String, Integer> map = new HashMap<>();

        final int BUFFER_SIZE = 512;
        char[] buffer = new char[BUFFER_SIZE];

        StringBuilder word = new StringBuilder();
        int cnt;

        while ((cnt = reader.read(buffer)) != -1) {
            if (cnt < BUFFER_SIZE) {
                buffer = Arrays.copyOf(buffer, cnt);
            }
            for (char c : buffer) {
                if (Character.isLetterOrDigit(c)) {
                    word.append(c);
                } else if (!word.isEmpty()) { // separator
                    map.merge(new String(word), 1, Integer::sum); // +1 for counting
                    word.setLength(0);
                    totalWords++;
                }
            }
        }
        if (!word.isEmpty()) {
            map.merge(new String(word), 1, Integer::sum); // +1 for counting
            word.setLength(0);
            totalWords++;
        }

        return map;
    }

    private List<Entry<String, Integer>> createSortedFrequencyTable(Reader reader) throws IOException {
        List<Entry<String, Integer>> frequencyTable = new ArrayList<>(
                createFrequencyMap(reader).entrySet());

        frequencyTable.sort(Entry.<String, Integer>comparingByValue().reversed()
                .thenComparing(Entry.comparingByKey()));

        return frequencyTable;
    }

    public WordStatistics(String filename) throws IOException {
        try (Reader reader = new InputStreamReader(new FileInputStream(filename))) {
            this.sortedFrequencyTable = createSortedFrequencyTable(reader);
        }

    }

    /**
     * Saves statistic in format: word,frequency,frequency_in_percents
     * 
     * @param filename - fi
     * @throws IOException
     */
    public void saveAsCSV(String filename) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filename);
                BufferedWriter bufferOut = new BufferedWriter(fileWriter)) {
            for (Entry<String, Integer> pair : sortedFrequencyTable) {
                String line = String.format(Locale.ROOT, "%s,%d,%.2f%%\n",
                        pair.getKey(),
                        pair.getValue(),
                        pair.getValue() / (float) totalWords * 100);

                bufferOut.append(line);
            }
        }
    }
}

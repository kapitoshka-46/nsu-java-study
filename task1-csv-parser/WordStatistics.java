import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WordStatistics {
    List<Entry<String, Integer>> sortedFrequencyTable;

    private static HashMap<String, Integer> createFrequencyMap(Reader reader) throws IOException {
        HashMap<String, Integer> map = new HashMap<>();

        final int BUFFER_SIZE = 512;
        char[] buffer = new char[BUFFER_SIZE];

        StringBuilder word = new StringBuilder();
        int cnt;
        while ((cnt = reader.read(buffer)) != -1) {
            if (cnt < BUFFER_SIZE) {
                buffer = Arrays.copyOf(buffer, cnt); // TODO: can i just decrease buffer length?
            }

            for (char c : buffer) {
                if (Character.isLetterOrDigit(c)) {
                    word.append(c);
                } else if (!word.isEmpty()) { // separator
                    map.merge(new String(word), 1, Integer::sum); // +1 for counting
                    word.setLength(0);
                }
            }
        }
        if (!word.isEmpty()) {
            map.merge(new String(word), 1, Integer::sum); // +1 for counting
        }

        return map;
    }

    private static List<Entry<String, Integer>> createSortedFrequencyTable(Reader reader) throws IOException {
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

    public List<Entry<String, Integer>> getFrequencyTable() {
        return Collections.unmodifiableList(sortedFrequencyTable);
    }

    public void saveAsCSV(String filename) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filename);
                BufferedWriter bufferOut = new BufferedWriter(fileWriter)) {
            for (Entry<String, Integer> pair : sortedFrequencyTable) {
                bufferOut.append(pair.getKey().toString() + "," + pair.getValue().toString() + "\n");
            }
        }
    }
}

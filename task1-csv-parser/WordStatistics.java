import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class WordStatistics {
    List<Entry<String, Integer>> sortedFrequencyTable;

    private static List<Entry<String, Integer>> createSortedFrequencyTable(Reader reader) throws IOException {
        StringBuilder word = new StringBuilder();
        HashMap<String, Integer> map = new HashMap<>();

        int c;
        while ((c = reader.read()) != -1) {
            if (Character.isLetterOrDigit(c)) {
                word.append((char) c);
            } else { // separator
                map.merge(new String(word), 1, Integer::sum); // +1 for counting
                word.setLength(0);
            }
        }

        List<Entry<String, Integer>> frequencyTable = new ArrayList<>(map.entrySet());
        frequencyTable.sort(Entry.comparingByValue(Collections.reverseOrder()));
        return frequencyTable;
    }

    public WordStatistics(String filename) throws IOException {
        System.out.println("\tWordStatistics contructor");
        try (Reader reader = new InputStreamReader(new FileInputStream(filename))) {
            this.sortedFrequencyTable = createSortedFrequencyTable(reader);
        }

        // we need to create sorted by frequancy of words list

    }

    public List<Entry<String, Integer>> getFrequencyTable() {
        return Collections.unmodifiableList(sortedFrequencyTable);
    }

    public void saveAsCSV(String filename) throws IOException {
        try (FileWriter fileWriter = new FileWriter("output.csv")) {
            for (Entry<String, Integer> pair : sortedFrequencyTable) {
                fileWriter.append(pair.getKey().toString() + "," + pair.getValue().toString() + "\n");
            }
        }

    }

}

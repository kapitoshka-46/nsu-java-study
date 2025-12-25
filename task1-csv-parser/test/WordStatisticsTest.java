import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.junit.Test;

public class WordStatisticsTest {
    private int testCount = 0;

    private String CreateTextFile(String content) throws IOException {

        final String filename = "test/cache/test_" + String.valueOf(++testCount) + ".txt";
        try (FileWriter fileOut = new FileWriter(filename)) {
            fileOut.write(content);
        }
        return filename;
    }

    private static List<String> getLines(List<Entry<String, Integer>> table) {
        List<String> result = new ArrayList<>();
        for (var entry : table) {
            result.add(entry.getKey() + "," + entry.getValue());
        }
        return result;
    }

    private static List<String> getLines(String filename) throws IOException {
        try (Reader reader = new FileReader(filename)) {
            return reader.readAllLines();
        }
    }

    private void test(String content, List<String> expected) throws IOException {
        String filename = CreateTextFile(content);
        String resultFilename = filename + ".csv";

        WordStatistics wordstat = new WordStatistics(filename);
        wordstat.saveAsCSV(resultFilename);
        List<String> result = getLines(resultFilename);

        assertEquals(result, expected);
        assertEquals(result, getLines(wordstat.getFrequencyTable()));
    }

    @Test
    public void lexicographicalWords() throws IOException {
        test("aa ba aa ba ca dd",
                List.of("aa,2", "ba,2", "ca,1", "dd,1"));
    }

    @Test
    public void noLexicographicalWords() throws IOException {
        test("ba ba aa aa ca dd",
                List.of("aa,2", "ba,2", "ca,1", "dd,1"));
    }

    @Test
    public void emptyInput() throws IOException {
        test("", List.of());
    }

    @Test
    public void BigFile() throws IOException {
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            content.append("word1000\n");
        }
        for (int i = 0; i < 300; i++) {
            content.append("word300\n");
        }
        for (int i = 0; i < 5000; i++) {
            content.append("word5000\n");
        }

        test(content.toString(),
                List.of("word5000,5000",
                        "word1000,1000",
                        "word300,300"));
    }
}
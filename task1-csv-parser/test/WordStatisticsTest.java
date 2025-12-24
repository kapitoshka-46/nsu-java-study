import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
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
}
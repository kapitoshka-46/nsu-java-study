import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting program");

        try {
            WordStatistics wordstat = new WordStatistics("test/text.txt");
            wordstat.saveAsCSV("test/result.csv");
        } catch (IOException e) {
            System.out.println("Get IO exception: " + e.getLocalizedMessage());
        }

        System.out.println("Closing...");
    }
}

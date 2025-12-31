import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage:");
            System.out.println("    freq [input text] [output csv]");
            return;
        }

        System.out.println("Reading " + args[0]);

        try {
            WordStatistics wordstat = new WordStatistics(args[0]);
            System.out.println("Saving statistics to " + args[1]);
            wordstat.saveAsCSV(args[1]);
            System.out.println("Done!");

        } catch (IOException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
}

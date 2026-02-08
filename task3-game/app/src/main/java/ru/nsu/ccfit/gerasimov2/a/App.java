package ru.nsu.ccfit.gerasimov2.a;

import java.util.InputMismatchException;
import java.util.Scanner;

import ru.nsu.ccfit.gerasimov2.a.gem.Gem;
import ru.nsu.ccfit.gerasimov2.a.gemfield.GemField;

public class App {
    static void printField(GemField gf) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print("    ");
        for (int col = 0; col < gf.cols(); col++) {
            System.out.print(col + " ");
        }
        System.out.println();
        System.out.print("    ");
        for (int col = 0; col < gf.cols(); col++) {
            System.out.print("--");
        }
        System.out.println();


        for (int i = 0; i < gf.rows(); i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < gf.cols(); j++) {
                Gem gem = gf.at(i, j);
                String gemView = gem.isDestroyed() ? "x" : Integer.valueOf(gem.color).toString();
                System.out.print(gemView + " ");
            }
            System.out.println();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }


    public static void main(String[] args) {
        GemField gemField = new GemField(5,5);
            
        Scanner scanner = new Scanner(System.in);
         while (true) {
            while(gemField.isDestroyable()) { /* бывают случаи, когда новые камушки уже находятся в оптимальной позиции */
                printField(gemField);
                gemField.destroy();
                printField(gemField);
                
                // теперь надо восполнить пропуски
                gemField.regenerateGems();
            }
            printField(gemField);   // all destroying ends

            // reading user input
            try {
                int row1 = scanner.nextInt();
                int col1 = scanner.nextInt();
                int row2 = scanner.nextInt();
                int col2 = scanner.nextInt();
                
                boolean success = gemField.swapIfPossible(row1, col1, row2, col2);
                if (!success) {
                    System.out.println("cannot swap");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println(e.getLocalizedMessage());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            
            
        }    
    }
}

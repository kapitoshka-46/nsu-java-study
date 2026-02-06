package ru.nsu.ccfit.gerasimov2.a;

import java.io.IOException;
import java.util.Scanner;

import ru.nsu.ccfit.gerasimov2.a.GemField.Gem;
import ru.nsu.ccfit.gerasimov2.a.GemField.GemField;

public class App {
    static void printField(GemField gf) {
        for (int i = 0; i < gf.rows(); i++) {
            for (int j = 0; j < gf.cols(); j++) {
                Gem gem = gf.at(i, j);
                String gemView = gem.isDestroyed() ? "x" : Integer.valueOf(gem.color).toString();
                System.out.print(gemView + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        GemField gemField = new GemField(5,5);
        printField(gemField);
    
        
        System.out.println("runnig destroyer");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printField(gemField);

            

            int row1 = scanner.nextInt();
            int col1 = scanner.nextInt();
            int row2 = scanner.nextInt();
            int col2 = scanner.nextInt();
            
            boolean success = gemField.swapIfPossible(row1, col1, row2, col2);
            if (!success) {
                System.out.println("cannot swap");
                continue;
            }
            gemField.destroy();
        }    
            
    }
}

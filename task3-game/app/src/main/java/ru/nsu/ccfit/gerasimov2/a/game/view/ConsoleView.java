package ru.nsu.ccfit.gerasimov2.a.game.view;

import java.time.Duration;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ru.nsu.ccfit.gerasimov2.a.game.model.GemField;
import ru.nsu.ccfit.gerasimov2.a.game.model.Model;
import ru.nsu.ccfit.gerasimov2.a.game.model.Position;
import ru.nsu.ccfit.gerasimov2.a.game.model.gem.Gem;

public class ConsoleView extends View {
    List<String> messages;
    Scanner scanner;    
    public ConsoleView(Model model) {
        super(model);
        scanner = new Scanner(System.in);
        messages = new ArrayList<>();
    }

    /**
     * Reading user input in foramt ROW [space] COL.
     * @return User input
     * @throws InputMismatchException  if the next token does not match the Integer regular expression, or is out of range
     * @throws NoSuchElementException  if input is exhausted
     * @throws IllegalStateException  if this scanner is closed
     */
    private Position readInputPosition() {

        while (true) {
            String rowString = scanner.next();
            String colString = scanner.next();
            try {
                Integer row = Integer.valueOf(rowString);
                Integer col = Integer.valueOf(colString);
                Position pos = new Position(row, col);

                return pos;
            } catch (NumberFormatException e) {
                displayMessage("You should type input as <ROW COL>. Try again");
            }
        }
    }

    private void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void displayGemField(Model model) {
        GemField gemField = model.getGemField();
        System.out.print("    ");
        for (int i = 0; i < gemField.getCols(); i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        System.out.println("---------------------");

        for (int i = 0; i < gemField.getRows(); i++) {
            System.out.printf("%d | ", i);
            for (int j = 0; j < gemField.getCols(); j++) {
                Gem gem = gemField.at(i, j);
                System.out.print(gem.isDestroyed() ? 'x' : String.valueOf(gem.color));
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println("-------------------");

        System.out.println("Messages:");     
        sleep(Duration.ofSeconds(1)); /* sleep after drawing */

    }

    @Override
    public void queryUpdate() {
        sleep(Duration.ofMillis(800));
        update();
    }

    @Override
    public void update() {
        displayGemField(model);
    }


    @Override
    public void message(String msg) {
        messages.add(msg);
    }

    @Override
    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public Position getUserInputSelection() {
        return readInputPosition();
    }

    @Override
    public void drawSelection(Position selectionPos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSelectionToDraw'");
    }

    
}

package ru.nsu.ccfit.gerasimov2.a.game.controller;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ru.nsu.ccfit.gerasimov2.a.game.model.Model;
import ru.nsu.ccfit.gerasimov2.a.game.model.Position;
import ru.nsu.ccfit.gerasimov2.a.game.view.View;

public class ConsoleController extends Controller {
    private Scanner scanner;
    private boolean isRunning;

    public ConsoleController(Model model, View view) {
        super(model, view);
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
    }

    private void handleInput(Position p1, Position p2) {
        boolean success = model.makeMove(p1, p2);
        if (!success) {
            view.message("Wrong move");
        }
    }

    public void runGame() {
        while (isRunning) {
            model.step();
            Position firstSelectedGem = readInputPosition();
            Position secondSelectedGem = readInputPosition();

            view.unsetAllUserSelections(secondSelectedGem);
            handleInput(firstSelectedGem, secondSelectedGem);
        }
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

                view.setUserSelection(pos);
                return pos;
            } catch (NumberFormatException e) {
                view.displayMessage("You should type input as <ROW COL>. Try again");
            }        
        }
    }
}


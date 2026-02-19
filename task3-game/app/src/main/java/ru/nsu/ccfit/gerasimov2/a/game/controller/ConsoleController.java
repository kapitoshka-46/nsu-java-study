package ru.nsu.ccfit.gerasimov2.a.game.controller;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ru.nsu.ccfit.gerasimov2.a.game.model.GemField;
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
        return new Position(scanner.nextInt(), scanner.nextInt());
 
    }
}

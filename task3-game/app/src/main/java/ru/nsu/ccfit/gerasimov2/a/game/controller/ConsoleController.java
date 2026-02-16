package ru.nsu.ccfit.gerasimov2.a.game.controller;

import java.util.List;
import java.util.Scanner;

import ru.nsu.ccfit.gerasimov2.a.game.model.GemField;
import ru.nsu.ccfit.gerasimov2.a.game.model.Model;
import ru.nsu.ccfit.gerasimov2.a.game.model.Position;

import ru.nsu.ccfit.gerasimov2.a.game.model.match3.Match3Model;
import ru.nsu.ccfit.gerasimov2.a.game.view.ConsoleView;
import ru.nsu.ccfit.gerasimov2.a.game.view.View;

public class ConsoleController {
    private View view;
    private Model model;
    private Scanner scanner;
    private boolean isRunning;

    public ConsoleController() {
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
        this.view = new ConsoleView();
        this.model = new Match3Model();
    }

    private void closeResoursesAndQuit() {
        isRunning = false;
        scanner.close();
    }

    public void runGame() {
        try {
            view.drawGemField(model); /* show field at first time */
            while (isRunning) {
                List<Position> posToDestroy = model.getPositionsToDestroy();
                if (posToDestroy.isEmpty()) {// TODO: create algo.isDestroyable and recompute it only when needed
                    /* do swap */
                    Position firstSelectedGem = readInputPosition();
                    Position secondSelectedGem = readInputPosition();
                    boolean swapSuccess = model.swapGems(firstSelectedGem, secondSelectedGem)
                            && !model.isDestroyable();

                    if (!swapSuccess) {
                        System.out.println("Cannot swap gems. Try again");
                        continue;
                    }
                } else {
                    GemField gemField = model.getGemField();

                    for (Position pos : posToDestroy) {
                        gemField.at(pos).destroyEfffect(gemField, pos);
                    }
                    view.drawGemField(model); /* show broken gems before restoring */
                    gemField.refillDestroyed();
                }

                view.drawGemField(model); /* anyway, show the field */

            }
        } catch (Exception e) { // TODO: catch onle needed exception
            System.err.println("Got exception: " + e.getLocalizedMessage());
            closeResoursesAndQuit();
        }

    }

    private Position readInputPosition() {
        return new Position(scanner.nextInt(), scanner.nextInt());
    }
}

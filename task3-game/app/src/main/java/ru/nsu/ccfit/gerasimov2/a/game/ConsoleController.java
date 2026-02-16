package ru.nsu.ccfit.gerasimov2.a.game;

import java.util.List;
import java.util.Scanner;

import ru.nsu.ccfit.gerasimov2.a.game.destroyalgo.DestroyAlgorithm;
import ru.nsu.ccfit.gerasimov2.a.game.destroyalgo.Match3DestroyAlgorithm;
import ru.nsu.ccfit.gerasimov2.a.game.factory.GemFactory;

public class ConsoleController {
    private ConsoleView view;
    private Scanner scanner;
    private boolean isRunning;
    private GemField gemField;
    private DestroyAlgorithm algo;

    ConsoleController() {
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
        this.view = new ConsoleView();
        gemField = new GemField(4, 4, new GemFactory(4));
        algo = new Match3DestroyAlgorithm();
    }

    void closeResoursesAndQuit() {
        isRunning = false;
        scanner.close();
    }

    void runGame() {
        try {
            view.drawGemField(gemField); /* show field at first time */
            while (isRunning) {
                List<Position> posToDestroy = algo.getPositionsToDestroy(gemField);
                if (posToDestroy.isEmpty()) {// TODO: create algo.isDestroyable and recompute it only when needed
                    /* do swap */
                    Position firstSelectedGem = readInputPosition();
                    Position secondSelectedGem = readInputPosition();
                    boolean swapSuccess = gemField.swapGems(firstSelectedGem, secondSelectedGem)
                            && !algo.getPositionsToDestroy(gemField).isEmpty();
                    // function
                    if (!swapSuccess) {
                        System.out.println("Cannot swap gems. Try again");
                        continue;
                    }
                } else {
                    for (Position pos : posToDestroy) {
                        gemField.at(pos).destroyEfffect(gemField, pos);
                    }
                    view.drawGemField(gemField); /* show broken gems before restoring */
                    gemField.refillDestroyed();
                }

                view.drawGemField(gemField); /* anyway, show the field */

            }
        } catch (Exception e) {
            System.err.println("Got exception: " + e.getLocalizedMessage());
            closeResoursesAndQuit();
        }

    }

    private Position readInputPosition() {
        return new Position(scanner.nextInt(), scanner.nextInt());
    }
}

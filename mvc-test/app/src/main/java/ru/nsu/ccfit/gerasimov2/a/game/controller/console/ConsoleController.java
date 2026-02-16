package ru.nsu.ccfit.gerasimov2.a.game.controller.console;

import java.util.Scanner;

import ru.nsu.ccfit.gerasimov2.a.game.controller.Controller;
import ru.nsu.ccfit.gerasimov2.a.game.model.PlayerID;
import ru.nsu.ccfit.gerasimov2.a.game.model.threexthree.Default3x3Model;
import ru.nsu.ccfit.gerasimov2.a.game.view.console.ConsoleView;

public class ConsoleController extends Controller {

    private Scanner scanner = new Scanner(System.in);

    public ConsoleController() {
        super(new ConsoleView(), new Default3x3Model());
    }

    boolean isP1 = true;

    class Position {
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int row, col;
    }

    Position getNextMove() {
        try {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            return new Position(row, col);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.exit(1);

        }
        return null;
    }

    @Override
    public void update() {
        PlayerID p1 = new PlayerID(0);
        PlayerID p2 = new PlayerID(1);

        Position move = getNextMove();
        while (!model.changeCellOwner(isP1 ? p1 : p2, move.row, move.col)) {
            System.out.println("Already owned. Choose another cell");
            move = getNextMove();
        }
        isP1 = !isP1;
    }

    @Override
    public void run() {
        view.display(model);
        while (true) {
            update();
            view.display(model);
        }
    }

}

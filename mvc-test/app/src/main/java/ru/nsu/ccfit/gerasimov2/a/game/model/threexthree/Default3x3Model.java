package ru.nsu.ccfit.gerasimov2.a.game.model.threexthree;

import java.util.Optional;

import ru.nsu.ccfit.gerasimov2.a.game.model.Model;
import ru.nsu.ccfit.gerasimov2.a.game.model.PlayerID;

public class Default3x3Model implements Model {

    public final int rows = 3;
    public final int cols = 3;

    private PlayerID[][] field = new PlayerID[3][3];

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public boolean isWinner(PlayerID player) {
        /* здесь надо, чтобы 3 клетки подряд имели одинакового владельца */
        throw new UnsupportedOperationException("Unimplemented method 'isWinner'");
    }

    @Override
    public boolean changeCellOwner(PlayerID owner, int row, int col) {
        if (isOwned(row, col)) {
            return false;
        }
        field[row][col] = owner;
        return true;
    }

    @Override
    public Optional<PlayerID> getCellOwner(int row, int col) {
        return Optional.ofNullable(field[row][col]);
    }

    @Override
    public boolean isOwned(int row, int col) {
        return field[row][col] != null;
    }

}

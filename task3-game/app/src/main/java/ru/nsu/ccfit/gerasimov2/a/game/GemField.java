package ru.nsu.ccfit.gerasimov2.a.game;

import ru.nsu.ccfit.gerasimov2.a.game.destroyalgo.DestroyAlgorithm;
import ru.nsu.ccfit.gerasimov2.a.game.factory.GemFactory;
import ru.nsu.ccfit.gerasimov2.a.game.gem.Gem;

public class GemField {
    private Gem[][] field;
    private boolean isTransposed = false;
    private GemFactory gemFactory;

    public GemField(int rows, int cols, GemFactory gemFactory) {
        if (rows <= 0)
            throw new IllegalArgumentException("Invalid rows number: " + rows);
        if (cols <= 0)
            throw new IllegalArgumentException("Invalid cols number: " + cols);

        field = new Gem[rows][cols];
        this.gemFactory = gemFactory;
        init();
    }

    boolean swapGems(Position gem1, Position gem2) {
        int row1 = isTransposed ? gem1.getCol() : gem1.getRow();
        int col1 = isTransposed ? gem1.getRow() : gem1.getCol();

        int row2 = isTransposed ? gem2.getCol() : gem2.getRow();
        int col2 = isTransposed ? gem2.getRow() : gem2.getCol();

        if (Math.abs(row1 - row2) > 1 || Math.abs(col1 - col2) > 1) {
            return false; /* can swap only neighbours */
        }

        Gem tmp = field[row1][col1];
        field[row1][col1] = field[row2][col2];
        field[row2][col2] = tmp;

        return true;
    }

    public void setFactory(GemFactory gemFactory) {
        this.gemFactory = gemFactory;
    }

    public void init() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                field[i][j] = gemFactory.newGem();
            }
        }
    }

    public Gem at(int row, int col) {
        if (row < 0)
            throw new IllegalArgumentException("Invalid row (negative): " + row);
        if (col < 0)
            throw new IllegalArgumentException("Invalid col (negative): " + col);

        return isTransposed ? field[col][row] : field[row][col];
    }

    public Gem at(Position pos) {
        return at(pos.getRow(), pos.getCol());
    }

    public int getRows() {
        return isTransposed ? field[0].length : field.length;
    }

    public int getCols() {
        return isTransposed ? field.length : field[0].length;
    }

    public void transpose() {
        isTransposed = !isTransposed;
    }

    public boolean isTransposed() {
        return isTransposed;
    }

    /**
     * Update only if gem is destroyed
     */
    public void refillDestroyed() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if (field[i][j].isDestroyed()) {
                    field[i][j] = gemFactory.newGem();
                }
            }
        }
    }
}

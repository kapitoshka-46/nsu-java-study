package ru.nsu.ccfit.gerasimov2.a.game;

import ru.nsu.ccfit.gerasimov2.a.game.gem.Gem;

public class GemField {
    private Gem[][] field;
    private boolean isTransposed = false;
    public GemField(int rows, int cols) {
        if (rows <= 0) throw new IllegalArgumentException("Invalid rows number: " + rows);
        if (cols <= 0) throw new IllegalArgumentException("Invalid cols number: " + cols);
        
        field = new Gem[rows][cols];
    } 

    
    public Gem at(int row, int col) {
        if (row < 0) throw new IllegalArgumentException("Invalid row (negative): " + row);
        if (col < 0) throw new IllegalArgumentException("Invalid col (negative): " + col);
 
        return isTransposed ? field[col][row] : field[row][col]; 
    }

     public Gem at(Position pos) {
        return isTransposed ? field[pos.getCol()][pos.getRow()] : field[pos.getRow()][pos.getCol()];
    }


    public int getRows() {
        return isTransposed ? field[0].length : field.length;
    }



    public int getCols() {
        return isTransposed ? field[0].length : field.length;
    }


    public void transpose() {
        isTransposed = !isTransposed;
    }
}

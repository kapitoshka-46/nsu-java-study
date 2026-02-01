package ru.nsu.ccfit.gerasimov2.a;

public class GemField {
    public final int rows;
    public final int cols;

    public GemField(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        gems = new Gem[rows][cols];
    }

    private Gem[][] gems;
    
    public Gem at(int row, int col) {
        return gems[row][col];
    }
}

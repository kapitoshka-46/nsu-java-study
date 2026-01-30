 package ru.nsu.ccfit.gerasimov2.a.Model.Field;

// Grid of nxm cells
public class Grid {
    private int rows;
    private int cols;
    private int size;
    private Cell[] grid;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.size = rows * cols;
        grid = new Cell[rows * cols]; 
    }

    public Cell getCell(int row, int col) {
        int index = row * rows + col; 
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Out of range");
        } 
        return grid[row * rows + col];
    }

    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
}

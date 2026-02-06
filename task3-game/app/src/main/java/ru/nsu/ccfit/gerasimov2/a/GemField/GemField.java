package ru.nsu.ccfit.gerasimov2.a.GemField;


/* идея проста
    1. Проходимся по каждому камушку и пытаемся построить линию либо вправо, 
    2. Если есть линия вправо, то разбиваем все камни. Позднее на место разбитых камней встают камни сверху 
    3. Если есть вниз, разбиваем эти камни
    4. 
 */

/* надо сделать ещё пошаговое взаимодействие с моделью, так как потом это будет удобно визуализировать
    1. Разрушение всех Камней
    2. Выпадение новыйх камней 
    3. Падение вниз
 */

public class GemField {
    final int rows;
    final int cols;
    boolean isTransposed = false;
    GemDestroyer destroyer = new Match3GemDestroyer();

    public int rows() {
        return isTransposed ? cols : rows;
    }
    public int cols() {
        return isTransposed ? rows : cols;
    }

    void swap(int row1, int col1, int row2, int col2) {        
        Gem tmp = gems[row1][col1];
        gems[row1][col1] = gems[row2][col2];
        gems[row2][col2] = tmp; 
    }

    public void destroy() {
        destroyer.destroyGems(this);
    }

    boolean isValidMove(int row1, int col1, int row2, int col2) {
        return (row1 == row2 && Math.abs(col1 - col2) == 1
            || col1 == col2 && Math.abs(row1 - row2) == 1);
    }
    public boolean swapIfPossible(int row1, int col1, int row2, int col2) {
        if (!isValidMove(row1, col1, row2, col2)) {
            return false;
        }
        swap(row1, col1, row2, col2);
        if (!destroyer.canDestroy(this)) {  
            swap(row1, col1, row2, col2);   /* wrong move -> swap back */
            return false;
        }     
        return true;    
    } 

    public GemField(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        gems = new Gem[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gems[i][j] = new Match3Gem((int) (Math.random() * 3));
            }
        }
    }

    public Gem[][] gems;
    
    public Gem at(int row, int col) {
        return isTransposed ?  gems[col][row] : gems[row][col];
    }

    public void transposeIndexes() {
        isTransposed = !isTransposed;
    }
}

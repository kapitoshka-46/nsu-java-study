package ru.nsu.ccfit.gerasimov2.a.game.model;

import java.util.List;


import ru.nsu.ccfit.gerasimov2.a.game.model.gem.Gem;
import ru.nsu.ccfit.gerasimov2.a.game.model.factory.GemFactory;
import ru.nsu.ccfit.gerasimov2.a.game.model.strategy.DestroyStratagy;
import ru.nsu.ccfit.gerasimov2.a.game.model.strategy.Match3DestroyStrategy;

public class Match3Model extends Model {
    private GemField gemField;
    private DestroyStratagy destroyAlgo;

    public Match3Model(int rows, int cols, int maxCOlor) {
        gemField = new GemField(rows, cols, new GemFactory(maxCOlor));
        destroyAlgo = new Match3DestroyStrategy();
        
    }

    public List<Position> getPositionsToDestroy() {
        return destroyAlgo.getPositionsToDestroy(gemField);
    }

    @Override
    public boolean isDestroyable() {
        return destroyAlgo.isDestroyable(gemField);
    }

    @Override
    public GemField getGemField() {
        return gemField;    //TODO: not a good function!!!!
    }



    @Override
    public Gem gemAt(Position pos) {
        return gemField.at(pos);
    }

    @Override
    public Gem gemAt(int row, int col) {
        return gemField.at(row, col);
    }

    private boolean isOutOfBounds(Position p1) {
        int row = p1.getRow();
        int col = p1.getCol();

        return !(0 <= row && row <= gemField.getRows() 
            && 0 <= col && col <= gemField.getCols()); 
    }

    @Override
    public boolean makeMove(Position p1, Position p2) {
        boolean isSame = p1.getCol() == p2.getCol() && p1.getRow() == p2.getRow();

        boolean isNeghbours = Math.abs(p1.getRow() - p2.getRow()) <= 1 
                               && Math.abs(p1.getCol() - p2.getCol()) <= 1; 
        boolean isOutOfBounds = isOutOfBounds(p1) || isOutOfBounds(p2);
        
        if (isSame || !isNeghbours || isOutOfBounds) {
            return false;
        }
        
        // now we sure that positions are correct and we allowed to swap gems 
        gemField.swap(p1, p2);  
        return true;
    }

    @Override
    public void step() {
        notifyView(); /* shows the field first time to player */ 

        List<Position> toDestroy = getPositionsToDestroy();
        while (!toDestroy.isEmpty()) {  /* do steps while can destroy */
            for (Position pos : toDestroy) {    /* destroy all */
                gemField.destroyAt(pos);
            }
            notifyView(); /* showing destroyed field */
        
            gemField.refillDestroyed(); /* generating new gems */
            notifyView(); /* showing new generated gems */

            toDestroy = getPositionsToDestroy(); /* check again */
        }
    }
}

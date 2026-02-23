package ru.nsu.ccfit.gerasimov2.a.game.view.swing;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;

import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

public class CellPanel extends JPanel {
    private int row;
    private int col;
    
    public CellPanel(int row, int col, GameArea gameArea) {
        setPreferredSize(new Dimension(60, 60));
        this.row = row;
        this.col = col;
    }

}

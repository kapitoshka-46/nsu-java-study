package ru.nsu.ccfit.gerasimov2.a.game.view.swing;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ru.nsu.ccfit.gerasimov2.a.game.model.Model;
import ru.nsu.ccfit.gerasimov2.a.game.model.Position;

public class GameArea extends JPanel {

    private int gridRows;
    private int gridCols;
    private int gridCellSize;
    private Model model;

    private void drawGrid(Graphics g) {
        for (int row = 0; row < gridRows; row++) {
            for (int col = 0; col < gridCols; col++) {
                g.setColor(Color.black);
                g.drawRect(col * gridCellSize, row * gridCellSize, gridCellSize, gridCellSize);
            }
        }
    }

    public GameArea(Rectangle bounds, Model model) {
        super();

        // set constructor params
        this.model = model;
        this.setBounds(bounds);


        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        this.gridCols = model.getGemField().getCols();
        this.gridCellSize = this.getBounds().width / gridCols;
        this.gridRows = model.getGemField().getRows();
        
        if (this.getBounds().width % gridCols != 0 || this.getBounds().height % gridRows != 0) {
            throw new IllegalArgumentException("Width and height должны быть кратны количеству клеток");
        }
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGemsOnField(g);
        drawGrid(g);
    }

    private Color intToColor(int number) {
        switch (number) {
            case 1: return Color.red;
            case 2: return Color.green;
            case 3: return Color.blue;
            case 4: return  Color.yellow;
            case 5: return Color.CYAN;
            case 6: return Color.MAGENTA;
            default:
                return Color.ORANGE;
        }
    }

    private void drawGemsOnField(Graphics g) {
        Color previous = g.getColor();

        for (int row = 0; row < gridRows; row++) {
            for (int col = 0; col < gridCols; col++) {
                g.setColor(intToColor(model.gemAt(new Position(row, col)).color));
                g.fillRect(row * gridCellSize + 5, col * gridCellSize + 5, gridCellSize - 10, gridCellSize - 10);
            
            }
        }

        g.setColor(previous);
    }    
}
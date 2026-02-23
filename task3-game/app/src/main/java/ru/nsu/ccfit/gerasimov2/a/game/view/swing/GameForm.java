package ru.nsu.ccfit.gerasimov2.a.game.view.swing;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JFrame;

import ru.nsu.ccfit.gerasimov2.a.game.model.Model;
import ru.nsu.ccfit.gerasimov2.a.game.model.Position;
import ru.nsu.ccfit.gerasimov2.a.game.view.View;

public class GameForm extends JFrame {
    private int width, height;
    private Model model;
    private GameArea gameArea;
    public GameForm(String winTitle, int width, int heght, Model model) {
        super(winTitle);
        this.width = width;
        this.height = heght;
        this.model = model;
        setSize(width, height);
        setLocationRelativeTo(null);

        this.setResizable(false);

        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.gameArea = new GameArea(new Rectangle(50, 50, 400, 400), model);
        this.add(gameArea);
    }

}

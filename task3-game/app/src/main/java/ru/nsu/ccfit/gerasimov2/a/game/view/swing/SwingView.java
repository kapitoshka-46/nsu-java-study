package ru.nsu.ccfit.gerasimov2.a.game.view.swing;

import java.time.Duration;


import ru.nsu.ccfit.gerasimov2.a.game.model.Model;
import ru.nsu.ccfit.gerasimov2.a.game.model.Position;
import ru.nsu.ccfit.gerasimov2.a.game.view.View;

public class SwingView extends View {

    private GameForm gameForm;
    Position currSelection;

    public SwingView(Model model) {
        super(model);
        this.gameForm = new GameForm("tri v ryad", 800,  800, model);
    }

    @Override
    public void update() {
        gameForm.paintAll(gameForm.getGraphics());
        try {
            Thread.sleep(Duration.ofMillis(1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // interrupt our thread if other 
        }
        return;
    }

    @Override
    public void message(String string) {
        System.err.println("message");
    }

    @Override
    public void displayMessage(String string) {
        System.err.println("display  msg");
    }

    @Override
    public Position getSelection() {
        throw new UnsupportedOperationException();
    }

}
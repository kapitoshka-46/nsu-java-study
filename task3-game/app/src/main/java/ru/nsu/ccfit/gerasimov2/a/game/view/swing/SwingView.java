package ru.nsu.ccfit.gerasimov2.a.game.view.swing;

import javax.swing.JFrame;

import ru.nsu.ccfit.gerasimov2.a.game.model.Model;
import ru.nsu.ccfit.gerasimov2.a.game.model.Position;
import ru.nsu.ccfit.gerasimov2.a.game.view.View;

public class SwingView extends View {

    private JFrame gameForm;
    public SwingView(Model model) {
        super(model);
        this.gameForm = new GameForm("tri v ryad", 800,  800, model);
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void message(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'message'");
    }

    @Override
    public void setUserSelection(Position pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUserSelection'");
    }

    @Override
    public void unsetAllUserSelections(Position pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unsetAllUserSelections'");
    }

    @Override
    public void unsetUserSelections(Position pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unsetUserSelections'");
    }

    @Override
    public void displayMessage(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayMessage'");
    }

}
package ru.nsu.ccfit.gerasimov2.a.game.view;

import ru.nsu.ccfit.gerasimov2.a.game.model.Model;

public interface View {
    void display(Model model);

    int getMaximumPlayers();
}

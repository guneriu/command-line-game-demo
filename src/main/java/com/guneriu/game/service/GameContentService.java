package com.guneriu.game.service;

import com.guneriu.game.model.Hero;

import java.util.List;

/**
 * Created by ugur on 27.06.2016.
 */
public interface GameContentService {
    List<String> getSavedGames();

    Hero loadSavedGame(String name);

    void saveGame(Hero hero);

    void loadGameContent();
}

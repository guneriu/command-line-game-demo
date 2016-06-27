package com.guneriu.game.service;

import com.guneriu.game.model.Hero;

import java.util.List;
import java.util.Optional;

/**
 * Created by ugur on 27.06.2016.
 */
public interface GameContentService {

    List<String> getSavedGames();

    Optional<Hero> getSavedGame(String name);

    void saveGame(Hero hero);

    void loadGameContent();
}

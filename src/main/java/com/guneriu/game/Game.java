package com.guneriu.game;

import com.guneriu.game.service.GameService;
import com.guneriu.game.util.io.*;
import com.guneriu.game.util.log.Logger;
import com.guneriu.game.util.log.LoggerFactory;
import com.guneriu.game.model.*;
import com.guneriu.game.service.AreaService;
import com.guneriu.game.service.WeaponService;

import java.util.List;
import java.util.Scanner;

/**
 * Created by ugur on 23.06.2016.
 */
public class Game {

    private static Logger logger = LoggerFactory.getLogger();

    public static void main(String[] args) {

        GameContentService.loadGameContent();

        Scanner scanner = new Scanner(System.in);

        GameDataWriter gameDataWriter = new GameDataWriter();

        List<String> savedGames = gameDataWriter.getSavedGames();

        Hero hero;

        if (savedGames.isEmpty()) {
            hero = createHero(scanner);
        } else {
            logger.write("(New Game)");
            logger.write("---- Saved Games ----");
            savedGames.forEach(savedGameName -> logger.write("(" + savedGameName + ")"));
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("New Game")) {
                hero = createHero(scanner);
            } else {
                hero = gameDataWriter.loadSavedGame(choice);
            }
        }

        GameService gameService = new GameService(scanner, hero);

        gameService.executeStories();
    }

    private static Hero createHero(Scanner scanner) {
        logger.write("what is your name warrior");
        Hero hero = new Hero(scanner.next());
        hero.setCurrentArea(AreaService.get("1"));
        logger.write("choose yourself a weapon");
        WeaponService.getByLevel(hero.getLevel()).forEach(weapon -> logger.write(weapon.getDescription()));
        hero.setWeapon(WeaponService.get(scanner.next()));
        return hero;
    }

}

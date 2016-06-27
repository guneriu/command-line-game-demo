package com.guneriu.game;

import com.guneriu.game.service.AreaService;
import com.guneriu.game.service.StoryService;
import com.guneriu.game.service.WeaponService;
import com.guneriu.game.service.impl.*;
import com.guneriu.game.util.log.Logger;
import com.guneriu.game.util.log.LoggerFactory;
import com.guneriu.game.model.*;

import java.util.List;
import java.util.Scanner;

/**
 * Created by ugur on 23.06.2016.
 */
public class Game {

    private static Logger logger = LoggerFactory.getLogger();

    public static void main(String[] args) {

        WeaponService weaponService = new WeaponServiceImpl();
        StoryService storyService = new StoryServiceImpl();
        AreaService areaService = new AreaServiceImpl();
        GameContentServiceImpl gameContentService = new GameContentServiceImpl(storyService, weaponService, areaService);
        gameContentService.loadGameContent();

        Scanner scanner = new Scanner(System.in);

        List<String> savedGames = gameContentService.getSavedGames();

        Hero hero;

        if (savedGames.isEmpty()) {
            hero = createHero(scanner, areaService, weaponService);
        } else {
            logger.write("(New Game)");
            logger.write("---- Saved Games ----");
            savedGames.forEach(savedGameName -> logger.write("(" + savedGameName + ")"));
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("New Game")) {
                hero = createHero(scanner, areaService, weaponService);
            } else {
                hero = gameContentService.loadSavedGame(choice);
            }
        }

        GameServiceImpl gameService = new GameServiceImpl(scanner, hero, gameContentService, storyService, weaponService);

        gameService.executeStories();
    }

    private static Hero createHero(Scanner scanner, AreaService areaService, WeaponService weaponService) {
        logger.write("what is your name warrior");
        Hero hero = new Hero(scanner.next());
        hero.setCurrentArea(areaService.get("1"));
        logger.write("choose yourself a weapon");
        weaponService.getByLevel(hero.getLevel()).forEach(weapon -> logger.write(weapon.getDescription()));
        hero.setWeapon(weaponService.get(scanner.next()));
        return hero;
    }

}

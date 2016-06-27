package com.guneriu.game.service.impl;

import com.guneriu.game.model.*;
import com.guneriu.game.service.*;
import com.guneriu.game.util.log.Logger;
import com.guneriu.game.util.log.LoggerFactory;

import java.util.Scanner;

/**
 *
 * GameServiceImpl class manages tasks like showing showMenu, executing tasks and exploring
 *
 * Created by ugur on 26.06.2016.
 */
public class GameServiceImpl implements GameService {

    private static Logger logger = LoggerFactory.getLogger();

    private final Scanner scanner;

    private final Hero hero;

    private final GameContentService gameContentService;

    private final StoryService storyService;

    private final WeaponService weaponService;

    public GameServiceImpl(Scanner scanner, Hero hero, GameContentService gameContentService, StoryService storyService, WeaponService weaponService) {
        this.scanner = scanner;
        this.hero = hero;
        this.gameContentService = gameContentService;
        this.storyService = storyService;
        this.weaponService = weaponService;
    }

    @Override
    public void executeStories() {
        Area currentArea = hero.getCurrentArea();
        currentArea.getStoryList().stream().filter(story -> !story.isCompleted()).forEach(s -> {
            logger.write(s.getDesc());
            if (s.hasEnemy()) {
                hero.fight(s.getEnemy());
            }
            s.setCompleted();
            hero.experience(s.getExperience());
            logger.write("You gained " + s.getExperience() + " experience, complete 100 to increase level");

            showMenu();
        });

        if (hero.getCurrentArea().isCompleted()) {
            explore();
        } else {
            executeStories();
        }
    }

    @Override
    public void explore() {

        if (storyService.isAllCompleted()) {
            endGame();
        }

        logger.write("you successfully completed the area.");
        logger.write("Now you can explore new areas.");
        logger.write("Where would you like to go");
        logger.write(hero.getCurrentArea().getDescription());

        String nextDirection = scanner.next();

        Direction direction = Direction.fromName(nextDirection);

        if (hero.getCurrentArea().isDirectionValid(direction)) {
            hero.setCurrentArea(hero.getCurrentArea().getConnectedArea(direction));
        }

        executeStories();
    }

    @Override
    public void showMenu() {
        logger.write("(1) Hero Stats");
        logger.write("(2) Inventory");
        logger.write("(3) Continue to stories");
        logger.write("(4) Exit Game");

        String choice = scanner.next();

        switch (choice) {
            case "1": showStats(); break;
            case "2": showInventory(); break;
            case "3": executeStories(); break;
            case "4": exit(); break;
            default: logger.write("What?"); showMenu();
        }
    }

    private void showInventory() {
        logger.write("Weapons you can choose: ");
        weaponService.getByLevel(hero.getLevel()).forEach(weapon -> logger.write(weapon.getDescription()));
        hero.setWeapon(weaponService.get(scanner.next()));
        showMenu();
    }

    private void showStats() {
        logger.write("health: " + hero.getHealth() + " experience bar: " + hero.getExperience()
                + " level: " + hero.getLevel() + " weapon: " + hero.getWeapon().getDescription());
        showMenu();
    }

    private void exit() {
        logger.write("Are you sure the exit the game y/n?");
        String next = scanner.next();
        if (next.equals("n")) {
            showMenu();
        }

        saveGame();
        System.exit(0);
    }

    private void endGame() {
        logger.write("You finished the game");

        saveGame();
        System.exit(0);
    }

    private void saveGame() {
        logger.write("Saving game");

        gameContentService.saveGame(hero);

        logger.write("Successfully saved game");
    }

}

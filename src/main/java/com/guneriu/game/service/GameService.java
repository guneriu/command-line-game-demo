package com.guneriu.game.service;

import com.guneriu.game.model.Area;
import com.guneriu.game.model.Direction;
import com.guneriu.game.model.Hero;
import com.guneriu.game.model.Story;
import com.guneriu.game.util.io.GameDataWriter;
import com.guneriu.game.util.log.Logger;
import com.guneriu.game.util.log.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Scanner;

/**
 *
 * GameService class manages tasks like showing menu, executing tasks and exploring
 *
 * Created by ugur on 26.06.2016.
 */
public class GameService {

    private static final String SAVE_DIRECTORY = System.getProperty("user.home") + "/games/";
    private static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
    private static Logger logger = LoggerFactory.getLogger();

    private final Scanner scanner;

    private final Hero hero;

    private GameDataWriter gameDataWriter;

    public GameService(Scanner scanner, Hero hero) {
        this.scanner = scanner;
        this.hero = hero;
        this.gameDataWriter = new GameDataWriter();
    }

    public void menu() {
        logger.write("(1) Hero stats");
        logger.write("(2) Inventory");
        logger.write("(3) Continue to stories");
        logger.write("(4) Exit Game");

        String choice = scanner.next();

        switch (choice) {
            case "1": stats(); break;
            case "2": inventory(); break;
            case "3": executeStories(); break;
            case "4": exit(); break;
            default: logger.write("What?"); menu();
        }
    }

    private void inventory() {
        logger.write("Weapons you can choose: ");
        WeaponService.getByLevel(hero.getLevel()).forEach(weapon -> logger.write(weapon.getDescription()));
        hero.setWeapon(WeaponService.get(scanner.next()));
        menu();
    }

    private void stats() {
        logger.write("health: " + hero.getHealth() + " experience bar: " + hero.getExperience()
                + " level: " + hero.getLevel() + " weapon: " + hero.getWeapon().getDescription());
        menu();
    }

    private void exit() {
        logger.write("Are you sure the exit the game y/n?");
        String next = scanner.next();
        if (next.equals("n")) {
            menu();
        }

        logger.write("Saving game");

        gameDataWriter.saveGame(hero);

        logger.write("Successfully saved game");
        System.exit(0);

    }

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

            menu();
        });

        if (hero.getCurrentArea().isCompleted()) {
            explore();
        } else {
            executeStories();
        }
    }

    public void explore() {

        if (StoryService.getAll().stream().allMatch(Story::isCompleted)) {
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

    public void endGame() {
        logger.write("You finished the game");
        System.exit(0);
    }

}

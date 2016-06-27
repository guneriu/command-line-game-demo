package com.guneriu.game.service.impl;

import com.guneriu.game.model.Area;
import com.guneriu.game.model.Direction;
import com.guneriu.game.model.Hero;
import com.guneriu.game.model.Weapon;
import com.guneriu.game.service.*;
import com.guneriu.game.util.log.Logger;
import com.guneriu.game.util.log.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    private Hero hero;

    private final GameContentService gameContentService;

    private final StoryService storyService;

    private final WeaponService weaponService;

    private AreaService areaService;

    public GameServiceImpl(Scanner scanner, GameContentService gameContentService, StoryService storyService, WeaponService weaponService, AreaService areaService) {
        this.scanner = scanner;
        this.gameContentService = gameContentService;
        this.storyService = storyService;
        this.weaponService = weaponService;
        this.areaService = areaService;
    }

    @Override
    public void executeStories() {
        Objects.requireNonNull(hero, "Please call startGame to setup hero");
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
        Objects.requireNonNull(hero, "Please call startGame to setup hero");
        if (storyService.isAllCompleted()) {
            endGame();
        }

        logger.write("you successfully completed the area.");
        logger.write("Now you can explore new areas.");
        logger.write("Where would you like to go");
        logger.write(hero.getCurrentArea().getDescription());

        String nextDirection = scanner.next();

        Optional<Direction> direction = Direction.fromName(nextDirection);

        if (!direction.isPresent()) {
            explore();
        }
        if (hero.getCurrentArea().isDirectionValid(direction.get())) {
            hero.setCurrentArea(hero.getCurrentArea().getConnectedArea(direction.get()));
        }

        executeStories();
    }

    @Override
    public void startGame() {
        gameContentService.loadGameContent();
        List<String> savedGames = gameContentService.getSavedGames();
        if (savedGames.isEmpty()) {
            hero = createHero();
        } else {
            hero = showGameMenu(savedGames);
        }

        this.executeStories();
    }

    private Hero showGameMenu(List<String> savedGames) {
        logger.write("(New Game)");
        logger.write("---- Saved Games ----");
        savedGames.forEach(savedGameName -> logger.write("(" + savedGameName + ")"));
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("New Game")) {
            hero = createHero();
        } else {
            Optional<Hero> optionalHero = gameContentService.getSavedGame(choice);
            if (optionalHero.isPresent()) {
                hero = optionalHero.get();
            }
            hero = showGameMenu(savedGames);
        }

        return hero;
    }

    @Override
    public void showMenu() {
        Objects.requireNonNull(hero, "Please call startGame to setup hero");
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

    private Hero createHero() {
        Hero hero = setupName();
        hero.setCurrentArea(areaService.get("1").get());
        setupWeapon(hero);
        return hero;
    }

    private void setupWeapon(Hero hero) {
        logger.write("choose yourself a weapon");
        weaponService.getByLevel(hero.getLevel()).forEach(weapon -> logger.write(weapon.getDescription()));
        Optional<Weapon> weapon = weaponService.get(scanner.next());
        if (weapon.isPresent()) {
            hero.setWeapon(weapon.get());
        } else {
            setupWeapon(hero);
        }
    }

    private Hero setupName() {
        logger.write("what is your name warrior");
        return new Hero(scanner.next());
    }

    private void showInventory() {
        setupWeapon(hero);
        showMenu();
    }

    private void showStats() {
        logger.write("health: " + hero.getHealth() + " experience bar: " + hero.getExperience()
                + " level: " + hero.getLevel() + " weapon: " + hero.getWeapon().getDescription());
        showMenu();
    }

    private void exit() {
        logger.write("Are you sure the exit the game yes/no?");
        String next = scanner.next();
        if (!next.equalsIgnoreCase("yes")) {
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

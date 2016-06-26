package com.guneriu.game;

import com.guneriu.game.io.*;
import com.guneriu.game.log.Logger;
import com.guneriu.game.log.LoggerFactory;
import com.guneriu.game.model.*;
import com.guneriu.game.provider.AreaProvider;
import com.guneriu.game.provider.StoryProvider;
import com.guneriu.game.provider.WeaponProvider;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by ugur on 23.06.2016.
 */
public class Game {

    public static final String APPENDIX = "-------- %s --------";
    private static final String DELIMITER = "#";

    private static Logger logger = LoggerFactory.getLogger();

    public static void main(String[] args) {

        loadGameContent();

        Hero hero = createHero(AreaProvider.get(1));

        Scanner scanner = new Scanner(System.in);

        System.out.println("what is your name warrior");
        hero.setName(scanner.next());
        System.out.println("choose yourself a weapon");
        WeaponProvider.getAll().forEach(weapon -> System.out.println(weapon.getDescription()));
        hero.setWeapon(WeaponProvider.get(scanner.nextInt()));

        doTasks(scanner, hero);

    }

    public static void loadGameContent() {
        try {
            ContentReader reader = new LineReader("/home/ugur/dev/projects/games/my-awesome-game/weapons.txt");
            Parser<Weapon> parser = new WeaponParser(DELIMITER);
            parser.parseContent(reader.read());
            WeaponProvider.load(parser.getContent());

            reader = new LineReader("/home/ugur/dev/projects/games/my-awesome-game/stories.txt");
            Parser<Story> storyParser = new StoryParser(DELIMITER);
            storyParser.parseContent(reader.read());
            StoryProvider.load(storyParser.getContent());

            reader = new LineReader("/home/ugur/dev/projects/games/my-awesome-game/areas.txt");
            Parser<Area> areaParser = new AreaParser(DELIMITER);
            areaParser.parseContent(reader.read());
            AreaProvider.load(areaParser.getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doTasks(Scanner scanner, Hero hero) {
        Area currentArea = hero.getCurrentArea();
        currentArea.getStoryList().stream().filter(story -> !story.isCompleted()).forEach(s -> {
            logger.write(s.getDesc());
            if (s.hasEnemy()) {
                hero.fight(s.getEnemy());
            }
            s.setCompleted();
            hero.experience(s.getExperience());
            logger.write("You gained " + s.getExperience() + " experience, complete 100 to increase level");
            logger.write("Your new stats, health: " + hero.getHealth() + " level: " + hero.getLevel() + " experience: " + hero.getExperience());
        });

        if (hero.getCurrentArea().isCompleted()) {
            explore(scanner, hero);
        } else {
            doTasks(scanner, hero);
        }
    }

    public static void explore(Scanner scanner, Hero hero) {

//        if (hero.getCurrentArea().getLinkedAreas().values().stream().allMatch(Area::isCompleted)) {
//            endGame();
//        }

        logger.write("you successfully completed the area.");
        logger.write("Now you can explore new areas.");
        logger.write("Where would you like to go");
        logger.write(hero.getCurrentArea().getDescription());

        String nextDirection = scanner.next();

        Direction direction = Direction.fromName(nextDirection);

        if (hero.getCurrentArea().isDirectionValid(direction)) {
            hero.setCurrentArea(hero.getCurrentArea().getConnectedArea(direction));
        }

        doTasks(scanner, hero);
    }

    public static void endGame() {
        logger.write("You finished the game");
        System.exit(0);
    }

    private static Hero createHero(Area area) {
        Hero hero = new Hero();
        hero.setCurrentArea(area);
        return hero;
    }

    public static String wrapTheOutput(String text) {
        return String.format(APPENDIX, text);
    }
}

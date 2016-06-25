package com.guneriu.game;

import com.guneriu.game.io.*;
import com.guneriu.game.model.Area;
import com.guneriu.game.model.Direction;
import com.guneriu.game.model.Hero;
import com.guneriu.game.model.Weapon;
import com.guneriu.game.story.*;
import com.guneriu.game.log.Logger;
import com.guneriu.game.log.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ugur on 23.06.2016.
 */
public class Game {

    public static final String APPENDIX = "-------- %s --------";
    private static final String DELIMITER = "#";

    private static Logger logger = LoggerFactory.getLogger();

    public static void main(String[] args) {

        try {
            ContentReader reader = new LineReader("/home/ugur/dev/projects/games/my-awesome-game/weapons.txt");
            Parser<Weapon> parser = new WeaponParser(DELIMITER);
            parser.parseContent(reader.read());
            parser.getContent().forEach(w -> System.out.println(w.getDescription()));

            reader = new LineReader("/home/ugur/dev/projects/games/my-awesome-game/areas.txt");
            Parser<Area> areaParser = new AreaParser(DELIMITER);
            areaParser.parseContent(reader.read());
            parser.getContent().forEach(w -> System.out.println(w.getDescription()));

        } catch (IOException e) {
            e.printStackTrace();
        }


//        List<Area> areas = createAreas();
//        Hero hero = createHero(areas);
//
//        Scanner scanner = new Scanner(System.in);
//
//        doTasks(scanner, hero);

    }

    public static void doTasks(Scanner scanner, Hero hero) {
        Area currentArea = hero.getCurrentArea();
        currentArea.getStoryList().stream().filter(story -> !story.isCompleted()).forEach(s -> {
            logger.write(s.getDesc());
            s.getFunction().apply(scanner, hero);
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

    private static Hero createHero(List<Area> areas) {
        Hero hero = new Hero();
        hero.setCurrentArea(areas.get(0));
        return hero;
    }

    private static List<Area> createAreas() {
        Area entrance = Area.builder().name("Entrance").storyList(
                Stream.of(EntranceStory.values()).map(BaseStory::getStory)
                        .collect(Collectors.toList())).build();

        Area trainingArea = Area.builder().name("Training Area").storyList(
                Stream.of(TrainingStory.values()).map(BaseStory::getStory)
                        .collect(Collectors.toList())).build();

        Area bazaarArea = Area.builder().name("Bazaar Area").storyList(
                Stream.of(BazaarStory.values()).map(BaseStory::getStory)
                        .collect(Collectors.toList())).build();

        entrance.connect(trainingArea, Direction.SOUTH);
        trainingArea.connect(bazaarArea, Direction.EAST);

        return Arrays.asList(entrance, trainingArea, bazaarArea);
    }


    public static String wrapTheOutput(String text) {
        return String.format(APPENDIX, text);
    }
}

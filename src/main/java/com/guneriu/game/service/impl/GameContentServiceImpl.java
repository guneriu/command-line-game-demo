package com.guneriu.game.service.impl;

import com.guneriu.game.Game;
import com.guneriu.game.model.Area;
import com.guneriu.game.model.Hero;
import com.guneriu.game.model.Story;
import com.guneriu.game.model.Weapon;
import com.guneriu.game.service.AreaService;
import com.guneriu.game.service.GameContentService;
import com.guneriu.game.service.StoryService;
import com.guneriu.game.service.WeaponService;
import com.guneriu.game.util.io.*;
import com.guneriu.game.util.log.Logger;
import com.guneriu.game.util.log.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for loading game data
 *
 * Created by ugur on 26.06.2016.
 */
public class GameContentServiceImpl implements GameContentService {

    private static final String DELIMITER = "#";
    public static final String CONTENT_FOLDER = "/content/assassins_creed/";
    private static final String SAVE_DIRECTORY = System.getProperty("user.home") + "/games/";
    private static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
    private static Logger logger = LoggerFactory.getLogger();


    private final StoryService storyService;

    private final WeaponService weaponService;

    private final AreaService areaService;

    public GameContentServiceImpl(StoryService storyService, WeaponService weaponService, AreaService areaService) {
        this.storyService = storyService;
        this.weaponService = weaponService;
        this.areaService = areaService;
    }

    @Override
    public List<String> getSavedGames() {
        try {
            return Files.list(Paths.get(SAVE_DIRECTORY)).map(path -> path.getFileName().toString()).collect(Collectors.toList());
        } catch (IOException e) {
            //
        }
        return new ArrayList<>();
    }

    @Override
    public Hero loadSavedGame(String name) {
        Hero hero = null;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(SAVE_DIRECTORY, name), CHARSET_UTF8)) {
            String[] heroData = reader.readLine().split("#");
            hero = new Hero(name, Integer.parseInt(heroData[0]));
            hero.experience(Integer.parseInt(heroData[1]) * 100 + Integer.parseInt(heroData[2]));
            hero.setCurrentArea(areaService.get(heroData[3]));
            hero.setWeapon(weaponService.get(heroData[4]));


            while (reader.ready()) {
                String[] storyData = reader.readLine().split("#");
                Story story = storyService.get(storyData[0]);
                Boolean completed = Boolean.valueOf(storyData[1]);
                if (completed) {
                    story.setCompleted();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return hero;
    }

    @Override
    public void saveGame(Hero hero) {
        try {
            Path path = Paths.get(SAVE_DIRECTORY + hero.getName());
            File file = path.toFile();
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, CHARSET_UTF8, StandardOpenOption.TRUNCATE_EXISTING)) {
                bufferedWriter.write(hero.getHealth() + "#" + hero.getLevel() + "#" + hero.getExperience() + "#" + hero.getCurrentArea().getId()
                        + "#" + hero.getWeapon().getId());
                bufferedWriter.newLine();

                storyService.getAll().forEach(story -> {
                    try {
                        bufferedWriter.write(story.getId() + "#" + story.isCompleted());
                        bufferedWriter.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.write("could not save the game");
                        System.exit(-1);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.write("could not save the game");
            System.exit(-1);
        }

    }

    @Override
    public void loadGameContent() {
        try {
            ContentReader reader = new LineReader(Game.class.getResource(CONTENT_FOLDER + "weapons.txt").getFile());
            Parser<Weapon> parser = new WeaponParser(DELIMITER);
            parser.parseContent(reader.read());
            weaponService.load(parser.getContent());

            reader = new LineReader(Game.class.getResource(CONTENT_FOLDER + "stories.txt").getFile());
            Parser<Story> storyParser = new StoryParser(weaponService, DELIMITER);
            storyParser.parseContent(reader.read());
            storyService.load(storyParser.getContent());

            reader = new LineReader(Game.class.getResource(CONTENT_FOLDER + "areas.txt").getFile());
            Parser<Area> areaParser = new AreaParser(storyService, DELIMITER);
            areaParser.parseContent(reader.read());
            areaService.load(areaParser.getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.guneriu.game.util.io;

import com.guneriu.game.Game;
import com.guneriu.game.model.Area;
import com.guneriu.game.model.Story;
import com.guneriu.game.model.Weapon;
import com.guneriu.game.service.AreaService;
import com.guneriu.game.service.StoryService;
import com.guneriu.game.service.WeaponService;

import java.io.IOException;

/**
 * Static class for loading game data
 *
 * Created by ugur on 26.06.2016.
 */
public class GameContentService {

    private static final String SEPERATOR = "#";
    public static final String CONTENT_FOLDER = "/content/assassins_creed/";

    public static void loadGameContent() {
        try {
            ContentReader reader = new LineReader(Game.class.getResource(CONTENT_FOLDER + "weapons.txt").getFile());
            Parser<Weapon> parser = new WeaponParser(SEPERATOR);
            parser.parseContent(reader.read());
            WeaponService.load(parser.getContent());

            reader = new LineReader(Game.class.getResource(CONTENT_FOLDER + "stories.txt").getFile());
            Parser<Story> storyParser = new StoryParser(SEPERATOR);
            storyParser.parseContent(reader.read());
            StoryService.load(storyParser.getContent());

            reader = new LineReader(Game.class.getResource(CONTENT_FOLDER + "areas.txt").getFile());
            Parser<Area> areaParser = new AreaParser(SEPERATOR);
            areaParser.parseContent(reader.read());
            AreaService.load(areaParser.getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

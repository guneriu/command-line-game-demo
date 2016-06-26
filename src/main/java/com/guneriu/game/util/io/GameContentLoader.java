package com.guneriu.game.util.io;

import com.guneriu.game.Game;
import com.guneriu.game.model.Area;
import com.guneriu.game.model.Story;
import com.guneriu.game.model.Weapon;
import com.guneriu.game.util.provider.AreaProvider;
import com.guneriu.game.util.provider.StoryProvider;
import com.guneriu.game.util.provider.WeaponProvider;

import java.io.IOException;

/**
 * Created by ugur on 26.06.2016.
 */
public class GameContentLoader {

    private static final String SEPERATOR = "#";

    public static void loadGameContent() {
        try {
            ContentReader reader = new LineReader(Game.class.getResource("/content/assassins_creed/weapons.txt").getFile());
            Parser<Weapon> parser = new WeaponParser(SEPERATOR);
            parser.parseContent(reader.read());
            WeaponProvider.load(parser.getContent());

            reader = new LineReader(Game.class.getResource("/content/assassins_creed/stories.txt").getFile());
            Parser<Story> storyParser = new StoryParser(SEPERATOR);
            storyParser.parseContent(reader.read());
            StoryProvider.load(storyParser.getContent());

            reader = new LineReader(Game.class.getResource("/content/assassins_creed/areas.txt").getFile());
            Parser<Area> areaParser = new AreaParser(SEPERATOR);
            areaParser.parseContent(reader.read());
            AreaProvider.load(areaParser.getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

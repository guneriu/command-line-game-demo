package com.guneriu.game.util.io;

import com.guneriu.game.model.Hero;
import com.guneriu.game.model.Story;
import com.guneriu.game.service.WeaponService;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse List of String and creates {@link Story} lists
 *
 * Created by ugur on 25.06.2016.
 */
public class StoryParser implements Parser<Story> {
    private String delimiter;

    private final WeaponService weaponService;

    /**
     * uses given delimiter to split line
     *
     * @param delimiter
     */
    public StoryParser(WeaponService weaponService, String delimiter) {
        this.delimiter = delimiter;
        this.weaponService = weaponService;
    }

    /**
     * uses {@link List<String>} and parses {@link Story} objects
     */
    @Override
    public List<Story> parseContent(List<String> lines) {
        List<Story> storyList = new ArrayList<>();
        for (String line : lines) {
            String[] storyData = line.split(delimiter);
            String storyId = storyData[0];
            String storyText = storyData[1];
            Story story = new Story(storyId, storyText);
            story.setExperience(Integer.parseInt(storyData[2]));
            if (storyData.length > 3) {
                for (int i = 3; i < storyData.length; i+=2) {
                    Hero enemy = new Hero(storyData[i], Integer.parseInt(storyData[i + 1]));
                    if (!storyData[i + 2].isEmpty()) {
                        enemy.setWeapon(weaponService.get(storyData[i + 2]).get());
                        i++;
                    }
                    story.setEnemy(enemy);
                }
            }
            storyList.add(story);
        }

        return storyList;
    }

}

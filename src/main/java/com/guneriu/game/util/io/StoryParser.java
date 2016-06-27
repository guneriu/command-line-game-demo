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
    private List<Story> storyList = new ArrayList<>();
    private String delimiter;

    /**
     * uses given delimiter to split line
     *
     * @param delimiter
     */
    public StoryParser(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * uses {@link List<String>} and parses {@link Story} objects
     */
    @Override
    public void parseContent(List<String> lines) {
        for (String line : lines) {
            String[] values = line.split(delimiter);
            String storyId = values[0];
            String storyText = values[1];
            Story story = new Story(storyId, storyText);
            story.setExperience(Integer.parseInt(values[2]));
            if (values.length > 3) {
                for (int i = 3; i < values.length; i+=2) {
                    Hero enemy = new Hero(values[i], Integer.parseInt(values[i + 1]));
                    if (!values[i + 2].isEmpty()) {
                        enemy.setWeapon(WeaponService.get(values[i + 2]));
                        i++;
                    }
                    story.setEnemy(enemy);
                }
            }
            storyList.add(story);
        }
    }

    @Override
    public List<Story> getContent() {
        return storyList;
    }

}

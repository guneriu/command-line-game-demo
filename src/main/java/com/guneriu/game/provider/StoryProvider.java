package com.guneriu.game.provider;

import com.guneriu.game.model.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ugur on 26.06.2016.
 */
public class StoryProvider {

    private static List<Story> storyList = new ArrayList<>();

    private StoryProvider() {}


    public static void load(List<Story> stories) {
        storyList.addAll(stories);

    }

    public static Story get(Integer id) {
        return storyList.stream().filter(story -> story.getId().equals(id)).findFirst().get();
    }
}

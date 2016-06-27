package com.guneriu.game.service;

import com.guneriu.game.model.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for accessing {@link Story} objects
 *
 * Created by ugur on 26.06.2016.
 */
public class StoryService {

    private static List<Story> storyList = new ArrayList<>();

    private StoryService() {}


    public static void load(List<Story> stories) {
        storyList.addAll(stories);

    }

    public static Story get(String id) {
        return storyList.stream().filter(story -> story.getId().equals(id)).findFirst().get();
    }

    public static List<Story> getAll() {
        return storyList;
    }


}

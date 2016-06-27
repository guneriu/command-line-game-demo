package com.guneriu.game.service;

import com.guneriu.game.model.Story;

import java.util.List;

/**
 * Created by ugur on 27.06.2016.
 */
public interface StoryService {

    void load(List<Story> stories);

    Story get(String id);

    List<Story> getAll();

    boolean isAllCompleted();
}

package com.guneriu.game.service;

import com.guneriu.game.model.Story;

import java.util.List;
import java.util.Optional;

/**
 * Created by ugur on 27.06.2016.
 */
public interface StoryService {

    void add(List<Story> stories);

    Optional<Story> get(String id);

    List<Story> getAll();

    boolean isAllCompleted();

    void setCompleted(Story story);
}

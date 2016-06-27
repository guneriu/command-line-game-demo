package com.guneriu.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Area class which represent a playable area in game context
 *
 * Created by ugur on 24.06.2016.
 */
public class Area implements Description {
    private String id;

    private final String name;

    private Map<Direction, Area> linkedAreas = new HashMap<>();

    private List<Story> storyList = new ArrayList<>();

    public Area(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Map<Direction, Area> getLinkedAreas() {
        return linkedAreas;
    }

    public List<Story> getStoryList() {
        return storyList;
    }

    public void addStory(Story story) {
        if (this.storyList == null) {
            this.storyList = new ArrayList<>();
        }
        this.storyList.add(story);
    }

    /**
     * connects two areas with the given direction and opposite
     *
     * @param linkedArea area to connect
     * @param direction direction to the area
     */
    public void connect(Area linkedArea, Direction direction) {
        this.getLinkedAreas().put(direction, linkedArea);
        linkedArea.getLinkedAreas().put(direction.getOpposite(), this);
    }

    public boolean isDirectionValid(Direction direction) {
        return this.linkedAreas.keySet().contains(direction);
    }

    public Area getConnectedArea(Direction direction) {
        return this.linkedAreas.get(direction);
    }

    public boolean isCompleted() {
        return this.storyList.stream().allMatch(Story::isCompleted);
    }

    @Override
    public String getDescription() {
        return "Area{ " + this.name
                + " you can explore "
                + getLinkedAreasDescription();
    }

    private String getLinkedAreasDescription() {
        if (this.linkedAreas.isEmpty()) {
            return "";
        }
        return this.getLinkedAreas().entrySet().stream().map(entry -> entry.getKey().name() + " to " +  entry.getValue().getName() + " ").reduce(String::concat).get();
    }
}

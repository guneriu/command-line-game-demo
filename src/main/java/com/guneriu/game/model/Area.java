package com.guneriu.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ugur on 24.06.2016.
 */
public class Area implements Description {
    private Integer id;
    private String name;

    Area(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private Map<Direction, Area> linkedAreas = new HashMap<>();

    private List<Story> storyList = new ArrayList<>();

    private boolean completed;

    public Area(String name) {
        this.name = name;
    }

    public Area(String name, Map<Direction, Area> linkedAreas, List<Story> storyList) {
        this(name);
        this.linkedAreas = linkedAreas;
        this.storyList = storyList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static AreaBuilder builder() {
        return new AreaBuilder();
    }

    public Map<Direction, Area> getLinkedAreas() {
        return linkedAreas;
    }

    public void setLinkedAreas(Map<Direction, Area> linkedAreas) {
        this.linkedAreas = linkedAreas;
    }

    public List<Story> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<Story> storyList) {
        this.storyList = storyList;
    }

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

    public static class AreaBuilder {
        private String name;
        private Map<Direction, Area> linkedAreas = new HashMap<>();
        private List<Story> storyList;

        AreaBuilder() {
        }

        public Area.AreaBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Area.AreaBuilder linkedAreas(Map<Direction, Area> linkedAreas) {
            this.linkedAreas = linkedAreas;
            return this;
        }

        public Area.AreaBuilder storyList(List<Story> storyList) {
            this.storyList = storyList;
            return this;
        }

        public Area build() {
            return new Area(name, linkedAreas, storyList);
        }

        public String toString() {
            return "com.guneriu.game.model.Area.AreaBuilder(name=" + this.name + ", linkedAreas=" + this.linkedAreas + ", storyList=" + this.storyList + ")";
        }
    }
}

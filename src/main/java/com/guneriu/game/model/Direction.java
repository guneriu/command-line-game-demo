package com.guneriu.game.model;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Directions allowed to explore in the game
 *
 * Created by ugur on 25.06.2016.
 */
public enum Direction {
    NORTH("NORTH"), SOUTH("SOUTH"), EAST("EAST"), WEST("WEST");

    private String name;

    Direction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<Direction> fromName(String name) {
        if (name != null && !name.isEmpty()) {
            return Stream.of(Direction.values()).filter(d -> d.getName().equalsIgnoreCase(name)).findFirst();
        }

        return null;
    }

    public Direction getOpposite() {
        switch (this) {
            case NORTH: return SOUTH;
            case SOUTH: return NORTH;
            case WEST: return EAST;
            case EAST: return WEST;
        }

        return null;
    }
}

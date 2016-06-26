package com.guneriu.game.io;

import com.guneriu.game.model.Area;
import com.guneriu.game.model.Direction;
import com.guneriu.game.provider.StoryProvider;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Parse List of String and creates {@code Order} and {@code Customer} lists
 */
public class AreaParser implements Parser<Area> {
    private List<Area> areaList = new ArrayList<>();
    private String delimeter;

    /**
     * uses given delimiter to split line
     *
     * @param delimeter
     */
    public AreaParser(String delimeter) {
        this.delimeter = delimeter;
    }

    /**
     * uses {@code List<String>} and parses {@code Customer} and {@code Order} objects
     */
    @Override
    public void parseContent(List<String> lines) {
        Map<String, Area> areaMap = new LinkedHashMap<>();
        for (String line : lines) {
            String[] values = line.split(delimeter);

            Integer id = Integer.parseInt(values[0]);
            Area area = getOrCreate(areaMap, values[1]);
            area.setId(id);

            Stream.of(values[2].split(",")).forEachOrdered(storyId -> area.getStoryList().add(StoryProvider.get(Integer.parseInt(storyId))));

            if (values.length > 3) {
                for (int i = 3; i < values.length; i+=2) {
                    Direction direction = Direction.fromName(values[i]);
                    Area linkedArea = getOrCreate(areaMap, values[i + 1]);
                    area.connect(linkedArea, direction);
                }
            }
        }

        areaList.addAll(areaMap.values());
    }

    @Override
    public List<Area> getContent() {
        return areaList;
    }

    private Area getOrCreate(Map<String, Area> areaMap, String name) {
        areaMap.putIfAbsent(name, new Area(name));
        return areaMap.get(name);
    }

}

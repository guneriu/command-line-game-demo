package com.guneriu.game.util.io;

import com.guneriu.game.model.Area;
import com.guneriu.game.model.Direction;
import com.guneriu.game.service.StoryService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Parse List of String and creates {@link Area} lists
 *
 * Created by ugur on 25.06.2016.
 */
public class AreaParser implements Parser<Area> {
    private List<Area> areaList = new ArrayList<>();
    private String delimiter;

    /**
     * uses given delimiter to split line
     *
     * @param delimiter
     */
    public AreaParser(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * uses {@link List<String>} and parses {@link Area} objects
     */
    @Override
    public void parseContent(List<String> lines) {
        Map<String, Area> areaMap = new LinkedHashMap<>();
        for (String line : lines) {
            String[] values = line.split(delimiter);

            Area area = getOrCreate(areaMap, values[1]);
            area.setId(values[0]);

            Stream.of(values[2].split(",")).forEachOrdered(storyId -> area.addStory(StoryService.get(storyId)));

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

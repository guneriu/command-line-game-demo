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
    private String delimiter;
    private final StoryService storyService;

    /**
     * uses given delimiter to split line
     *
     * @param delimiter
     */
    public AreaParser(StoryService storyService, String delimiter) {
        this.storyService = storyService;
        this.delimiter = delimiter;
    }

    /**
     * uses {@link List<String>} and parses {@link Area} objects
     */
    @Override
    public List<Area> parseContent(List<String> lines) {
        Map<String, Area> areaMap = new LinkedHashMap<>();
        for (String line : lines) {
            String[] areaData = line.split(delimiter);

            Area area = getOrCreate(areaMap, areaData[1]);
            area.setId(areaData[0]);

            Stream.of(areaData[2].split(",")).forEachOrdered(storyId -> area.addStory(storyService.get(storyId)));

            if (areaData.length > 3) {
                for (int i = 3; i < areaData.length; i+=2) {
                    Direction direction = Direction.fromName(areaData[i]);
                    Area linkedArea = getOrCreate(areaMap, areaData[i + 1]);
                    area.connect(linkedArea, direction);
                }
            }
        }

        return new ArrayList<>(areaMap.values());
    }

    private Area getOrCreate(Map<String, Area> areaMap, String name) {
        areaMap.putIfAbsent(name, new Area(name));
        return areaMap.get(name);
    }

}

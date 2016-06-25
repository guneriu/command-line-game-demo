package com.guneriu.game.io;

import com.guneriu.game.builder.AreaBuilder;
import com.guneriu.game.builder.Builder;
import com.guneriu.game.model.Area;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Parse List of String and creates {@code Order} and {@code Customer} lists
 */
public class AreaParser implements Parser<Area> {
    private List<Area> areaList = new ArrayList<>();
    private Builder<Area> areaBuilder = new AreaBuilder();
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
        for (String line : lines) {
            String[] values = line.split(delimeter);
            Area area = areaBuilder.build(values);
            areaList.add(area);
        }
    }


    @Override
    public List<Area> getContent() {
        return areaList;
    }

}

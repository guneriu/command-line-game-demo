package com.guneriu.game.provider;

import com.guneriu.game.model.Area;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ugur on 26.06.2016.
 */
public class AreaProvider {

    private static List<Area> areaList = new ArrayList<>();

    private AreaProvider() {}


    public static void load(List<Area> areas) {
        areaList.addAll(areas);

    }

    public static Area get(Integer id) {
        return areaList.stream().filter(area -> area.getId().equals(id)).findFirst().get();
    }
}

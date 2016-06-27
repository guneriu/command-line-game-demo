package com.guneriu.game.service;

import com.guneriu.game.model.Area;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for accessing {@link Area} objects
 *
 * Created by ugur on 26.06.2016.
 */
public class AreaService {

    private static List<Area> areaList = new ArrayList<>();

    private AreaService() {}


    public static void load(List<Area> areas) {
        areaList.addAll(areas);

    }

    public static Area get(String id) {
        return areaList.stream().filter(area -> area.getId().equals(id)).findFirst().get();
    }
}

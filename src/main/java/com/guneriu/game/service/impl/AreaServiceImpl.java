package com.guneriu.game.service.impl;

import com.guneriu.game.model.Area;
import com.guneriu.game.service.AreaService;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for accessing {@link Area} objects
 *
 * Created by ugur on 26.06.2016.
 */
public class AreaServiceImpl implements AreaService {

    private static List<Area> areaList = new ArrayList<>();


    @Override
    public void load(List<Area> areas) {
        areaList.addAll(areas);
    }

    @Override
    public Area get(String id) {
        return areaList.stream().filter(area -> area.getId().equals(id)).findFirst().get();
    }
}

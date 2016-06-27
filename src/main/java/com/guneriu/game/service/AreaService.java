package com.guneriu.game.service;

import com.guneriu.game.model.Area;

import java.util.List;

/**
 * Created by ugur on 27.06.2016.
 */
public interface AreaService {
    void load(List<Area> areas);

    Area get(String id);
}

package com.guneriu.game.service;

import com.guneriu.game.model.Area;

import java.util.List;
import java.util.Optional;

/**
 * Created by ugur on 27.06.2016.
 */
public interface AreaService {

    void add(List<Area> areas);

    Optional<Area> get(String id);
}

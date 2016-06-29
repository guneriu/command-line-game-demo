package com.guneriu.game.service.impl;

import com.guneriu.game.model.Area;
import com.guneriu.game.model.Direction;
import com.guneriu.game.service.AreaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for accessing {@link Area} objects
 *
 * Created by ugur on 26.06.2016.
 */
public class AreaServiceImpl implements AreaService {

    private List<Area> areaList = new ArrayList<>();


    @Override
    public void add(List<Area> areas) {
        areaList.addAll(areas);
    }

    @Override
    public Optional<Area> get(String id) {
        return areaList.stream().filter(area -> area.getId().equals(id)).findFirst();
    }

    /**
     * connects two areas with the given direction and opposite
     *
     * @param area first area to connect
     * @param linkedArea second area to connect to first area
     * @param direction direction to the area
     */
    @Override
    public void connect(Area area, Area linkedArea, Direction direction) {
        area.getLinkedAreas().put(direction, linkedArea);
        linkedArea.getLinkedAreas().put(direction.getOpposite(), area);
    }
}

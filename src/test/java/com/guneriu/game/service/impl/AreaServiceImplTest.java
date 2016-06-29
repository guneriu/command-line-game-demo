package com.guneriu.game.service.impl;

import com.guneriu.game.model.Area;
import com.guneriu.game.model.Direction;
import com.guneriu.game.service.AreaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by ugur on 29.06.2016.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class AreaServiceImplTest {

    private AreaService areaService;
    private Area sampleArea;

    @Before
    public void setUp() throws Exception {
        this.areaService = new AreaServiceImpl();
        sampleArea = new Area("Test area");
        sampleArea.setId("1");
    }

    @Test
    public void givenAreas_whenGet_thenReturnAreaWithGivenId() {
        Area expectedArea = new Area("Second area");
        expectedArea.setId("2");
        areaService.add(Arrays.asList(sampleArea, expectedArea));

        Optional<Area> actualArea = areaService.get(expectedArea.getId());
        Assert.assertTrue(actualArea.isPresent());
        Assert.assertEquals(expectedArea.getId(), actualArea.get().getId());
    }

    @Test
    public void givenTwoAreas_whenConnect_thenConnectThemBidirectional() {
        Area linkedArea = new Area("Second area");
        linkedArea.setId("2");

        Direction direction = Direction.NORTH;
        areaService.connect(sampleArea, linkedArea, direction);

        Assert.assertTrue(!sampleArea.getLinkedAreas().isEmpty());
        Assert.assertTrue(!linkedArea.getLinkedAreas().isEmpty());

        Assert.assertEquals(linkedArea, sampleArea.getConnectedArea(direction));
        Assert.assertEquals(sampleArea, linkedArea.getConnectedArea(direction.getOpposite()));


    }
}
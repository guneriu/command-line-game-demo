package com.guneriu.game.util.io;

import com.guneriu.game.model.Area;
import com.guneriu.game.model.Story;
import com.guneriu.game.service.AreaService;
import com.guneriu.game.service.StoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by ugur on 29.06.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class AreaParserTest {

    private AreaParser areaParser;

    private String delimiter = "#";

    private String sampleZeroConnectedAreaData = "1#Entrance Area#1";

    private String sampleConnectedAreaData = "1#Entrance Area#1,2#SOUTH#Training Area";

    private AreaService areaService;

    private Story sampleStory;

    @Before
    public void setUp() throws Exception {
        StoryService storyService = Mockito.mock(StoryService.class);
        areaService = Mockito.mock(AreaService.class);
        areaParser = new AreaParser(storyService, areaService, delimiter);

        sampleStory = new Story("1", "Test story");
        sampleStory.setExperience(10);

        Mockito.when(storyService.get(Mockito.any())).thenReturn(Optional.of(sampleStory));
    }

    @Test
    public void givenNotConnectedArea_whenParseContent_thenCreateAreaAsGiven() throws Exception {
        List<Area> areaList = areaParser.parseContent(Arrays.asList(sampleZeroConnectedAreaData));
        Area area = areaList.get(0);
        String[] areaData = sampleZeroConnectedAreaData.split(delimiter);
        Assert.assertEquals(areaData[0], area.getId());
        Assert.assertEquals(areaData[1], area.getName());
    }

    @Test
    public void given2Story_whenParseContent_thenCreateAreaWith2Story() throws Exception {
        List<Area> areaList = areaParser.parseContent(Arrays.asList(sampleZeroConnectedAreaData));
        Area area = areaList.get(0);
        String[] areaData = sampleZeroConnectedAreaData.split(delimiter);
        Assert.assertEquals(areaData[2].split(",").length, area.getStoryList().size());
    }

    @Test
    public void givenConnectedArea_whenParseContent_thenConnectThem() throws Exception {
        areaParser.parseContent(Arrays.asList(sampleConnectedAreaData));

        Mockito.verify(areaService).connect(Mockito.any(), Mockito.any(), Mockito.any());
    }

}
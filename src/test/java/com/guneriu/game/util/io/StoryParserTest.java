package com.guneriu.game.util.io;

import com.guneriu.game.model.Story;
import com.guneriu.game.model.Weapon;
import com.guneriu.game.service.WeaponService;
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
public class StoryParserTest {

    private StoryParser storyParser;

    private String delimiter = "#";

    private String sampleStoryData = "2#Sample Story Desc#50#Trainer#10#1";

    private Weapon sampleWeapon;

    @Before
    public void setUp() throws Exception {
        WeaponService weaponService = Mockito.mock(WeaponService.class);
        storyParser = new StoryParser(weaponService, delimiter);
        sampleWeapon = new Weapon("1", "test weapon", 20, 1);

        Mockito.when(weaponService.get(Mockito.any())).thenReturn(Optional.of(sampleWeapon));
    }

    @Test
    public void givenStoryData_whenParseContent_thenCreateStoryAsGiven() throws Exception {
        List<Story> storyList = storyParser.parseContent(Arrays.asList(sampleStoryData));
        Story story = storyList.get(0);
        String[] storyData = sampleStoryData.split(delimiter);
        Assert.assertEquals(storyData[0], story.getId());
        Assert.assertEquals(storyData[1], story.getDescription());
        Assert.assertEquals(Integer.valueOf(storyData[2]), story.getExperience());
    }

    @Test
    public void givenStoryDataWithEnemy_whenParseContent_thenCreateStoryWithEnemy() throws Exception {
        List<Story> storyList = storyParser.parseContent(Arrays.asList(sampleStoryData));
        Story story = storyList.get(0);
        String[] storyData = sampleStoryData.split(delimiter);
        Assert.assertEquals(storyData[3], story.getEnemy().getName());
        Assert.assertEquals(Integer.valueOf(storyData[4]), story.getEnemy().getHealth());
        Assert.assertEquals(sampleWeapon, story.getEnemy().getWeapon());
    }


}
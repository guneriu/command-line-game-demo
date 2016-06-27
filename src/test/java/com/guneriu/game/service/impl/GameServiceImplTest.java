package com.guneriu.game.service.impl;

import com.guneriu.game.exception.SystemExitException;
import com.guneriu.game.model.Area;
import com.guneriu.game.model.Hero;
import com.guneriu.game.model.Story;
import com.guneriu.game.model.Weapon;
import com.guneriu.game.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by ugur on 22.06.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest {

    private static final String STATS = "1";
    private static final String INVENTORY = "2";
    private static final String STORIES = "3";
    private static final String EXIT_GAME = "4";
    public static final String EXIT_YES = "yes";

    private GameService gameService;

    private StoryService storyService;

    private WeaponService weaponService;

    private AreaService areaService;

    private GameContentService gameContentService;

    private InputService inputService;

    private ExitService exitService;

    private Story testStory;

    private Weapon testWeapon;

    private Area testArea;

    @Before
    public void setUp() throws Exception {
        inputService = Mockito.mock(InputService.class);
        exitService = Mockito.mock(ExitService.class);
        gameContentService = Mockito.mock(GameContentService.class);
        weaponService = Mockito.mock(WeaponService.class);
        areaService = Mockito.mock(AreaService.class);
        storyService = Mockito.mock(StoryService.class);
        gameService = new GameServiceImpl(inputService, exitService, gameContentService, storyService, weaponService, areaService);

        Mockito.doThrow(new SystemExitException()).when(exitService).exit();

        Mockito.when(gameContentService.getSavedGames()).thenReturn(new ArrayList<>());
        testStory = new Story("1", "Test story");
        testStory.setExperience(10);
        testWeapon = new Weapon("1", "test weapon", 20, 1);
        testArea = new Area("Test area");
        testArea.addStory(testStory);

        Mockito.when(weaponService.get(Mockito.any())).thenReturn(Optional.of(testWeapon));
        Mockito.when(storyService.get(Mockito.any())).thenReturn(Optional.of(testStory));
        ArrayList<Story> stories = new ArrayList<>();
        stories.add(testStory);
        Mockito.when(storyService.getAll()).thenReturn(stories);
        Mockito.when(areaService.get(Mockito.any())).thenReturn(Optional.of(testArea));


    }

    @Test
    public void givenHeroName_whenCreateHero_thenCreateHeroWithGivenName() throws Exception {
        //given
        String expectedHeroName = "Test Hero";
        Mockito.when(inputService.next()).thenReturn(testWeapon.getId(), EXIT_GAME, EXIT_YES);
        Mockito.when(inputService.nextLine()).thenReturn(expectedHeroName);
//        Mockito.when(inputService.next()).thenReturn("yes");
        //when
        try {
            gameService.startGame();
        } catch (Exception e) {
            Assert.assertTrue(e.getClass().equals(SystemExitException.class));
        }

        //then
        ArgumentCaptor<Hero> heroCapture = ArgumentCaptor.forClass(Hero.class);
        Mockito.verify(gameContentService).saveGame(heroCapture.capture());
        Assert.assertEquals(expectedHeroName, heroCapture.getValue().getName());
    }

    @Test
    public void givenWeapon_whenCreateHero_thenCreateHeroWithGivenWeapon() throws Exception {
        //given
        String expectedHeroName = "Test Hero";
        Mockito.when(inputService.next()).thenReturn(testWeapon.getId(), EXIT_GAME, EXIT_YES);
        Mockito.when(inputService.nextLine()).thenReturn(expectedHeroName);
//        Mockito.when(inputService.next()).thenReturn("yes");
        //when
        try {
            gameService.startGame();
        } catch (Exception e) {
            Assert.assertTrue(e.getClass().equals(SystemExitException.class));
        }

        //then
        ArgumentCaptor<Hero> heroCapture = ArgumentCaptor.forClass(Hero.class);
        Mockito.verify(gameContentService).saveGame(heroCapture.capture());
        Assert.assertEquals(testWeapon, heroCapture.getValue().getWeapon());
    }

    @Test
    public void givenStoryCompleted_whenExplore_thenSetStoryCompleted() throws Exception {
        //given
        String expectedHeroName = "Test Hero";
        Mockito.when(inputService.next()).thenReturn(testWeapon.getId(), EXIT_GAME, EXIT_YES);
        Mockito.when(inputService.nextLine()).thenReturn(expectedHeroName);
        //when
        try {
            gameService.startGame();
        } catch (Exception e) {
            Assert.assertTrue(e.getClass().equals(SystemExitException.class));
        }

        //then
        Mockito.verify(storyService).setCompleted(testStory);
    }

    @Test
    public void givenAllStoriesCompleted_whenExplore_thenEndGame() throws Exception {
        //given
        String expectedHeroName = "Test Hero";
        Mockito.when(inputService.nextLine()).thenReturn(expectedHeroName);
        Mockito.when(inputService.next()).thenReturn(testWeapon.getId(), STORIES, STORIES);
        testStory.setCompleted();
        ArrayList<Story> stories = new ArrayList<>();
        stories.add(testStory);
        Mockito.when(storyService.getAll()).thenReturn(stories);
        Mockito.when(storyService.isAllCompleted()).thenReturn(true);

        //when
        try {
            gameService.startGame();
        } catch (Exception e) {
            Assert.assertTrue(e.getClass().equals(SystemExitException.class));
        }

        //then
        Mockito.verify(gameContentService).saveGame(Mockito.any());
    }

}

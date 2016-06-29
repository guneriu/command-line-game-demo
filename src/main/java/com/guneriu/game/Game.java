package com.guneriu.game;

import com.guneriu.game.service.*;
import com.guneriu.game.service.impl.*;

import java.util.Scanner;

/**
 * Created by ugur on 23.06.2016.
 */
public class Game {

    public static void main(String[] args) {

        WeaponService weaponService = new WeaponServiceImpl();
        StoryService storyService = new StoryServiceImpl();
        AreaService areaService = new AreaServiceImpl();
        InputService inputService = new InputServiceImpl(new Scanner(System.in));
        ExitService exitService = new ExitServiceImpl();
        GameContentServiceImpl gameContentService = new GameContentServiceImpl(storyService, weaponService, areaService, exitService);

        GameServiceImpl gameService = new GameServiceImpl(inputService, exitService, gameContentService, storyService, weaponService, areaService);
        gameService.startGame();

    }

}

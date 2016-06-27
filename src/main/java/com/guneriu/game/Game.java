package com.guneriu.game;

import com.guneriu.game.service.AreaService;
import com.guneriu.game.service.StoryService;
import com.guneriu.game.service.WeaponService;
import com.guneriu.game.service.impl.*;
import com.guneriu.game.util.log.Logger;
import com.guneriu.game.util.log.LoggerFactory;

import java.util.Scanner;

/**
 * Created by ugur on 23.06.2016.
 */
public class Game {

    public static void main(String[] args) {

        WeaponService weaponService = new WeaponServiceImpl();
        StoryService storyService = new StoryServiceImpl();
        AreaService areaService = new AreaServiceImpl();
        GameContentServiceImpl gameContentService = new GameContentServiceImpl(storyService, weaponService, areaService);

        Scanner scanner = new Scanner(System.in);
        GameServiceImpl gameService = new GameServiceImpl(scanner, gameContentService, storyService, weaponService, areaService);
        gameService.startGame();

    }

}

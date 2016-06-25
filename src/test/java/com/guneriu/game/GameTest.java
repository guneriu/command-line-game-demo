package com.guneriu.game;

import com.guneriu.game.model.Hero;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by ugur on 22.06.2016.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class GameTest {


    @Test
    public void givenEmptyGame_whenCreateHero_thenReturnHero() throws Exception {

        Hero hero = new Hero("ugur");
        Assert.assertNotNull(hero);
    }
}

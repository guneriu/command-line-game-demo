package com.guneriu.game.story;

import com.guneriu.game.model.old.OldWeapon;
import com.guneriu.game.log.LoggerFactory;

/**
 * Created by ugur on 25.06.2016.
 */
public enum EntranceStory  implements BaseStory {
    START_GAME(new Story("Welcome. \nWhat is your Name.", (scanner, hero) -> {
        hero.setName(scanner.next());
        return hero;
    }, 10)),
    CHOOSE_A_WEAPON(new Story("Choose a OldWeapon", (scanner, hero) -> {
        OldWeapon.getByLevel(hero.getLevel()).forEach(w -> LoggerFactory.getLogger().write((w.getDescription())));
        OldWeapon weapon = OldWeapon.getByCode(scanner.nextInt());
        hero.setWeapon(weapon);
        LoggerFactory.getLogger().write(hero.getDescription());
        return hero;
    }, 10));

    private Story story;

    EntranceStory(Story story) {
        this.story= story;
    }

    public Story getStory() {
        return story;
    }
}

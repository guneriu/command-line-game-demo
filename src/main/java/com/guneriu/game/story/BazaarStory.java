package com.guneriu.game.story;

import com.guneriu.game.model.Hero;
import com.guneriu.game.model.old.OldWeapon;

/**
 * Created by ugur on 25.06.2016.
 */
public enum BazaarStory implements BaseStory {
    FOLLOW_THE_TARGET(new Story("Now you need to follow the target without getting noticed"
            + "\nyou followed the target"
            + "He recognized you, now fight.\n"
            , (scanner, hero) -> hero, 20, new Hero("Templar", 40, OldWeapon.BASIC_ARROW)));

    private Story story;

    BazaarStory(Story story) {
        this.story= story;
    }

    public Story getStory() {
        return story;
    }
}

package com.guneriu.game.story;

import com.guneriu.game.model.Hero;

/**
 * Created by ugur on 25.06.2016.
 */
public enum TrainingStory implements BaseStory {
    DUMMY_TRAIN(new Story("Now you have your weapon."
            + " You must practise", (scanner, hero) -> hero, 10, new Hero("Dummy Warrior", 20)));

    private Story story;

    TrainingStory(Story story) {
        this.story= story;
    }

    @Override
    public Story getStory() {
        return story;
    }
}

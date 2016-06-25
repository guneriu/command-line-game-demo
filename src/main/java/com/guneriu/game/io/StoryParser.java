package com.guneriu.game.io;

import com.guneriu.game.builder.Builder;
import com.guneriu.game.builder.StoryBuilder;
import com.guneriu.game.story.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse List of String and creates {@code Order} and {@code Customer} lists
 */
public class StoryParser implements Parser<Story> {
    private List<Story> storyList = new ArrayList<>();
    private Builder<Story> storyBuilder = new StoryBuilder();
    private String delimeter;

    /**
     * uses given delimiter to split line
     *
     * @param delimeter
     */
    public StoryParser(String delimeter) {
        this.delimeter = delimeter;
    }

    /**
     * uses {@code List<String>} and parses {@code Customer} and {@code Order} objects
     */
    @Override
    public void parseContent(List<String> lines) {
        for (String line : lines) {
            String[] values = line.split(delimeter);
            Story story = storyBuilder.build(values);
            storyList.add(story);
        }
    }

    @Override
    public List<Story> getContent() {
        return storyList;
    }

}

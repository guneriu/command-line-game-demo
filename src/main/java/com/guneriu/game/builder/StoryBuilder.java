package com.guneriu.game.builder;

import com.guneriu.game.story.Story;

/**
 * Builder class for {@code Customer}
 *
 */
public class StoryBuilder implements Builder<Story> {

	/**
	 * creates {@code Customer} from string array
	 * 
	 * @param values string array containing values
	 * @return {@code Customer}
	 */
	@Override
	public Story build(String[] values) {
		return new Story(1, "hede");
	}

}

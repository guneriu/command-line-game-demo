package com.guneriu.game.util.io;

import java.util.List;

/**
 * Parser to parse lines String list of T
 *
 * Created by ugur on 25.06.2016.
 */
public interface Parser<T> {

    /**
     *
     * uses {@link List<String>} and parses List of T
     *
     * @param lines content to be parsed
     * @return parsed List of T
     */
    List<T> parseContent(List<String> lines);


}

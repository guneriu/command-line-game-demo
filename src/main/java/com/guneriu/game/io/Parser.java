package com.guneriu.game.io;

/**
 * Created by ugur on 26.06.2016.
 */

import java.util.List;

/**
 *
 * Parser to parse lines String list
 *
 */
public interface Parser<T> {

    public void parseContent(List<String> lines);
    public List<T> getContent();

}

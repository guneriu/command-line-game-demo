package com.guneriu.game.model;

import java.util.function.Function;

/**
 * Created by ugur on 23.06.2016.
 */
@FunctionalInterface
public interface Action<T> {

    T apply(T type, Function<T, T> function);
//    default T apply(T type, Function<? extends T, ? extends T> function) {
//        return function.apply(type);
//    };


}

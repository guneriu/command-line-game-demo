package com.guneriu.game.service.impl;

import com.guneriu.game.service.InputService;

import java.util.Scanner;

/**
 * Created by ugur on 28.06.2016.
 */
public class InputServiceImpl implements InputService {

    private final Scanner scanner;

    public InputServiceImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String next() {
        return scanner.next();
    }

    @Override
    public String nextLine() {
        return scanner.next();
    }
}

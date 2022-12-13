package com.findwise.simplesearchengine.engine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tokenizer {

    private Tokenizer() {
    }

    public static List<String> tokenize(String input) {
        return input.trim().equals("") ?
                Collections.emptyList() :
                Arrays.stream(input.trim().toLowerCase().split("\\W+")).toList();
    }
}

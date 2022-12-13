package com.findwise.simplesearchengine.engine.scoring.tf;

import java.math.BigDecimal;
import java.util.List;

public interface TFCalculator {
    BigDecimal calculate(String term, List<String> tokens);
}

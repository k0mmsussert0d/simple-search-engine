package com.findwise.simplesearchengine.engine.scoring.tf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class RawCountTFCalculator implements TFCalculator {
    @Override
    public BigDecimal calculate(String term, List<String> tokens) {
        return BigDecimal.valueOf(
                tokens.stream()
                        .filter(t -> t.equals(term))
                        .count()
        ).divide(BigDecimal.valueOf(tokens.size()), 6, RoundingMode.HALF_UP);
    }
}

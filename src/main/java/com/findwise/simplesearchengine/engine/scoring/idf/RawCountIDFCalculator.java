package com.findwise.simplesearchengine.engine.scoring.idf;

import java.math.BigDecimal;

public class RawCountIDFCalculator implements IDFCalculator {
    @Override
    public BigDecimal calculate(long occurrences, long totalDocuments) {
        return BigDecimal.valueOf(
                Math.log((double) totalDocuments / (double) occurrences)
        );
    }
}

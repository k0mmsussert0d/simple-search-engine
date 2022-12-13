package com.findwise.simplesearchengine.engine.scoring.idf;

import java.math.BigDecimal;

public interface IDFCalculator {
    BigDecimal calculate(long occurrences, long totalDocuments);
}

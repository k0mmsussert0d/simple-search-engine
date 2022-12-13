package com.findwise.simplesearchengine.engine;

import com.findwise.IndexEntry;
import com.findwise.SearchEngine;
import com.findwise.simplesearchengine.engine.scoring.idf.IDFCalculator;
import com.findwise.simplesearchengine.engine.scoring.tf.TFCalculator;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class SearchEngineImpl implements SearchEngine {

    private final Multimap<String, Document> idx = HashMultimap.create();
    private final TFCalculator tf;
    private final IDFCalculator idf;

    public SearchEngineImpl(TFCalculator tf, IDFCalculator idf) {
        this.tf = tf;
        this.idf = idf;
    }

    @Override
    public void indexDocument(String id, String content) {
        var document = new Document(id, content);
        Tokenizer.tokenize(content)
                .forEach(token -> idx.put(token, document));
    }

    @Override
    public List<IndexEntry> search(String term) {
        Collection<Document> hits = idx.get(term);
        if (hits.isEmpty()) {
            return Collections.emptyList();
        }

        BigDecimal idfScore = idf.calculate(hits.size(), idx.size());
        return hits.stream()
                .map(hit -> new IndexEntryImpl(
                        hit.id(),
                        calculateScore(term, hit, idfScore)
                ))
                .sorted(Comparator.comparing(IndexEntry::getScore).reversed())
                .collect(Collectors.toList());
    }

    private double calculateScore(String term, Document hit, BigDecimal idfScore) {
        return tf.calculate(term, Tokenizer.tokenize(hit.content()))
                .multiply(idfScore)
                .doubleValue();
    }
}

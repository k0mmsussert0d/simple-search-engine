package com.findwise.simplesearchengine.engine

import com.findwise.IndexEntry
import com.findwise.SearchEngine
import com.findwise.simplesearchengine.engine.scoring.idf.RawCountIDFCalculator
import com.findwise.simplesearchengine.engine.scoring.tf.RawCountTFCalculator
import spock.lang.Shared
import spock.lang.Specification


class TFIDFSearchEngineImplSpec extends Specification {

    @Shared
    SearchEngine engine

    def docs = [
            "1": "the brown fox jumped over the brown dog",
            "2": "the lazy brown dog sat in the corner",
            "3": "the red fox bit the lazy dog",
    ]

    def setup() {
        engine = new SearchEngineImpl(
                new RawCountTFCalculator(),
                new RawCountIDFCalculator(),
        )
    }

    def "search results are sorted according to tf-idf score"() {
        given:
        docs.each { key, value -> engine.indexDocument(key, value) }

        when:
        def results0 = engine.search("brown")
        def results1 = engine.search("fox")

        then:
        results0.collect(IndexEntry::getId) == ["1", "2"]
        results1.collect(IndexEntry::getId) == ["3", "1"]
    }
}
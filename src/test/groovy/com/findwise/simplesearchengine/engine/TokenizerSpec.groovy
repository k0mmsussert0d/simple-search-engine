package com.findwise.simplesearchengine.engine

import spock.lang.Specification

class TokenizerSpec extends Specification {

    def "returns no tokens for empty input"() {
        expect:
        Tokenizer.tokenize(input).size() == 0

        where:
        input << [
                "",
                " ",
                "       ",
                "\t\n"
        ]
    }

    def "returns single token for one-word input"() {
        expect:
        Tokenizer.tokenize(input) == output

        where:
        input       | output
        "foo"       | ["foo"]
        "foo "      | ["foo"]
        "    foo  " | ["foo"]
    }

    def "returns all tokens for multiple words input"() {
        expect:
        Tokenizer.tokenize(input) == output

        where:
        input                | output
        "foo bar"            | ["foo", "bar"]
        "foo bar biz"        | ["foo", "bar", "biz"]
        "foo      bar"       | ["foo", "bar"]
        "foo,   bar   , biz" | ["foo", "bar", "biz"]
    }
}

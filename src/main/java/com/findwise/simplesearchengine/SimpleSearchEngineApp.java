package com.findwise.simplesearchengine;

import com.findwise.IndexEntry;
import com.findwise.SearchEngine;
import com.findwise.simplesearchengine.engine.SearchEngineImpl;
import com.findwise.simplesearchengine.engine.scoring.idf.RawCountIDFCalculator;
import com.findwise.simplesearchengine.engine.scoring.tf.RawCountTFCalculator;

import java.util.List;
import java.util.Scanner;

public class SimpleSearchEngineApp {

    public static void main(String[] args) {
        SearchEngine searchEngine = new SearchEngineImpl(new RawCountTFCalculator(), new RawCountIDFCalculator());
        System.out.println("""
                Simple Search Engine
                --------------------
                [1] Index new document
                [2] Search
                [0] Exit
                                
                Enter the option number and hit <ENTER>
                """);

        var scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            var mode = scanner.nextLine();
            switch (mode) {
                case "1" -> {
                    System.out.println("Enter document ID: ");
                    var docId = scanner.nextLine();
                    System.out.println("Enter document content: ");
                    var content = scanner.nextLine();
                    searchEngine.indexDocument(docId, content);
                }
                case "2" -> {
                    System.out.println("Enter search term: ");
                    var term = scanner.nextLine();
                    List<IndexEntry> results = searchEngine.search(term);
                    if (results.isEmpty()) {
                        System.out.println("No results found");
                    } else {
                        System.out.println("Results found:");
                        results.forEach(hit -> System.out.printf("%s\t%s%n", hit.getScore(), hit.getId()));
                    }
                }
                case "0" -> System.exit(0);
                default -> System.err.println("Unrecognized option");
            }
            System.out.println("Enter the option number and hit <ENTER>");
        }
    }
}

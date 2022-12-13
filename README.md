# Simple Search Engine

CLI demo implementation of [tf-idf](https://en.wikipedia.org/wiki/Tf%E2%80%93idf)-backed in-memory search engine.

## Build

This project is built and managed by Maven. It requires JDK 17+. Use the following command to build it into a JAR file:

```bash
mvn clean package
```

## Usage

Run the app with

```bash
java -jar target/simple-search-engine-1.0-SNAPSHOT-jar-with-dependencies.jar
```

You should be greeted with a main menu.

```
Simple Search Engine
--------------------
[1] Index new document
[2] Search
[0] Exit

Enter the option number and hit <ENTER>
```

Follow the instructions displayed on the screen.

## Notes

> There’s a mistake in the example. Please find it.

The order of documents returned for the example "fox" query is wrong. Should they be sorted according to their respective tf*idf scoring, the correct order is `[3, 1]`.
package ai.brace;

import ai.brace.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args) throws UnsupportedEncodingException {
        TextComparator c = new TextComparator();
        System.out.println("==== task 1 =====");
        task1("a1.json", c);
        System.out.println("==== task 2 =====");
        task2("a1.json", "a2.json", c);
        System.out.println("==== task 3 =====");
        task3("a1.json", "a2.json");
    }

    /**
     * parses the resource file into instance of Book
     *
     * @param filename
     * @return
     * @throws UnsupportedEncodingException
     */
    private static Book getBook(String filename) throws UnsupportedEncodingException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LastModified.class, new LastModifiedDeserializer());
        Gson gson = gsonBuilder.create();
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream(filename);
        Reader reader = new InputStreamReader(is, "UTF-8");
        JsonReader jsonReader = new JsonReader(reader);
        return gson.fromJson(jsonReader, Book.class);
    }

    /**
     * sort (by id) and print the "textdata"
     *
     * @throws UnsupportedEncodingException
     */
    private static void task1(String filename, TextComparator comparator) throws UnsupportedEncodingException {
        Book book = getBook(filename);
        book.getTextArray().stream().sorted(comparator).forEach(t -> System.out.println(t.getTextdata()));
    }

    /**
     * merge, sort (by id), and price the "textdata"
     *
     * @throws UnsupportedEncodingException
     */
    private static void task2(String book1Filename, String book2Filename, TextComparator comparator) throws UnsupportedEncodingException {
        Book book1 = getBook(book1Filename);
        Book book2 = getBook(book2Filename);
        Stream.concat(book1.getTextArray().stream(), book2.getTextArray().stream()).sorted(comparator).forEach(t -> System.out.println(t.getTextdata()));
    }

    /**
     * word frequency counter
     *
     * @param book1Filename
     * @param book2Filename
     * @throws UnsupportedEncodingException
     */
    private static void task3(String book1Filename, String book2Filename) throws UnsupportedEncodingException {
        Book book1 = getBook(book1Filename);
        Book book2 = getBook(book2Filename);
        Map<String, Integer> counter = new TreeMap<>();

        // used to extract the word (doesn't handle any words with dash or any other chars in it)
        Pattern wordPattern = Pattern.compile("([A-Za-z]+)");

        // get the words into "counter"
        book1.getTextArray().forEach(t -> {
            frequencyCounter(t.getTextdata(), counter, wordPattern);
        });
        book2.getTextArray().forEach(t -> {
            frequencyCounter(t.getTextdata(), counter, wordPattern);
        });

        for (String word : counter.keySet()) {
            System.out.println("(" + word + ") : " + counter.get(word));
        }
    }

    /**
     * splits the text by whitespace and adds to the "counter"
     *
     * @param text
     * @param counter
     */
    private static void frequencyCounter(String text, Map<String, Integer> counter, Pattern wordPattern) {
        String[] split = text.split("[\\s]");

        for (String s : split) {
            Matcher m1 = wordPattern.matcher(s);

            // make sure it matches the "word" pattern and extract the word
            if (m1.find()) {
                String match = m1.group(0).toLowerCase();
                Integer c = counter.get(match);

                if (c == null) {
                    c = 0;
                }

                c += 1;
                counter.put(match, c);
            }
        }
    }

    private static void task4(String book1Filename, String book2Filename) throws UnsupportedEncodingException {
        Book book1 = getBook(book1Filename);
        Book book2 = getBook(book2Filename);


    }
}

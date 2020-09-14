package ai.brace;

import ai.brace.model.Book;
import ai.brace.model.TextComparator;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args) throws UnsupportedEncodingException {
        TextComparator c = new TextComparator();
        System.out.println("==== task 1 =====");
        task1("a1.json", c);
        System.out.println("==== task 2 =====");
        task2("a1.json", "a2.json", c);
    }

    private static Book getBook(String filename) throws UnsupportedEncodingException {
        Gson gson = new Gson();
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
}

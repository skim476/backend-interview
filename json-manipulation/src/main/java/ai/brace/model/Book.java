package ai.brace.model;

import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Book {
    private String version;

    private String uuid;

    /**
     * since we are reading in a long and writing a date/time in ISO format,
     * i decided to just create a new class and have custom serializer/deserializer just for
     * this attribute/class.  another approach is to create a custom serializer/deserializer
     * for the Book class.
     */
    private LastModified lastModified;

    private String title;

    private String author;

    private String translator;

    private String releaseDate;

    private String language;

    private List<Text> textArray;

    public Book() {

    }

    /**
     * merges the 2 Books, taking on the attributes of the newer Book (based on lastModified)
     *
     * @param book1
     * @param book2
     */
    public Book(Book book1, Book book2) {
        Book newer = null;
        Book older = null;

        // find the newer/older book based on lastModified
        if (book1.getLastModified().getLastModified() >= book2.getLastModified().getLastModified()) {
            newer = book1;
            older = book2;
        } else {
            newer = book2;
            older = book1;
        }

        // set from newer.  and if the newer doesn't have it, set it from older
        setVersion(newer.getVersion() != null ? newer.getVersion() : older.getVersion());
        setUuid(UUID.randomUUID().toString().toLowerCase());
        setLastModified(newer.getLastModified());
        setTitle(newer.getTitle() != null ? newer.getTitle() : older.getTitle());
        setAuthor(newer.getAuthor() != null ? newer.getAuthor() : older.getAuthor());
        setTranslator(newer.getTranslator() != null ? newer.getTranslator() : older.getTranslator());
        setReleaseDate(newer.getReleaseDate() != null ? newer.getReleaseDate() : older.getReleaseDate());
        setLanguage(newer.getLanguage() != null ? newer.getLanguage() : older.getLanguage());
        List<Text> texts = new ArrayList<>();
        texts.addAll(book1.getTextArray());
        texts.addAll(book2.getTextArray());
        setTextArray(texts.stream().sorted(new TextComparator()).collect(Collectors.toList()));
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LastModified getLastModified() {
        return lastModified;
    }

    public void setLastModified(LastModified lastModified) {
        this.lastModified = lastModified;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Text> getTextArray() {
        return textArray;
    }

    public void setTextArray(List<Text> textArray) {
        this.textArray = textArray;
    }
}

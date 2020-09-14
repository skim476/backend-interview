package ai.brace.model;

import com.google.gson.annotations.JsonAdapter;

import java.util.List;
import java.util.UUID;

public class Book {
    private String version;

    private String uuid;

    /**
     * since we are reading in a long and writing a date/time in ISO format,
     * i decided to just create a new class and have custom serializer/deserializer just for
     * this attribute/class.
     */
    private LastModified lastModified;

    private String title;

    private String author;

    private String translator;

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

//        if (book1.getLastModified() >= book2.getLastModified()) {
//            newer = book1;
//            older = book2;
//        } else {
//            newer = book2;
//            older = book1;
//        }

        setVersion(newer.getVersion() != null ? newer.getVersion() : older.getVersion());
        setUuid(UUID.randomUUID().toString().toLowerCase());
        setLastModified(newer.getLastModified());
        setTitle(newer.getTitle() != null ? newer.getTitle() : older.getTitle());
        setAuthor(newer.getAuthor() != null ? newer.getAuthor() : older.getAuthor());
        setTranslator(newer.getTranslator() != null ? newer.getTranslator() : older.getTranslator());
        setLanguage(newer.getTranslator() != null ? newer.getTranslator() : older.getTranslator());

        // sort?
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

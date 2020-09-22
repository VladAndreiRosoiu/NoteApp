package ro.var.noteapplication.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Note {

    private String title;
    private String body;
    private long writeDate;
    private long modifiedOnDate;
    private boolean markAsFinished;
    private List<String> hashtagList = new ArrayList<>();

    public Note() {
    }

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
        this.writeDate = System.currentTimeMillis();
        this.modifiedOnDate = System.currentTimeMillis();
        this.markAsFinished = false;
        this.hashtagList = null;
    }

    public Note(String title, String body, List<String> hashtagList) {
        this.title = title;
        this.body = body;
        this.writeDate = System.currentTimeMillis();
        this.modifiedOnDate = System.currentTimeMillis();
        this.markAsFinished = false;
        this.hashtagList = hashtagList;
    }

    public Note(String title, String body, long writeDate, long modifiedOnDate, boolean markAsFinished, List<String> hashtagList) {
        this.title = title;
        this.body = body;
        this.writeDate = writeDate;
        this.modifiedOnDate = modifiedOnDate;
        this.markAsFinished = markAsFinished;
        this.hashtagList = hashtagList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getWriteDate() {
        return this.writeDate;
    }

    public long getModifiedOnDate() {
        return modifiedOnDate;
    }

    public void setModifiedOnDate(long modifiedOnDate) {
        this.modifiedOnDate = modifiedOnDate;
    }

    public List<String> getHashtagList() {
        return hashtagList;
    }

    public boolean isMarkAsFinished() {
        return markAsFinished;
    }

    public void setMarkAsFinished(boolean markAsFinished) {
        this.markAsFinished = markAsFinished;
    }

    public void setHashtagList(List<String> hashtagList) {
        this.hashtagList = hashtagList;
    }


    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a | dd-MM-yyyy ");
        return title + "\n" +
                "Created on: " + sdf.format(writeDate) + "\n" +
                "Last modified on: " + sdf.format(modifiedOnDate) + "\n" +
                body + "\n" +
                "Note is marked as done: " + markAsFinished + "\n" +
                "Hashtags " + hashtagList.toString() + "." + "\n\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return writeDate == note.writeDate &&
                modifiedOnDate == note.modifiedOnDate &&
                markAsFinished == note.markAsFinished &&
                Objects.equals(title, note.title) &&
                Objects.equals(body, note.body) &&
                Objects.equals(hashtagList, note.hashtagList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, body, writeDate, modifiedOnDate, markAsFinished, hashtagList);
    }
}

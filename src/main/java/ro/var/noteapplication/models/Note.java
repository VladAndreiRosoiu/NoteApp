package ro.var.noteapplication.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Note {

    private String title;
    private String body;
    private long creationDate;
    private long modificationDate;
    private boolean markAsFinished;
    private List<String> hashtagList = new ArrayList<>();

    public Note() {
    }

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
        this.creationDate = System.currentTimeMillis();
        this.modificationDate = System.currentTimeMillis();
        this.markAsFinished = false;
        this.hashtagList = null;
    }

    public Note(String title, String body, List<String> hashtagList) {
        this.title = title;
        this.body = body;
        this.creationDate = System.currentTimeMillis();
        this.modificationDate = System.currentTimeMillis();
        this.markAsFinished = false;
        this.hashtagList = hashtagList;
    }

    public Note(String title, String body, long creationDate, long modificationDate, boolean markAsFinished, List<String> hashtagList) {
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
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

    public long getCreationDate() {
        return this.creationDate;
    }

    public long getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(long modificationDate) {
        this.modificationDate = modificationDate;
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
                "Created on: " + sdf.format(creationDate) + "\n" +
                "Last modified on: " + sdf.format(modificationDate) + "\n" +
                body + "\n" +
                "Note is marked as done: " + markAsFinished + "\n" +
                "Hashtags " + hashtagList.toString() + "." + "\n\n";
    }
}

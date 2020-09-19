package ro.var.noteapplication.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Note {

    private String title;
    private String body;
    private long creationDate;
    private long modificationDate;
    private List<String> hashtagList = new ArrayList<>();

    public Note() {
    }

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
        this.creationDate = System.currentTimeMillis();
        this.modificationDate = System.currentTimeMillis();
        this.hashtagList = null;
    }

    public Note(String title, String body, List<String> hashtagList) {
        this.title = title;
        this.body = body;
        this.creationDate = System.currentTimeMillis();
        this.modificationDate = System.currentTimeMillis();
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

    public long getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(long modificationDate) {
        this.modificationDate = modificationDate;
    }

    public List<String> getHashtagList() {
        return hashtagList;
    }

    public void setHashtagList(List<String> hashtagList) {
        this.hashtagList = hashtagList;
    }


    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return "Note " + title + "\n" +
                "Created on " + sdf.format(creationDate) + "\n" +
                "Last modified on" + sdf.format(modificationDate) + "\n" +
                body + "\n" +
                "Hashtags " + hashtagList.toString() + ".";
    }
}

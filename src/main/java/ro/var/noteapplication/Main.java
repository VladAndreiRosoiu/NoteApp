package ro.var.noteapplication;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ro.var.noteapplication.models.Note;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Read from JSON
        JSONParser jsonParser = new JSONParser();
        try {
            Reader reader = new FileReader(Main.class.getClassLoader().getResource("notes.json").getFile());
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            List<Note> noteList=new ArrayList<>();
            for (Object object:jsonArray){
                JSONObject noteObject= (JSONObject) object; 
                String title=(String) noteObject.get("title");
                String body=(String) noteObject.get("body");
                Long creationDate = (Long) noteObject.get("creationDate");
                Long modificationDate = (Long) noteObject.get("modificationDate");
                Boolean markAsFinished = (Boolean) noteObject.get("markAsFinished");
                List hashtagList=(List) noteObject.get("hashtagList");

                Note note = new Note(title,body,creationDate,modificationDate,markAsFinished,hashtagList);
                noteList.add(note);
            }

            noteList.forEach(note -> System.out.println(note));

        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }


    }

//    private static void showDisplayMenu() {
//        System.out.println("1 - Display notes by creation date(latest first)");
//        System.out.println("2 - Display notes by creation date(oldest first)");
//        System.out.println("3 - Display notes by edit date(latest first)");
//        System.out.println("4 - Display notes by edit date(oldest first)");
//        System.out.println("5 - Return to main menu");
//    }
//
//    private static void showMenu() {
//        System.out.println("Note's");
//        System.out.println("1 - Take a new note");
//        System.out.println("2 - Display notes");
//        System.out.println("3 - Show note content");
//        System.out.println("4 - Delete note");
//        System.out.println("5 - Exit");
//    }
}
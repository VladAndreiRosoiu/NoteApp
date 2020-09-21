package ro.var.noteapplication;

import ro.var.noteapplication.models.Note;
import ro.var.noteapplication.services.IOService;
import ro.var.noteapplication.services.IOServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        File getNotesFileForReading = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("notes.json")).getFile());
        File getNotesFileForWriting = new File("src/main/resources/notes.json");

        //added if-else for windows users -> getResource() inserts "%20" in blank spaces path

        if (getNotesFileForReading.getPath().contains("%20")){
            String correctPath = getNotesFileForReading.getPath().replace("%20", " ");
            File notesFileCorrectPath=new File(correctPath);
            IOService readWriteService = new IOServiceImpl();
            List<Note> noteList = new ArrayList<>(readWriteService.readFile(notesFileCorrectPath));

            //testing reading file
            System.out.println("Before edit:");
            noteList.stream().forEach(note-> System.out.println(note));


            //editing first note's title and writing it to notes.json
            System.out.println("After editing:");
            noteList.get(0).setTitle("Note application!");
            readWriteService.writeFile(noteList,getNotesFileForWriting);
            noteList.stream().forEach(note-> System.out.println(note));

        }else {
            IOService readWriteService = new IOServiceImpl();
            List<Note> noteList = new ArrayList<>(readWriteService.readFile(getNotesFileForReading));
            noteList.forEach(System.out::println);
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
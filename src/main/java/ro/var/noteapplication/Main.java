package ro.var.noteapplication;

import ro.var.noteapplication.models.Note;
import ro.var.noteapplication.models.NoteApp;
import ro.var.noteapplication.services.IOService;
import ro.var.noteapplication.services.IOServiceImpl;

import java.io.*;
import java.util.Objects;

public class Main {

   private static final File getNotesFileForReading = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("notes.json")).getFile());
   private static final File getNotesFileForWriting = new File("src/main/resources/notes.json");

    public static void main(String[] args) {

        IOService ioService = new IOServiceImpl();

        if (getNotesFileForReading.getPath().contains("%20")){
            String correctPath = getNotesFileForReading.getPath().replace("%20", " ");
            File notesFileCorrectPath=new File(correctPath);
            NoteApp noteApp=new NoteApp(ioService.readFile(notesFileCorrectPath), getNotesFileForWriting);
            noteApp.openNoteApp();

        }else {
           NoteApp noteApp=new NoteApp(ioService.readFile(getNotesFileForReading), getNotesFileForWriting);
           noteApp.openNoteApp();
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
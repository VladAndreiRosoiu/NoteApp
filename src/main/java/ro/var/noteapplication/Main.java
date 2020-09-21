package ro.var.noteapplication;

import ro.var.noteapplication.models.NoteApp;
import ro.var.noteapplication.services.IOService;
import ro.var.noteapplication.services.IOServiceImpl;

import java.io.File;
import java.util.Objects;

public class Main {

    private static final File getNotesFileForReading = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("notes.json")).getFile());
    private static final File getNotesFileForWriting = new File("src/main/resources/notes.json");

    public static void main(String[] args) {

        IOService ioService = new IOServiceImpl();

        if (getNotesFileForReading.getPath().contains("%20")) {
            String correctPath = getNotesFileForReading.getPath().replace("%20", " ");
            File notesFileCorrectPath = new File(correctPath);
            NoteApp noteApp = new NoteApp(ioService.readFile(notesFileCorrectPath), getNotesFileForWriting);
            noteApp.openNoteApp();
        } else {
            NoteApp noteApp = new NoteApp(ioService.readFile(getNotesFileForReading), getNotesFileForWriting);
            noteApp.openNoteApp();
        }
    }
}
package ro.var.noteapplication;

import ro.var.noteapplication.models.NoteApp;
import ro.var.noteapplication.services.IOService;
import ro.var.noteapplication.services.IOServiceImpl;

import java.io.File;
import java.util.Objects;

public class Main {

    private static final File NOTES_FILE_FOR_READING = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("notes.json")).getFile());
    private static final File NOTES_FILE_FOR_WRITING = new File("src/main/resources/notes.json");

    public static void main(String[] args) {

        IOService ioService = new IOServiceImpl();
        if (NOTES_FILE_FOR_READING.getPath().contains("%20")) {
            String correctPath = NOTES_FILE_FOR_READING.getPath().replace("%20", " ");
            File notesFileWithCorrectPath = new File(correctPath);
            NoteApp noteApp = new NoteApp(ioService.readFile(notesFileWithCorrectPath), NOTES_FILE_FOR_WRITING);
            noteApp.openNoteApp();
        } else {
            NoteApp noteApp = new NoteApp(ioService.readFile(NOTES_FILE_FOR_READING), NOTES_FILE_FOR_WRITING);
            noteApp.openNoteApp();
        }
    }
}
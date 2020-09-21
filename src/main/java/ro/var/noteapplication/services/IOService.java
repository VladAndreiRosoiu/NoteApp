package ro.var.noteapplication.services;

import ro.var.noteapplication.models.Note;

import java.io.File;
import java.util.List;


public interface IOService {
    public List<Note> readFile(File file);

    public void writeFile(List<Note> noteList, File file);
}

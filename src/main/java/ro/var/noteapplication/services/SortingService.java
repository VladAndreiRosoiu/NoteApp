package ro.var.noteapplication.services;

import ro.var.noteapplication.models.Note;

import java.util.List;

public interface SortingService {
    public List<Note> latestNotesFirst(List<Note> noteList);

    public List<Note> oldestNotesFirst(List<Note> noteList);

    public List<Note> latestEditedNotesFirst(List<Note> noteList);

    public List<Note> oldestEditedNotesFirst(List<Note> noteList);

    public List<Note> unfinishedNotes(List<Note> noteList);
}

package ro.var.noteapplication.services;

import ro.var.noteapplication.models.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortingServiceImpl implements SortingService {

    @Override
    public List<Note> latestNotesFirst(List<Note> noteList) {
        List<Note> sortedList = new ArrayList<>(noteList);
        sortedList.sort((note, t1) -> (int) (t1.getCreationDate() - note.getCreationDate()));
        return sortedList;
    }

    @Override
    public List<Note> oldestNotesFirst(List<Note> noteList) {
        List<Note> sortedList = new ArrayList<>(noteList);
        sortedList.sort((note, t1) -> (int) (note.getCreationDate() - t1.getCreationDate()));
        return sortedList;
    }

    @Override
    public List<Note> latestEditedNotesFirst(List<Note> noteList) {
        List<Note> sortedList = new ArrayList<>(noteList);
        sortedList.sort((note, t1) -> (int) (t1.getModificationDate() - note.getModificationDate()));
        return sortedList;
    }

    @Override
    public List<Note> oldestEditedNotesFirst(List<Note> noteList) {
        List<Note> sortedList = new ArrayList<>(noteList);
        sortedList.sort((note, t1) -> (int) (note.getModificationDate() - t1.getModificationDate()));
        return sortedList;
    }

    @Override
    public List<Note> unfinishedNotes(List<Note> noteList) {
        return new ArrayList<>(noteList.stream().filter(note -> !note.isMarkAsFinished()).collect(Collectors.toSet()));
    }
}

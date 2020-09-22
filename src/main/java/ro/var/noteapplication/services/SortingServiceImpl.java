package ro.var.noteapplication.services;

import ro.var.noteapplication.models.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortingServiceImpl implements SortingService {

    @Override
    public List<Note> latestNotesFirst(List<Note> noteList) {
        List<Note> sortedList = new ArrayList<>(noteList);
        sortedList.sort((note, t1) -> (int) (t1.getWriteDate() - note.getWriteDate()));
        return sortedList;
    }

    @Override
    public List<Note> oldestNotesFirst(List<Note> noteList) {
        List<Note> sortedList = new ArrayList<>(noteList);
        sortedList.sort((note, t1) -> (int) (note.getWriteDate() - t1.getWriteDate()));
        return sortedList;
    }

    @Override
    public List<Note> latestEditedNotesFirst(List<Note> noteList) {
        List<Note> sortedList = new ArrayList<>(noteList);
        sortedList.sort((note, t1) -> (int) (t1.getModifiedOnDate() - note.getModifiedOnDate()));
        return sortedList;
    }

    @Override
    public List<Note> oldestEditedNotesFirst(List<Note> noteList) {
        List<Note> sortedList = new ArrayList<>(noteList);
        sortedList.sort((note, t1) -> (int) (note.getModifiedOnDate() - t1.getModifiedOnDate()));
        return sortedList;
    }

    @Override
    public List<Note> unfinishedNotes(List<Note> noteList) {
        return new ArrayList<>(noteList.stream().filter(note -> !note.isMarkAsFinished()).collect(Collectors.toList()));
    }
}

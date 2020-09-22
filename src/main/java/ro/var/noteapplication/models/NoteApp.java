package ro.var.noteapplication.models;

import ro.var.noteapplication.services.IOService;
import ro.var.noteapplication.services.IOServiceImpl;
import ro.var.noteapplication.services.SortingService;
import ro.var.noteapplication.services.SortingServiceImpl;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class NoteApp {
    private final List<Note> noteList;
    private final File notesFile;
    private final IOService ioService = new IOServiceImpl();
    private final SortingService sortingService = new SortingServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    public NoteApp(List<Note> noteList, File notesFile) {
        this.noteList = noteList;
        this.notesFile = notesFile;
    }

    public void openNoteApp() {
        try {
            do {
                printNoteAppHeader();
                printNoteAppMainMenu();
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        addNewNote();
                        break;
                    case 2:
                        displayOptions();
                        break;
                    case 3:
                        selectNote(Objects.requireNonNull(searchNotes()));
                        break;
                    case 4:
                        editNote(selectNote(Objects.requireNonNull(searchNotes())));
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please choose between [1-4] only!");
                        openNoteApp();
                        break;
                }
            } while (true);
        } catch (InputMismatchException e) {
            System.out.println("Please choose between [1-4] only!");
            scanner = new Scanner(System.in);
            openNoteApp();
        }
    }

    private void printNoteAppHeader() {
        System.out.println("--------------------------");
        System.out.println("|    Note Application    |");
        System.out.println("--------------------------");
    }

    private void printNoteAppMainMenu() {
        System.out.println("1 - Take a new note");
        System.out.println("2 - Display notes");
        System.out.println("3 - Search note");
        System.out.println("4 - Edit note");
        System.out.println("5 - Exit application");
        System.out.println("Please enter an option:");
    }

    private void printNoteAppDisplayMenu() {
        System.out.println("Display menu");
        System.out.println("1 - Display latest notes first");
        System.out.println("2 - Display oldest notes first");
        System.out.println("3 - Display notes by edit date(latest first)");
        System.out.println("4 - Display notes by edit date(oldest first)");
        System.out.println("5 - Display unfinished notes");
        System.out.println("6 - Return to main menu");
        System.out.println("Please enter an option:");
    }

    private void printNoteEditMenu() {
        System.out.println("Edit menu");
        System.out.println("1 - Edit title");
        System.out.println("2 - Edit body");
        System.out.println("3 - Edit hashtag list");
        System.out.println("4 - Mark note as finished");
        System.out.println("5 - Delete note");
        System.out.println("6 - Return to main menu");
        System.out.println("Please enter an option:");
    }

    private void printSearchMenu() {
        System.out.println("Search menu");
        System.out.println("1 - Search by title");
        System.out.println("2 - Search by hashtag");
        System.out.println("3 - Search by keyword");
        System.out.println("4 - Return to main menu");
        System.out.println("Please enter an option:");
    }

    private void addNewNote() {
        System.out.println("Please enter note title:");
        scanner.skip("\n");
        String title = scanner.nextLine();
        System.out.println("Please enter note body:");
        String body = scanner.nextLine();
        System.out.println("Please enter hashtag/s:");
        String hashtag = scanner.nextLine();
        List<String> hashtagList = new ArrayList<>();
        if (hashtag.contains("#")) {
            String[] arrayHashtagList = hashtag.split("#");
            for (int i = 0; i < arrayHashtagList.length; i++) {
                arrayHashtagList[i] = "#" + arrayHashtagList[i];
                hashtagList.add(arrayHashtagList[i]);
            }
            hashtagList.remove(0);
        } else {
            System.out.println("Invalid input!");
        }
        noteList.add(new Note(title, body, System.currentTimeMillis(), System.currentTimeMillis(), false, hashtagList));
        System.out.println("Note successfully added!");
        ioService.writeFile(noteList, notesFile);
    }

    private void displayOptions() {
        try {
            printNoteAppDisplayMenu();
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    printNotes(sortingService.latestNotesFirst(noteList));
                    break;
                case 2:
                    printNotes(sortingService.oldestNotesFirst(noteList));
                    break;
                case 3:
                    printNotes(sortingService.latestEditedNotesFirst(noteList));
                    break;
                case 4:
                    printNotes(sortingService.oldestEditedNotesFirst(noteList));
                    break;
                case 5:
                    printNotes(sortingService.unfinishedNotes(noteList));
                case 6:
                    openNoteApp();
                    break;
                default:
                    System.out.println("Invalid input. Please choose between [1-6] only!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please choose between [1-6] only!");
            scanner = new Scanner(System.in);
            displayOptions();
        }
    }

    private void printNotes(List<Note> notes) {
        notes.forEach(System.out::println);
    }

    private List<Note> searchNoteByTitle(String keyword) {
        return new ArrayList<>((noteList.stream().filter(note -> note.getTitle().toLowerCase()
                .contains(keyword.toLowerCase()))
                .collect(Collectors.toList())));
    }

    private List<Note> searchNoteByBodyKeyword(String keyword) {
        return new ArrayList<>(noteList.stream().filter(note -> note.getBody().toLowerCase()
                .contains(keyword.toLowerCase()))
                .collect(Collectors.toList()));
    }

    private List<Note> searchNoteByHashtag(String keyword) {
        List<Note> tempList = new ArrayList<>();
        for (Note note : noteList) {
            for (String hashtag : note.getHashtagList()) {
                if (hashtag.toLowerCase().contains(keyword.toLowerCase())) {
                    if (!tempList.contains(note)) {
                        tempList.add(note);
                    }
                }
            }
        }
        return tempList;
    }

    private List<Note> searchNotes() {
        try {
            printSearchMenu();
            int option = scanner.nextInt();
            String keyword;
            switch (option) {
                case 1:
                    scanner.skip("\n");
                    System.out.println("Please enter title to search");
                    keyword = scanner.nextLine();
                    return searchNoteByTitle(keyword);
                case 2:
                    scanner.skip("\n");
                    System.out.println("Please enter hashtag to search");
                    keyword = scanner.nextLine();
                    return searchNoteByHashtag(keyword);
                case 3:
                    scanner.skip("\n");
                    System.out.println("Please enter keyword to search");
                    keyword = scanner.nextLine();
                    return searchNoteByBodyKeyword(keyword);
                case 4:
                    openNoteApp();
                default:
                    System.out.println("Invalid input. Please choose between [1-4] only!");
            }
            return null;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please choose between [1-4] only!");
            scanner = new Scanner(System.in);
            searchNotes();
            return null;
        }
    }

    private Note selectNote(List<Note> notes) {
        for (int i = 0; i < notes.size(); i++) {
            System.out.println((i + 1) + " - " + notes.get(i));
        }
        System.out.println("Please select one note:");
        int selectedNote = scanner.nextInt();
        if (selectedNote >= 0 && selectedNote <= notes.size()) {
            System.out.println("Selected note is:\n" + notes.get(selectedNote - 1));
            return notes.get(selectedNote - 1);
        } else {
            System.out.println("Invalid input!");
            return null;
        }
    }

    private void editNote(Note currentNote) {
        try {
            if (currentNote != null) {
                printNoteEditMenu();
                int option = scanner.nextInt();
                String keyword;
                switch (option) {
                    case 1:
                        scanner.skip("\n");
                        System.out.println("Please enter new title");
                        keyword = scanner.nextLine();
                        editNoteTitle(currentNote, keyword);
                        break;
                    case 2:
                        scanner.skip("\n");
                        System.out.println("Please enter new body");
                        keyword = scanner.nextLine();
                        editNoteBody(currentNote, keyword);
                        break;
                    case 3:
                        scanner.skip("\n");
                        System.out.println("Please enter hashtags");
                        keyword = scanner.nextLine();
                        editHashtag(currentNote, keyword);
                        break;
                    case 4:
                        System.out.println("Do you want to mark current note as finished? Y/N:");
                        String answer = scanner.next();
                        if (answer.equalsIgnoreCase("y")) {
                            currentNote.setMarkAsFinished(true);
                        } else {
                            System.out.println("Nothing has changed, note has not been marked as finished!");
                        }
                        break;
                    case 5:
                        System.out.println("Do you want to delete current note? Y/N:");
                        String deleteAnswer = scanner.next();
                        if (deleteAnswer.equalsIgnoreCase("y")) {
                            noteList.remove(currentNote);
                            System.out.println("Note successfully deleted!");
                        } else {
                            System.out.println("Nothing has changed, note has not been deleted!");
                        }
                        break;
                    case 6:
                        openNoteApp();
                        break;
                    default:
                        System.out.println("Invalid input. Please choose between [1-6] only!");
                }
            } else {
                System.out.println("Invalid note!");
            }
            ioService.writeFile(noteList, notesFile);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please choose between [1-6] only!");
            scanner = new Scanner(System.in);
            editNote(currentNote);
        }
    }

    private void editNoteTitle(Note note, String title) {
        note.setTitle(title);
        note.setModifiedOnDate(System.currentTimeMillis());
    }

    private void editNoteBody(Note note, String body) {
        note.setBody(body);
        note.setModifiedOnDate(System.currentTimeMillis());
    }

    private void editHashtag(Note note, String hashtag) {
        note.getHashtagList().removeAll(note.getHashtagList());
        List<String> hashtagList = new ArrayList<>();
        if (hashtag.contains("#")) {
            String[] arrayHashtagList = hashtag.split("#");
            for (int i = 0; i < arrayHashtagList.length; i++) {
                arrayHashtagList[i] = "#" + arrayHashtagList[i];
                hashtagList.add(arrayHashtagList[i]);
            }
            hashtagList.remove(0);
        } else {
            System.out.println("Invalid input!");
        }
        note.setHashtagList(hashtagList);
        note.setModifiedOnDate(System.currentTimeMillis());
    }
}

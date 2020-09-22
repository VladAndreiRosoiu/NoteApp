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
                        //TODO - try catch
                        addNewNote();
                        ioService.writeFile(noteList, notesFile);
                        break;
                    case 2:
                        //TODO - try catch
                        displayNotes();
                        break;
                    case 3:
                        //TODO - search note
                    case 4:
                        //TODO - try catch
                        editNote();
                        ioService.writeFile(noteList, notesFile);
                        break;
                    case 5:
                        //TODO - try catch
                        System.exit(0);
                        break;
                    default:
                        //TODO - try catch
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
        System.out.println("4 - Delete note");
        System.out.println("5 - Return to main menu");
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
        List<String> hashtagList = new ArrayList<>(Arrays.asList(hashtag.split("#")));
        noteList.add(new Note(title, body, System.currentTimeMillis(), System.currentTimeMillis(), false, hashtagList));

        //TODO - fix adding hashtags

//        String[] hashtags = input.nextLine().replace(" ", "").split(",");
//        for (int i = 0; i < hashtags.length; i++) {
//        hashtags[i] = "#" + hashtags[i];
//        }
    }

    private void displayNotes() {
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
                System.out.println("Error");
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
                    if (!tempList.contains(note)){
                        tempList.add(note);
                    }
                }
            }
        }
        return tempList;
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
        List<String> hashtags = new ArrayList<>(Arrays.asList(hashtag.split("#")));
        note.setHashtagList(hashtags);
        note.setModifiedOnDate(System.currentTimeMillis());
    }

    private Note selectNote(List<Note> notes) {
        for (int i = 0; i < notes.size(); i++) {
            System.out.println((i + 1) + " - " + notes.get(i));
        }
        System.out.println("Please select one note:");
        int selectedNote = scanner.nextInt();
        return notes.get(selectedNote - 1);
    }

    private List<Note> searchNotes() {
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
                System.out.println("default");
        }
        return null;
    }

    private void editNote() {
        Note currentNote = selectNote(Objects.requireNonNull(searchNotes()));
        System.out.println("Selected note is:" + currentNote);
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
                //TODO - mark note as finished
                break;
            case 5:
                //TODO - delete note
                break;
            case 6:
                openNoteApp();
                break;
            default:
                System.out.println("Error");
        }
    }

}

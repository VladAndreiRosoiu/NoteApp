package ro.var.noteapplication.models;

import org.w3c.dom.ls.LSOutput;
import ro.var.noteapplication.services.IOService;
import ro.var.noteapplication.services.IOServiceImpl;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class NoteApp {
    private List<Note> noteList;
    private File notesFile;
    IOService ioService=new IOServiceImpl();
    private Scanner scanner=new Scanner(System.in);

    public NoteApp(List<Note> noteList, File notesFile){
        this.noteList=noteList;
        this.notesFile=notesFile;
    }

    public void openNoteApp(){
        do {
            printNoteAppMainMenu();
            int option=scanner.nextInt();
            switch (option){
                case 1:
                    //TODO
                    System.out.println("1");
                    break;
                case 2:
                    //TODO
                    System.out.println("2");
                    break;
                case 3:
                    //TODO
                    System.out.println("3");
                    break;
                case 4:
                    //TODO
                    System.out.println("4");
                    break;
                case 5:
                    //TODO
                    System.out.println("5");
                    break;
                case 6:
                    //TODO
                    System.out.println("6");
                    break;
                case 7:
                    //TODO
                    System.out.println("7");
                    break;
                default:
                    //TODO
                    System.out.println("default");
                    break;
            }
        }while (true);
    }

    private void printNoteAppHeader(){
        System.out.println("--------------------------");
        System.out.println("|    Note Application    |");
        System.out.println("--------------------------");
    }

    private void printNoteAppMainMenu(){}

    private void printNoteAppDisplayMenu(){}

    private void printNoteEditMenu(){}

}

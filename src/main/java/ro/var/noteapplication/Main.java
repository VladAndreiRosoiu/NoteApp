package ro.var.noteapplication;


public class Main {

    public static void main(String[] args) {

        do {
            System.out.println("Test");
            System.out.println("Test");
            System.out.println("Test");
        }while (true);
    }

    private static void showDisplayMenu() {
        System.out.println("1 - Display notes by creation date(latest first)");
        System.out.println("2 - Display notes by creation date(oldest first)");
        System.out.println("3 - Display notes by edit date(latest first)");
        System.out.println("4 - Display notes by edit date(oldest first)");
        System.out.println("5 - Return to main menu");
    }

    private static void showMenu() {
        System.out.println("Note's");
        System.out.println("1 - Take a new note");
        System.out.println("2 - Display notes");
        System.out.println("3 - Show note content");
        System.out.println("4 - Delete note");
        System.out.println("5 - Exit");
    }
}
package by.epam.m6_t1.view;

import by.epam.m6_t1.entity.Admin;
import by.epam.m6_t1.entity.Library;
import by.epam.m6_t1.logic.AdminLogic;
import by.epam.m6_t1.logic.UserLogic;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;

public class MainView {
    public static void printMessage() {
        System.out.println("Welcome to the Library program.");
    }

    public static int enterAnswer(int start, int end) {
        Scanner in = new Scanner(System.in);
        int answer;

        do {
            answer = in.nextInt();
        } while (answer < start || answer > end);

        return answer;
    }

    private static void pagination(int booksLength, UserLogic userLogic) {
        Scanner in = new Scanner(System.in);
        int pages = (booksLength % 3 == 0) ? booksLength / 3 : booksLength + 1;
        int page = 1;

        System.out.println("What page would you like to go?");
        while (true) {
            System.out.println("1.Next\n2.Previous\n3.Exit");
            System.out.print("Enter your answer: ");

            int pageAnswer = in.nextInt();

            if (pageAnswer == 1) {
                if (page + 1 > pages) {
                    page = 0;
                }
                userLogic.showBooks(page + 1);
                page++;
            } else if (pageAnswer == 2) {
                if (page - 1 <= 0) {
                    page = pages + 1;
                }
                userLogic.showBooks(page - 1);
                page--;
            } else {
                break;
            }
        }

    }

    private static void searchBook(UserLogic userLogic) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter book name: ");
        String bookName = in.next();

        userLogic.searchBook(bookName);
    }

    private static void deleteBook(AdminLogic adminLogic, LibraryView libraryView) {
        int bookId;

        System.out.println("Select book to delete.");
        libraryView.printLibraryWithId(adminLogic.getLibrary().getBooks());
        System.out.print("Enter your answer: ");
        bookId = MainView.enterAnswer(1, adminLogic.getLibrary().getBooks().size());

        adminLogic.deleteBook(bookId - 1);
    }

    private static void editBook(AdminLogic adminLogic, LibraryView libraryView) {
        int bookId;

        System.out.println("Select book to edit");
        libraryView.printLibraryWithId(adminLogic.getLibrary().getBooks());
        System.out.print("Enter your answer: ");
        bookId = MainView.enterAnswer(1, adminLogic.getLibrary().getBooks().size());

        adminLogic.editBook(bookId - 1, UserView.enterBookInfo());
    }

    public static void adminActions(ArrayList<String> usersEmails) throws Exception {
        Scanner in = new Scanner(System.in);
        AdminLogic adminLogic = new AdminLogic();
        AdminView adminView = new AdminView();
        LibraryView libraryView = new LibraryView();
        int answer;

        adminView.showUserMessage();

        System.out.print("Enter your answer: ");
        answer = in.nextInt();

        switch (answer) {
            case 1:
                adminLogic.showBooks();
                pagination(adminLogic.getLibrary().getBooks().size(), adminLogic);
                break;
            case 2:
                searchBook(adminLogic);
                break;
            case 3:
                adminLogic.addBook(UserView.enterBookInfo(), usersEmails);
                break;
            case 4:
                deleteBook(adminLogic, libraryView);
                break;
            case 5:
                editBook(adminLogic, libraryView);
                break;
            default:
                System.out.println("Error!");
        }
    }

    public static void userActions(ArrayList<String> adminsEmails) throws Exception {
        UserLogic userLogic = new UserLogic();
        UserView userView = new UserView();
        Scanner in = new Scanner(System.in);
        String bookName;
        int answer;

        userView.showUserMessage();
        System.out.print("Enter your answer: ");
        answer = in.nextInt();

        switch (answer) {
            case 1:
                userLogic.showBooks();
                pagination(userLogic.getLibrary().getBooks().size(), userLogic);
                break;
            case 2:
                searchBook(userLogic);
                break;
            case 3:
                userLogic.addBook(UserView.enterBookInfo(), adminsEmails);
                break;
            default:
                System.out.println("Error!");
        }
    }

    public static int continueAction() {
        Scanner in = new Scanner(System.in);

        System.out.println("Would you like to continue?");
        System.out.println("1.Yes");
        System.out.println("2.No");
        System.out.print("Enter your answer: ");

        int answer = in.nextInt();

        return answer;
    }
}

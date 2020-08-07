package by.epam.m6_t1.logic;

import by.epam.m6_t1.entity.Book;
import by.epam.m6_t1.entity.Library;
import by.epam.m6_t1.view.LibraryView;

import javax.mail.internet.InternetAddress;
import java.util.ArrayList;

public class UserLogic {
    private LibraryView libraryView;
    private LibraryLogic libraryLogic;
    private Library library;

    public UserLogic (){
        libraryView = new LibraryView();
        libraryLogic = new LibraryLogic();
        library = libraryLogic.generateLibrary();
    }

    public Library getLibrary() {
        return this.library;
    }

    public void showBooks() {
        library = libraryLogic.generateLibrary();
        libraryView.printLibrary(library.getBooks());
    }

    public void showBooks(int page) {
        library = libraryLogic.generateLibrary();
        libraryView.printLibrary(library.getBooks(), page);
    }

    public void searchBook(String name) {
        library = libraryLogic.generateLibrary();
        boolean flag = false;

        for (Book book:library.getBooks()) {
            if (book.getName().equals(name)) {
                System.out.println();
                System.out.println(libraryView.printBook(book));
                flag = true;
            }
        }

        if (!flag) {
            System.out.println("Sorry, I can't find this book.");
        }
    }

    public void addBook(Book book, ArrayList<String> adminEmails) throws Exception {
        String type = (book.isElectro()) ? "e-book" : "book";
        String mailText = "Book name: " + book.getName() + "\n" + "Description: " + book.getDescription() + "\n" + "Type: " + type;

        Sender.sendMail(convertEmails(adminEmails), mailText);
    }

    public InternetAddress[] convertEmails(ArrayList<String> usersEmails) throws Exception{
        InternetAddress[] emails = new InternetAddress[usersEmails.size()];

        for (int i = 0; i < emails.length; i++) {
            emails[i] = new InternetAddress(usersEmails.get(i));
        }

        return emails;
    }
}

package by.epam.m6_t1.logic;

import by.epam.m6_t1.entity.Book;
import by.epam.m6_t1.entity.Library;

import javax.mail.internet.InternetAddress;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AdminLogic extends UserLogic {

    public AdminLogic() {
        super();
    }

    public void addBook(Book book, ArrayList<String> usersEmails) throws Exception {
        String bookType = (book.isElectro()) ? "e-book" : "book";

        try (FileWriter writer = new FileWriter("lib.txt", true)) {
            writer.write(book.getName() + " " + book.getDescription() + " " + bookType + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        sendBook(book, super.convertEmails(usersEmails));
    }

    public void deleteBook(int id) {
        Library lib = getLibrary();
        lib.getBooks().remove(id);

        overwrite(lib.getBooks());
    }

    public void editBook(int id, Book book) {
        Library lib = getLibrary();

        lib.getBooks().get(id).setName(book.getName());
        lib.getBooks().get(id).setDescription(book.getDescription());
        lib.getBooks().get(id).setElectro(book.isElectro());

        overwrite(lib.getBooks());
    }


    private void overwrite(ArrayList<Book> books) {
        try (FileWriter writer = new FileWriter("lib.txt", false)) {
            for (Book book:books) {
                String type = (book.isElectro()) ? "e-book" : "book";
                writer.write(book.getName() + " " + book.getDescription() + " " + type + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void sendBook(Book book, InternetAddress[] emails) throws Exception {
        String bookType = (book.isElectro()) ? "e-book" : "book";
        String messageText = "Name: " + book.getName() + "\nDescription: " + book.getDescription() + "\nType: " + bookType + "\n";
        Sender.sendMail(emails, messageText);
    }
}

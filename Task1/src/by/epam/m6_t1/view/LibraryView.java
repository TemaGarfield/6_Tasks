package by.epam.m6_t1.view;

import by.epam.m6_t1.entity.Book;
import java.util.ArrayList;




public class LibraryView {

    public void printLibraryWithId(ArrayList<Book> books) {

        int id = 1;
        for (Book book:books) {
            System.out.println(id + ". " + printBook(book));
            System.out.println();
            id++;
        }
    }


    public void printLibrary(ArrayList<Book> books)  {
        for (int i = 0; i < 3; i++) {
            System.out.println(printBook(books.get(i)));
            System.out.println();
        }

    }

    public void printLibrary(ArrayList<Book> books, int page) {
        if (page * 3 < books.size()) {
            for (int i = page * 3 - 3; i < page * 3; i++) {
                System.out.println(printBook(books.get(i)));
                System.out.println();
            }
        } else {
            for (int i = page * 3 - 3; i < books.size(); i++) {
                System.out.println(printBook(books.get(i)));
                System.out.println();
            };
        }
//        if ()
//        for (int i = page * 3 - 3; i < page * 3; i++) {
//            System.out.println(printBook(books.get(i)));
//            System.out.println();
//        }
    }

    public String printBook(Book book) {
        String type = (book.isElectro()) ? "e-book" : "book";
        return "Book: " + book.getName() + "\nDescription: " + book.getDescription() + "\nType: " + type;
    }


}

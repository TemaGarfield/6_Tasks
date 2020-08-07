package by.epam.m6_t1.logic;

import by.epam.m6_t1.entity.Book;
import by.epam.m6_t1.entity.Library;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LibraryLogic {
    private String readFile() {
        String data = "";

        try (FileReader reader = new FileReader("lib.txt")){
            int c;
            while ((c = reader.read()) != -1) {
                data += (char) c;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return data;
    }

    private String[] splitData () {
        String data = readFile();
        String[] splitData = data.split("\n");

        return splitData;
    }

    public Library generateLibrary() {
        ArrayList<Book> books = new ArrayList<>();
        String[] data = splitData();

        for (String d:data) {
            String[] splitBySpace = d.split(" ");

            books.add(new Book(splitBySpace[0], splitBySpace[1], splitBySpace[2].equals("e-book")));
        }

        Library library = new Library(books);
        return library;
    }
}

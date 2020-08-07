package by.epam.m6_t1.view;

import by.epam.m6_t1.entity.Book;

import java.util.Scanner;

public class UserView{

    public void showUserMessage () {
        System.out.println("What would you like to do?");
        System.out.println("1. Watch books");
        System.out.println("2. Find book by name?");
        System.out.println("3. Add book");
    }


    public static Book enterBookInfo() {
        Scanner in = new Scanner(System.in);
        boolean type = false;

        System.out.print("Enter book name: ");
        String bookName = in.next();
        System.out.print("Enter book description: ");
        String description = in.next();
        System.out.println("Select type: ");
        System.out.println("1. e-book");
        System.out.println("2. book");
        int answer;
        do {
            answer = in.nextInt();
            if (answer == 1) {
                type = true;
            }
        } while (answer < 1 || answer > 2);

        return new Book(bookName, description, type);
    }
}

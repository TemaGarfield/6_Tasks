package by.epam.m6_t1.logic;

import by.epam.m6_t1.entity.Admin;
import by.epam.m6_t1.entity.User;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Authentication {
    private ArrayList<User> users = new ArrayList<>();
    private User user;
    private ArrayList<String> usersEmails = new ArrayList<>();
    private ArrayList<String> adminsEmails = new ArrayList<>();

    public Authentication() throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
        generateUserList();

        this.user = findUser();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<String> getUsersEmails() {
        return usersEmails;
    }

    public ArrayList<String> getAdminsEmails() {
        return adminsEmails;
    }

    private String readFile() {
        String data = "";

        try (FileReader reader = new FileReader("logins.txt")) {
            int c;
            while ((c = reader.read()) != -1) {
                 data += (char) c;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return data;
    }

    private String[] splitData() {
        String[] splitData = readFile().split("\n");

        return splitData;
    }

    private void generateUserList() {
        String[] splitData = splitData();

        for (String d:splitData()) {
            String[] splitBySpace = d.split(" ");
            if (splitBySpace[0].equals("A")) {
                users.add(new Admin(splitBySpace[1], splitBySpace[3], splitBySpace[2]));
                adminsEmails.add(splitBySpace[2]);
            } else {
                users.add(new User(splitBySpace[1], splitBySpace[3], splitBySpace[2]));
                usersEmails.add(splitBySpace[2]);
            }
        }
    }



    private User findUser() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Enter username: ");
            String username = in.next();
            System.out.print("Enter password: ");
            String password = in.next();
            for (User u : users) {
                if (u.getUsername().equals(username) && Security.decrypt(u.getPassword()).equals(password)) {
                    System.out.println("Good login!");
                    return u;
                }

            }
            System.out.println("Sorry!");
        }
    }
}

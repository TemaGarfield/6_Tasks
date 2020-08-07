package by.epam.m6_t1.main;

import by.epam.m6_t1.entity.Admin;
import by.epam.m6_t1.logic.Authentication;
import by.epam.m6_t1.view.MainView;

public class Main {
    public static void main(String[] args) throws Exception {
        MainView.printMessage();
        Authentication au = new Authentication();

        while (true) {
            if (au.getUser().getClass().equals(Admin.class)) {
                MainView.adminActions(au.getUsersEmails());
            } else {
                MainView.userActions(au.getAdminsEmails());
            }

            if (MainView.continueAction() != 1) {
                System.out.println("GoodBye!");
                break;
            }
        }
    }
}


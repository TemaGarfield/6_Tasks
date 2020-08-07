package by.epam.m6_t1.view;

public class AdminView extends UserView{
    public void showUserMessage() {
        super.showUserMessage();
        System.out.println("4. Delete book");
        System.out.println("5. Edit book");
    }
}

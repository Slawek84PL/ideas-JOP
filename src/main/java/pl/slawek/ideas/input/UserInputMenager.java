package pl.slawek.ideas.input;

import java.util.Scanner;

public class UserInputMenager {

    private Scanner scanner;

    public UserInputMenager() {
        scanner = new Scanner(System.in);
    }

    public UserInputCommand nextCommand() {
        return new UserInputCommand(scanner.nextLine());
    }
}

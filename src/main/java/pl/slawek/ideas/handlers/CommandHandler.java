package pl.slawek.ideas.handlers;

import pl.slawek.ideas.input.UserInputCommand;

public interface CommandHandler {
    void handle(UserInputCommand userInputCommand);

    boolean supports(String name);
}

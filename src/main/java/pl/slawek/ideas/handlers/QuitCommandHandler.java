package pl.slawek.ideas.handlers;

import pl.slawek.ideas.QuitIdeasApplicationException;
import pl.slawek.ideas.input.UserInputCommand;

public class QuitCommandHandler extends BaseCommandHandler {
    public static final String COMMAND_NAME = "quit";

    @Override
    public void handle(final UserInputCommand command) {
        throw new QuitIdeasApplicationException();
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}

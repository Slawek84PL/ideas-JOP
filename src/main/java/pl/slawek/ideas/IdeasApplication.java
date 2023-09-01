package pl.slawek.ideas;

import pl.slawek.ideas.handlers.*;
import pl.slawek.ideas.input.UserInputCommand;
import pl.slawek.ideas.input.UserInputMenager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

class IdeasApplication {

    private static Logger LOG = Logger.getLogger(IdeasApplication.class.getName());
    public static void main(String[] args) {
        new IdeasApplication().start();
    }

    private void start() {
        LOG.info("Start app...");

        boolean applicationLoop = true;

        List<CommandHandler> hanlers = new ArrayList<>();
        hanlers.add(new HelpCommandHandler());
        hanlers.add(new QuitCommandHandler());
        hanlers.add(new CategoryCommandHandler());
        hanlers.add(new QuestionCommandHandler());
        hanlers.add(new AnswerCommandHandler());

        UserInputMenager userInputMenager = new UserInputMenager();

        while (applicationLoop) {
            try {
                UserInputCommand userInputCommand = userInputMenager.nextCommand();
                LOG.info(userInputCommand.toString());

                Optional<CommandHandler> currentHandler = Optional.empty();
                for (CommandHandler handler : hanlers) {
                    if (handler.supports(userInputCommand.getCommand())) {
                        currentHandler = Optional.of(handler);
                        break;
                    }
                }

                currentHandler
                        .orElseThrow(() -> new IllegalStateException("Nie znaleziono handlera dla polecenia" + userInputCommand.getCommand()))
                        .handle(userInputCommand);

            } catch (QuitIdeasApplicationException e) {
                LOG.info("Quite...");
                applicationLoop = false;

            } catch (IllegalArgumentException e) {
                LOG.warning("Błąd walidacji " + e.getMessage());

            } catch (Exception e) {
                LOG.log(Level.SEVERE, "Nieznany błąd " + e);
            }
        }

    }
}

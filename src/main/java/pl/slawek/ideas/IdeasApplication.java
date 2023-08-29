package pl.slawek.ideas;

import pl.slawek.ideas.handlers.*;
import pl.slawek.ideas.input.UserInputCommand;
import pl.slawek.ideas.input.UserInputMenager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class IdeasApplication {

    public static void main(String[] args) {
        new IdeasApplication().start();
    }

    private void start() {
        System.out.println("Start app...");

        /**
         * category list -> categoryList()
         * category add CategoryName -> categoryAdd(name)
         *
         * quite -> quiteApplication
         *
         * question list -> questionList()
         * question add CategoryName QuestionName -> questionAdd(CategoryName, QuestionName)
         *
         * answer list QuestionName -> answerList(QuestionName)
         * answer add QuestionName AnswerName -> answerAdd(QuestionName, AnswerAdd)
         */

        boolean applicationLoop = true;

        List<CommandHandler> hanlers = new ArrayList<>();
        hanlers.add(new HelpCommandHandler());
        hanlers.add(new QuitCommandHandler());
        hanlers.add(new CategoryCommandHandler());
        hanlers.add(new QuestionCommandHandler());

        UserInputMenager userInputMenager = new UserInputMenager();

        while (applicationLoop) {
            try {
                UserInputCommand userInputCommand = userInputMenager.nextCommand();
                System.out.println(userInputCommand);

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
                System.out.println("Quite...");
                applicationLoop = false;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

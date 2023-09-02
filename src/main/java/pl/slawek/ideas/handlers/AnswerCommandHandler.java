package pl.slawek.ideas.handlers;

import pl.slawek.ideas.dao.CategoryDao;
import pl.slawek.ideas.dao.QuestionDao;
import pl.slawek.ideas.input.UserInputCommand;
import pl.slawek.ideas.model.Category;
import pl.slawek.ideas.model.Question;

import java.util.List;
import java.util.logging.Logger;

public class AnswerCommandHandler extends BaseCommandHandler {

    private static Logger LOG = Logger.getLogger(AnswerCommandHandler.class.getName());

    private static final String COMMAND_NAME = "answer";

    private QuestionDao questionDao;

    private CategoryDao categoryDao;

    private Question question;

    public AnswerCommandHandler() {
        this.categoryDao = new CategoryDao();
        this.questionDao = new QuestionDao();
    }

    @Override
    public void handle(final UserInputCommand command) {
        switch (command.getAction()) {
            case LIST:
                LOG.info("List of answer...");

                if (command.getParam().size() != 1) {
                    throw new IllegalArgumentException("Dodanie kategorii musi posiadać dwa parametry. Wpisz help po więcej informacji");
                }

                question = getQuestion(command.getParam().get(0));

                displayQuestion(question);
                break;

            case ADD:
                LOG.info("add answer");

                if (command.getParam().size() != 2) {
                    throw new IllegalArgumentException("Dodanie odpowiedzi musi posiadać dwa parametry. Wpisz help po więcej informacji");
                }

                question = getQuestion(command.getParam().get(0));
                String answerName = command.getParam().get(1);

                questionDao.addAnswer(question, answerName);


                break;

            default: {
                throw new IllegalArgumentException(String.format("Nieznana akcja: %s z komendy %s",
                        command.getAction(), command.getCommand()));
            }
        }
    }

    private Question getQuestion(final String questionName) {
        return questionDao.findOne(questionName)
                .orElseThrow(() -> new IllegalStateException("Nie ma takiego pytania jak " + questionName));
    }

    private void displayQuestion(Question question) {
        System.out.println(question);
        question.getAnswers().forEach(System.out::println);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

}

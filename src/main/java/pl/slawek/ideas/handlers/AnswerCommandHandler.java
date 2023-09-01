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

    public AnswerCommandHandler() {
        this.categoryDao = new CategoryDao();
        this.questionDao = new QuestionDao();
    }

    @Override
    public void handle(final UserInputCommand command) {
        switch (command.getAction()) {
            case LIST:
                LOG.info("List of answer...");

                if (!command.getParam().isEmpty()) {
                    throw new IllegalArgumentException("Lista pytań nie obsługuje parametrów. Wpisz help po więcej informacji");
                }

                if (command.getParam().size() != 1) {
                    throw new IllegalArgumentException("Dodanie kategorii musi posiadać dwa parametry. Wpisz help po więcej informacji");
                }

                String questionName = command.getParam().get(0);
                Question question = questionDao.findOne(questionName)
                        .orElseThrow(() -> new IllegalStateException("Nie ma takiego pytania jak " + questionName));

                displayQuestion(question);
                break;

            case ADD:
                LOG.info("add answer");

                if (command.getParam().size() != 1) {
                    throw new IllegalArgumentException("Dodanie kategorii musi posiadać dwa parametry. Wpisz help po więcej informacji");
                }

                questionName = command.getParam().get(0);
                String answerName = command.getParam().get(1);

                // TODO: 2023-09-01 od 15:23
                
//                Category category = categoryDao.findOne(categoryName).
//                        orElseThrow(() -> new IllegalStateException(String.format("Categoria %s nie znaleziona ", categoryName)));
//
//                questionDao.add(new Question(questionName, category));
                break;

            default: {
                throw new IllegalArgumentException(String.format("Nieznana akcja: %s z komendy %s",
                        command.getAction(), command.getCommand()));
            }
        }
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

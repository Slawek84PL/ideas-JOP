package pl.slawek.ideas.handlers;

import pl.slawek.ideas.dao.CategoryDao;
import pl.slawek.ideas.dao.QuestionDao;
import pl.slawek.ideas.input.UserInputCommand;
import pl.slawek.ideas.model.Category;
import pl.slawek.ideas.model.Question;

import java.util.List;
import java.util.logging.Logger;

public class QuestionCommandHandler extends BaseCommandHandler {

    private static Logger LOG = Logger.getLogger(QuestionCommandHandler.class.getName());

    private static final String COMMAND_NAME = "question";

    private QuestionDao questionDao;

    private CategoryDao categoryDao;

    public QuestionCommandHandler() {
        this.categoryDao = new CategoryDao();
        this.questionDao = new QuestionDao();
    }

    @Override
    public void handle(final UserInputCommand command) {
        switch (command.getAction()) {
            case LIST:
                LOG.info("List of question...");

                if (!command.getParam().isEmpty()) {
                    throw new IllegalArgumentException("Lista pytań nie obsługuje parametrów. Wpisz help po więcej informacji");
                }

                List<Question> questions = questionDao.findAll();
                questions.forEach(System.out::println);
                break;

            case ADD:
                LOG.info("add question");

                if (command.getParam().size() != 2) {
                    throw new IllegalArgumentException("Dodanie kategorii musi posiadać dwa parametry. Wpisz help po więcej informacji");
                }

                String categoryName = command.getParam().get(0);
                String questionName = command.getParam().get(1);
                Category category = categoryDao.findOne(categoryName).
                        orElseThrow(() -> new IllegalStateException(String.format("Categoria %s nie znaleziona ", categoryName)));

                questionDao.add(new Question(questionName, category));
                break;

            default: {
                throw new IllegalArgumentException(String.format("Nieznana akcja: %s z komendy %s",
                        command.getAction(), command.getCommand()));
            }
        }
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

}

package pl.slawek.ideas.handlers;

import pl.slawek.ideas.dao.CategoryDao;
import pl.slawek.ideas.dao.QuestionDao;
import pl.slawek.ideas.input.UserInputCommand;
import pl.slawek.ideas.model.Category;
import pl.slawek.ideas.model.Question;

import java.util.List;

public class QuestionCommandHandler extends BaseCommandHandler {
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
                System.out.println("List of question...");
                List<Question> questions = questionDao.findAll();
                questions.forEach(System.out::println);
                break;

            case ADD:
                System.out.println("add question");
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

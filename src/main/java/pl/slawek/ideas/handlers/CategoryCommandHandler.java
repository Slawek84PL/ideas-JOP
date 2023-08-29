package pl.slawek.ideas.handlers;

import pl.slawek.ideas.dao.CategoryDao;
import pl.slawek.ideas.input.UserInputCommand;
import pl.slawek.ideas.model.Category;

import java.util.List;
import java.util.logging.Logger;

public class CategoryCommandHandler extends BaseCommandHandler {

    private static Logger LOG = Logger.getLogger(CategoryCommandHandler.class.getName());

    private static final String COMMAND_NAME = "category";

    private CategoryDao categoryDao;

    public CategoryCommandHandler() {
        this.categoryDao = new CategoryDao();
    }

    @Override
    public void handle(final UserInputCommand command) {
        if (command.getAction() == null) {
            throw new IllegalArgumentException("Akcja nie może być pusta");
        }

        switch (command.getAction()) {
            case LIST:
                LOG.info("List of category...");

                if (!command.getParam().isEmpty()) {
                    throw new IllegalArgumentException("Lista kategorii nie obsługuje parametrów. Wpisz help po więcej informacji");
                }

                List<Category> categories = categoryDao.findAll();
                categories.forEach(System.out::println);
                break;

            case ADD:
                LOG.info("add category");

                if (command.getParam().size() != 1) {
                    throw new IllegalArgumentException("Dodanie kategorii obsługuje tylko jeden parametr. Wpisz help po więcej informacji");
                }

                String categoryName = command.getParam().get(0);
                categoryDao.add(new Category(categoryName));
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

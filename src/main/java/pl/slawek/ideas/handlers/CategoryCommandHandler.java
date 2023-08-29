package pl.slawek.ideas.handlers;

import pl.slawek.ideas.dao.CategoryDao;
import pl.slawek.ideas.input.UserInputCommand;
import pl.slawek.ideas.model.Category;

import java.util.List;

public class CategoryCommandHandler extends BaseCommandHandler {
    private static final String COMMAND_NAME = "category";

    private CategoryDao categoryDao;

    public CategoryCommandHandler() {
        this.categoryDao = new CategoryDao();
    }

    @Override
    public void handle(final UserInputCommand command) {
        switch (command.getAction()) {
            case LIST:
                System.out.println("List of category...");
                List<Category> categories = categoryDao.findAll();
                categories.forEach(System.out::println);
                break;

            case ADD:
                System.out.println("add category");
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

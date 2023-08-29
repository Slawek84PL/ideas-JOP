package pl.slawek.ideas.model;

public class Category {
    private String name;

    Category() {
    }

    public Category(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}

package pl.slawek.ideas.model;

public class Answer {
    private String name;

    String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "name='" + name + '\'' +
                '}';
    }
}

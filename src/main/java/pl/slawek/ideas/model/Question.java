package pl.slawek.ideas.model;

import java.util.List;

public class Question {
    private String name;

    private Category category;

    private List<Answer> answers;

    Question() {
    }

    public Question(final String name, final Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    void setCategory(final Category category) {
        this.category = category;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    void setAnswers(final List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", answers=" + answers +
                '}';
    }
}
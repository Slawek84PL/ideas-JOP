package pl.slawek.ideas.model;

public class Answer {
    private String name;

    Answer() {
    }

    public Answer(final String answer) {
        this.name = answer;
    }

    public String getName() {
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

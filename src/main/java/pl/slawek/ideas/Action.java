package pl.slawek.ideas;

import java.util.Objects;

public enum Action {
    LIST("list"), ADD("add");

    private final String value;

    Action(String value) {
        this.value = value;
    }
    
    public static Action of(String value) {
        for (Action action : values()) {
            if (Objects.equals(action.value, value)) {
                return action;
            }
        }

        throw new IllegalArgumentException("Nieznana akcja " + value);
    }
}

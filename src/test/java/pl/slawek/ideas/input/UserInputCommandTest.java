package pl.slawek.ideas.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.slawek.ideas.Action;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserInputCommandTest {

    @Test
    void shouldBuildCorrectUserInputCommand() {
        // given
        String input = "category add CategoryName";

        // when
        UserInputCommand userInputCommand = new UserInputCommand(input);

        //then
        assertEquals("category", userInputCommand.getCommand());
        assertEquals(Action.ADD, userInputCommand.getAction());
        assertLinesMatch(List.of("CategoryName"), userInputCommand.getParam());
    }

    @Test
    void shouldBuildCorrectUserInputCommandWithMultipleParams() {
        // given
        String input = "command list param1 param2 param3";

        // when
        UserInputCommand userInputCommand = new UserInputCommand(input);

        //then
        assertEquals("command", userInputCommand.getCommand());
        assertEquals(Action.LIST, userInputCommand.getAction());
        assertLinesMatch(List.of("param1", "param2", "param3"), userInputCommand.getParam());
    }

    @Test
    void shouldBuildCorrectUserInputCommandWithoutParams() {
        // given
        String input = "command list";

        // when
        UserInputCommand userInputCommand = new UserInputCommand(input);

        //then
        assertEquals("command", userInputCommand.getCommand());
        assertEquals(Action.LIST, userInputCommand.getAction());
        assertEquals(0, userInputCommand.getParam().size());
    }

}
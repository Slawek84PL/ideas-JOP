package pl.slawek.ideas.dao;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeBase;
import pl.slawek.ideas.model.Question;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionDao {

    private static final Path PATH = Paths.get("./questions.txt");
    private List<Question> lines;
    private final ObjectMapper objectMapper;

    public QuestionDao() {
        objectMapper = new ObjectMapper();
    }

    public List<Question> findAll() {
        readLines();
        return lines;
    }

    public void add(final Question question) {
        readLines();
        lines.add(question);
//        System.out.println("wydruk listy któa zostaje wpisana do Mappera" + lines);

        try {
            Files.writeString(PATH, objectMapper.writeValueAsString(lines));
//            objectMapper.writeValueAsString(Files.writeString(PATH, lines.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readLines(){
        try {
            lines = objectMapper.readValue(Files.readString(PATH), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            lines = new ArrayList<>();
        }
    }
}

package pl.slawek.ideas.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.slawek.ideas.model.Question;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuestionDao {

    private static Logger LOG = Logger.getLogger(CategoryDao.class.getName());

    private static final Path PATH = Paths.get("./questions.json");
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

        try {
            Files.writeString(PATH, objectMapper.writeValueAsString(lines));
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Błąd przy zapisie pliku " + PATH, e);
        }
    }

    private void readLines(){
        try {
            lines = objectMapper.readValue(Files.readString(PATH), new TypeReference<>() {
            });
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Błąd przy odczycie pliku " + PATH, e);
            lines = new ArrayList<>();
        }
    }
}

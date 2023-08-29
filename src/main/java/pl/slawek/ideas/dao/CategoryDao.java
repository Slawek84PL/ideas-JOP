package pl.slawek.ideas.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.slawek.ideas.model.Category;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDao {

    private static final Path PATH = Paths.get("./categories.json");
    private List<Category> lines;
    private ObjectMapper objectMapper;

    public CategoryDao() {
        objectMapper = new ObjectMapper();
    }
    public List<Category> findAll() {
        readLines();
        return lines;
    }

    public void add(final Category category) {
        readLines();
        lines.add(category);

        try {
            Files.writeString(PATH, objectMapper.writeValueAsString(lines));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readLines() {
        try {
            lines = objectMapper.readValue(Files.readString(PATH), new TypeReference<>() {
            });
        } catch (IOException e) {
            lines = new ArrayList<>();
        }
    }

    public Optional<Category> findOne(final String categoryName) {
        readLines();
        return findAll().stream().filter(c -> c.getName().equals(categoryName)).findAny();
    }
}

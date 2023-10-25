package ru.otus.dataprocessor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ru.otus.model.Measurement;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    private static final Type MEASUREMENT_TYPE = new TypeToken<List<Measurement>>() {
    }.getType();

    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        // читает файл, парсит и возвращает результат
        try (JsonReader reader =
                     new JsonReader(
                             new FileReader(
                                     getClass()
                                             .getClassLoader()
                                             .getResource(fileName)
                                             .toURI()
                                             .getPath()))) {
            return new Gson().fromJson(reader, MEASUREMENT_TYPE);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.capitole.apitests.frameworkauto.utils;

import com.capitole.apitests.model.PetInfo;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static void writeToFile(final String text, final String filename) {
        String path = String.format("data/%s.txt", filename);

        try {
            File file = new File(path);

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            logger.error("Error while saving text to file. " + e.getMessage());
        }
    }

    public static void saveResponseToFile(final Response response, final String filename) {
        writeToFile(response.asString(), filename);
    }

    public static void saveListToFile(final List<PetInfo> info, final String fileName) {
        final String text = info.stream().map(p -> p.getId() + ", " + p.getName())
                .collect(Collectors.joining(System.lineSeparator()));
        writeToFile(text, fileName);
    }

    public static void saveMapToFile(final Map<String, Integer> pets, final String fileName) {
        final String text = pets.entrySet().stream()
                .map(entry -> "\"" + entry.getKey() + "\": " + entry.getValue())
                .reduce("", (accumulated, current) -> accumulated + current + ",");
        writeToFile(String.format("{%s}", text.substring(0, text.length() - 1)), fileName);
    }
}

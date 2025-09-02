package com.mb.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mb.Request;

public class JsonParser {

    private static final String BASE_DIR = System.getProperty("user.dir") + "/";
    private static final String TESTFILES_DIR = BASE_DIR + "testfiles";
    private static final String OUTPUT_DIR = BASE_DIR + "data";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setDateFormat(DATE_FORMAT);
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Reads a JSON file into a {@link Request} object.
     */
    public static Request parseInRequest(String jsonFileName) {
        if (jsonFileName == null || jsonFileName.isEmpty()) {
            return new Request();
        }
        try {
            return MAPPER.readValue(new File(jsonFileName), Request.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Request();
        }
    }

    /**
     * Lets the user choose a file from the testfiles directory.
     */
    public static String chooseTestFile() {
        List<String> files = listFilesInDirectory(TESTFILES_DIR);
        if (files.isEmpty()) {
            throw new IllegalStateException("No JSON files found in " + TESTFILES_DIR);
        }

        printAvailableFiles(files);

        int index = promptUserForFileIndex(files.size());
        String chosenFile = files.get(index);
        String pathToUse = TESTFILES_DIR + "/" + chosenFile;

        System.out.println("Selected test file: " + pathToUse);
        return pathToUse;
    }

    /**
     * Writes an object as JSON to the output directory.
     */
    public static void parseOutFile(String fileName, Object outputObject) {
        if (outputObject == null) {
            return;
        }

        ensureDirectoryExists(OUTPUT_DIR);
        File outputFile = new File(OUTPUT_DIR, fileName + ".json");

        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {

            writer.write(MAPPER.writeValueAsString(outputObject));
            System.out.println("JSON file created: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ----------------------
    // Helper Methods
    // ----------------------

    private static List<String> listFilesInDirectory(String directoryPath) {
        File folder = new File(directoryPath);
        File[] files = Optional.ofNullable(folder.listFiles()).orElse(new File[0]);

        List<String> fileNames = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                fileNames.add(file.getName());
            }
        }
        return fileNames;
    }

    private static void printAvailableFiles(List<String> files) {
        System.out.println("Available JSON files in: " + TESTFILES_DIR);
        IntStream.range(0, files.size())
                .forEach(i -> System.out.println("    [" + i + "] " + files.get(i)));
    }

    private static int promptUserForFileIndex(int maxSize) {
        try (Scanner scanner = new Scanner(System.in)) {
            int index;
            do {
                System.out.print("Enter the number of the file to use: ");
                index = scanner.nextInt();
            } while (index < 0 || index >= maxSize);
            return index;
        }
    }

    private static void ensureDirectoryExists(String path) {
        File dir = new File(path);
        if (!dir.exists() && !dir.mkdir()) {
            throw new RuntimeException("Could not create directory: " + path);
        }
    }
}

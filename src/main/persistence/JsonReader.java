package persistence;

import model.Aquarium;
import model.Decoration;
import model.Octopus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Partial code obtained from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads aquarium from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads aquarium from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Aquarium read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAquarium(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses aquarium from JSON object and returns it
    private Aquarium parseAquarium(JSONObject jsonObject) {
        Aquarium aq  = new Aquarium();
        addOctopuses(aq, jsonObject);
        addDecorations(aq, jsonObject);
        return aq;
    }

    // MODIFIES: aq
    // EFFECTS: parses octopuses from JSON object and adds them to aquarium
    private void addOctopuses(Aquarium aq, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("octopuses");
        for (Object json: jsonArray) {
            JSONObject nextOctopus = (JSONObject) json;
            addOctopus(aq, nextOctopus);
        }
    }

    // MODIFIES: aq
    // EFFECTS: parses decorations from JSON object and adds them to aquarium
    private void addDecorations(Aquarium aq, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("decorations");
        for (Object json: jsonArray) {
            JSONObject nextDecoration = (JSONObject) json;
            addDecoration(aq, nextDecoration);
        }
    }

    // MODIFIES: aq
    // EFFECTS: parses octopus from JSON object and adds it to aquarium
    private void addOctopus(Aquarium aq, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int growth = jsonObject.getInt("growth");
        int size = jsonObject.getInt("size");
        Octopus octopus = new Octopus(name, growth, size);
        aq.addOctopus(octopus);
    }

    // MODIFIES: aq
    // EFFECTS: parses decoration from JSON object and adds it to aquarium
    private void addDecoration(Aquarium aq, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        String tooltip = jsonObject.getString("tooltip");
        Decoration decoration = new Decoration(name, description, tooltip);
        aq.addDecoration(decoration);
    }
}

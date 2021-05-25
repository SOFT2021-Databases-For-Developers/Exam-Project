package app.postgresql.helpers;

import app.postgresql.models.Car;
import app.postgresql.models.Make;
import app.postgresql.models.Model;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonReader {
    private static String file = System.getProperty("user.dir") + "/src/main/resources/car_model_list.json";

    public static List<Make> getMakesFromJson() {
        List<Make> makes = new ArrayList<>();
        Gson gson = new Gson();
        Reader reader;
        try {

            // create a reader
            reader = Files.newBufferedReader(Paths.get(file));

            // convert JSON array to list of users
            List<JsonObject> objects = gson.fromJson(reader, new TypeToken<List<JsonObject>>() {}.getType());

            List<String> object_strings = new ArrayList<>();
            for(JsonObject m : objects) {
                object_strings.add(m.get("make").toString().replace("\"", ""));
            }

            List<String> filted_string_list = object_strings.stream()
                    .distinct()
                    .collect(Collectors.toList());

            for (String s : filted_string_list) {
                makes.add(new Make(s));
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makes;
    }

    public static List<Model> getModelsAndMakesFromJson() {
        List<Model> modelMakes = new ArrayList<>();
        Gson gson = new Gson();
        Reader reader;
        try {

            // create a reader
            reader = Files.newBufferedReader(Paths.get(file));

            // convert JSON array to list of users
            List<JsonObject> objects = gson.fromJson(reader, new TypeToken<List<JsonObject>>() {}.getType());

            for (JsonObject o : objects) {
                String model = o.get("model").toString().replace("\"", "");
                String make = o.get("make").toString().replace("\"", "");
                modelMakes.add(new Model(model, make));
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelMakes;
    }


    public static List<Car> getCarsFromJson() {
        List<Car> carmodels = null;
        Gson gson = new Gson();
        Reader reader;
        try {

            // create a reader
            reader = Files.newBufferedReader(Paths.get(file));

            // convert JSON array to list of users
            carmodels = gson.fromJson(reader, new TypeToken<List<Car>>() {}.getType());

            // print users
            //users.forEach(System.out::println);

            // close reader
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carmodels;
    }
}

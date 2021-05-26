package app.postgresql.helpers;

import app.postgresql.models.Car;
import app.postgresql.models.Make;
import app.postgresql.models.Model;
import app.postgresql.repositories.MakeRepository;
import app.postgresql.repositories.ModelRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
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

    public static List<Model> getModelsAndMakesFromJson(MakeRepository makeRepo) {
        List<Model> modelMakes = new ArrayList<>();
        Collection<Make> makes = makeRepo.findAll();
        Gson gson = new Gson();
        Reader reader;
        try {

            // create a reader
            reader = Files.newBufferedReader(Paths.get(file));

            // convert JSON array to list of users
            List<JsonObject> objects = gson.fromJson(reader, new TypeToken<List<JsonObject>>() {}.getType());

            for (JsonObject o : objects) {
                String modelStr = o.get("model").toString().replace("\"", "");
                String makeStr = o.get("make").toString().replace("\"", "");
                int modelYear = o.get("year").getAsInt();

                Make m = makes.stream()
                        .filter(make -> makeStr.equals(make.getName()))
                        .findFirst()
                        .orElse(null);
                modelMakes.add(new Model(modelStr, modelYear, m));
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelMakes;
    }


    public static List<Car> getCarsFromJson(MakeRepository makeRepo, ModelRepository modelRepo) {
        List<Car> cars = new ArrayList<>();
        Gson gson = new Gson();

        Collection<Model> models = modelRepo.findAll();
        Collection<Make> makes = makeRepo.findAll();


        Reader reader;
        try {

            // create a reader
            reader = Files.newBufferedReader(Paths.get(file));

            // convert JSON array to list of users
            List<JsonObject> objects = gson.fromJson(reader, new TypeToken<List<JsonObject>>() {}.getType());

            for (JsonObject o : objects) {
                String modelStr = o.get("model").toString().replace("\"", "");
                String makeStr = o.get("make").toString().replace("\"", "");

                Make ma = makes.stream()
                        .filter(make -> makeStr.equals(make.getName()))
                        .findFirst()
                        .orElse(null);
                Model mo = models.stream()
                        .filter(model -> modelStr.equals(model.getName()))
                        .findFirst()
                        .orElse(null);
                cars.add(new Car(ma, mo));
            }

            // print users
            //users.forEach(System.out::println);

            // close reader
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cars;
    }
}

package app.helpers;

import app.models.mongo.User;
import app.models.postgresql.Car;
import app.models.postgresql.Listing;
import app.repositories.MongoService;
import com.github.javafaker.Faker;

import java.util.*;

public class Generator {

    public static List<User> GenerateFakeUsers(int amount) {
        Faker faker = new Faker();
        List<User> fakeUsers = new ArrayList<User>();
        Random rnd;
        for (int i = 0; i < amount; i++) {
            rnd = new Random();
            User user = new User();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setPassword(GenerateRandomAlphanumericString(rnd.nextInt(24 - 10 + 1) + 10));
            user.setEmail(GenerateEmail(user.getFirstName(), user.getLastName(), "mail.com"));
            fakeUsers.add(user);
        }
        return fakeUsers;
    }

    public static String GenerateRandomAlphabeticString(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public static String GenerateRandomAlphanumericString(int targetStringLength) {
        int leftLimit = 48; // numeral '0
        int rightLimit = 122; // letter 'z
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public static String GenerateEmail(String firstname, String lastname, String host) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s-", firstname.toLowerCase()));
        sb.append(String.format("%s", lastname.toLowerCase()));
        sb.append("@");
        sb.append(String.format("%s", host));
        return sb.toString();
    }

    public static String GenerateListingTitle(String make, String model, int year, int km) {
        StringBuilder sb=new StringBuilder();
        sb.append(String.format("%s ", make));
        sb.append(String.format("%s ", model));
        sb.append(String.format("%d, ", year));
        sb.append(String.format("%d km", km));
        return sb.toString();
    }

    public static String GenerateListingDescription(String make, String model, int year, int km) {
        StringBuilder sb=new StringBuilder();
        sb.append(String.format("Selling my %s ", make));
        sb.append(String.format("%s ", model));
        sb.append(String.format("from %d. ", year));
        sb.append(String.format("It has driven only %d km, and I am sure you will get many more our of it.", km));
        return sb.toString();
    }

    public static Collection<Listing> GenerateFakeListings(boolean run, int amount, List<Car> cars) {
        List<Listing> listings = new ArrayList<>();
        if (run) {
            Random random = new Random();
            for (int i = 0; i < amount; i++) {
                int rndIndex = (int) (Math.random() * cars.size());
                Car c = cars.get(rndIndex);

                Listing l = new Listing();
                l.setSeller(Generator.GenerateRandomAlphanumericString(24));
                l.setCar(c);
                l.setPrice(random.nextFloat() * (100 + 100000));
                l.setCreated_on(new Date());
                l.setKm(random.nextInt(99999));
                l.setDescription(Generator.GenerateListingDescription(c.getMake().getName(), c.getModel().getName(), c.getModel().getYear(), l.getKm()));
                l.setTitle(Generator.GenerateListingTitle(c.getMake().getName(), c.getModel().getName(), c.getModel().getYear(), l.getKm()));
                listings.add(l);
            }
        }
        return listings;
    }
}

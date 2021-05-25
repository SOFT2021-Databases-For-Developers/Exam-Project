package app.postgresql.helpers;

import app.postgresql.models.Car;
import app.postgresql.models.Listing;

import java.util.Random;

public class Generator {

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

    public static String GenerateListingTitle(String make, String model, String category, int year, int km) {
        StringBuilder sb=new StringBuilder();
        sb.append(String.format("%s ", make));
        sb.append(String.format("%s ", model));
        sb.append(String.format("%d ", year));
        sb.append(String.format("%s, ", category));
        sb.append(String.format("%d km", km));
        return sb.toString();
    }

    public static String GenerateListingDescription(String make, String model, String category, int year, int km) {
        StringBuilder sb=new StringBuilder();
        sb.append(String.format("Selling my %s ", make));
        sb.append(String.format("%s ", model));
        sb.append(String.format("%s ", category));
        sb.append(String.format("from %d. ", year));
        sb.append(String.format("It has driven only %d km, and I am sure you will get many more our of it.", km));
        return sb.toString();
    }
}

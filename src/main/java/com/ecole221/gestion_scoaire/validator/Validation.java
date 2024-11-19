package com.ecole221.gestion_scoaire.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Validation {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$"
    );

    public static void validateNotEmptyOrSpaces(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " ne doit pas etre vide ou contenir que des spaces");
        }
    }

    public static void validateNotEmptyOrSpacesNumber(Integer value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " ne doit pas etre vide");
        }
    }

    public static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\d{9,15}")) {
            throw new IllegalArgumentException("Le format du téléphone est invalid");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Le format de l'email est invalid");
        }
    }

    public static void validateNotNegative(int number, String fieldName) {
        if (number < 0) {
            throw new IllegalArgumentException(fieldName + " ne doit pas etre negatif");
        }
    }

    public static LocalDate validateAndParseDate(String dateStr, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDate.parse(dateStr, formatter);  // Parse the date string into LocalDate
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date pattern or format: " + pattern, e);
        }
    }

}

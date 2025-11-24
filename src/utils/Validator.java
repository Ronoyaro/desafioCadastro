package utils;

import enums.Sexo;
import enums.Tipo;

import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validateNamePet(String name) {
        if (name.isBlank()) return true;
        return Pattern.matches("^[A-Za-z]+(?:\\s+[A-Za-z]+)+$", name.trim());
    }

    public static boolean validateTypePet(String name) {
        return name.equalsIgnoreCase(Tipo.CACHORRO.getTIPO()) || name.equalsIgnoreCase(Tipo.GATO.getTIPO());
    }

    public static boolean validateSexPet(String name) {
        return name.equalsIgnoreCase(Sexo.MACHO.getSEXO()) || name.equalsIgnoreCase(Sexo.FEMEA.getSEXO());
    }

    public static boolean validateAgePet(String age) {
        return Double.parseDouble(age) <= 20;
    }

    public static boolean validateWeightPet(String weightPet) {
        double weightPetParsed = Double.parseDouble(weightPet.replace(",", "."));
        return weightPetParsed >= 0.5 && weightPetParsed <= 60;
    }

    public static boolean validateRacePet(String race) {
        if(race.isBlank()) return false;
        return Pattern.matches("^[A-Za-z]+(?:\\s+[A-Za-z]+)*$", race.trim());
    }
}

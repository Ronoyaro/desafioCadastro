package utils;

import enums.Sexo;
import enums.Tipo;

import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validateNamePet(String name) {
        if (name.isBlank()) return true;
        name = name.trim();
        return Pattern.matches("^[A-Za-z]+(?:\\s+[A-Za-z]+)+$", name);
    }

    public static boolean validateTypePet(String name) {
        return name.equalsIgnoreCase(Tipo.CACHORRO.getTIPO()) || name.equalsIgnoreCase(Tipo.GATO.getTIPO());
    }

    public static boolean validateSexPet(String name) {
        return name.equalsIgnoreCase(Sexo.MACHO.getSEXO()) || name.equalsIgnoreCase(Sexo.FEMEA.getSEXO());
    }

    public static boolean validateAgePet(String age) {
        return Double.parseDouble(age) > 20;
    }

    public static boolean validateWeightPet(Double weightPet) {
        return weightPet >= 0.5 && weightPet <= 60;
    }

    public static String validateRacePet(String race) {
        if(race.isBlank()) return "NÃO INFORMADO";
        boolean isValidRace = Pattern.matches("^[A-Za-z]+(?:\\s+[A-Za-z]+)*$", race);
        if(!isValidRace) {
            throw new IllegalArgumentException("Caracteres especiais e/ou números não são permitidos");
        }
        return race;
    }
}

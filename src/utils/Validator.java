package utils;

import enums.Sexo;
import enums.Tipo;

import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    public static String validateNamePet(String name) {
        if (name.isBlank()) return "NÃO INFORMADO";
        name = name.trim();

        boolean isNamePetValid = Pattern.matches("^[A-Za-z]+(?:\\s+[A-Za-z]+)+$", name);

        if (!isNamePetValid) throw new IllegalArgumentException("Não permitido caracteres especiais e/ou animal sem sobrenome");

        return name;
    }

    public static Tipo validateTypePet(String name) {
        if (name.equalsIgnoreCase(Tipo.CACHORRO.getTIPO())) return Tipo.CACHORRO;

        if (name.equalsIgnoreCase(Tipo.GATO.getTIPO())) return Tipo.GATO;

        throw new IllegalArgumentException("Digite um tipo válido: (Gato/Cachorro)");
    }

    public static Sexo validateSexPet(String name) {
        if (name.equalsIgnoreCase(Sexo.FEMEA.getSEXO())) return Sexo.FEMEA;

        if (name.equalsIgnoreCase(Sexo.MACHO.getSEXO())) return Sexo.MACHO;

        throw new IllegalArgumentException("Digite um tipo válido: (Macho/Femea)");
    }

    public static Double validateAgePet(String age) {
        if(age.contains(",")) {
            String formattedAge = age.replace(",", ".");
            return Double.parseDouble(formattedAge);
        }
        double agePet = Double.parseDouble(age);
        if (agePet <= 0D || agePet > 20D) throw new IllegalArgumentException("Idade não permitida");
        return agePet;
    }

    public static Double validateWeightPet(String weight) {
        if(weight.contains(",")) {
            String weightFormatted = weight.replace(",", ".");
            return Double.parseDouble(weightFormatted);
        }
        double weightPet = Double.parseDouble(weight);
        if(weightPet < 0.5 || weightPet > 60) throw new IllegalArgumentException("Peso não permitido");
        return weightPet;
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

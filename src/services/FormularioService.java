package services;

import enums.Sexo;
import enums.Tipo;
import utils.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.Scanner;

public class FormularioService {
    final static Scanner SCANNER = new Scanner(System.in);

    public static String answerNameQuestion() {
        System.out.println(readFormulario().getFirst());
        String namePet = SCANNER.nextLine();
        if (!Validator.validateNamePet(namePet)) throw new IllegalArgumentException("Digite um valor válido");
        return namePet;
    }

    public static Tipo answerTypePetQuestion() {
        System.out.println(readFormulario().get(1));
        String typePet = SCANNER.nextLine();
        if (!Validator.validateTypePet(typePet)) {
            throw new IllegalArgumentException("Digite um tipo válido: (Gato/Cachorro)");
        }
        return typePet.equalsIgnoreCase("Cachorro") ? Tipo.CACHORRO : Tipo.GATO;
    }

    public static Sexo answerSexPetQuestion() {
        System.out.println(readFormulario().get(2));
        String sexPet = SCANNER.nextLine();
        if (!Validator.validateSexPet(sexPet)) {
            throw new IllegalArgumentException("Digite um sexo válido: (Macho/Femea");
        }
        return sexPet.equalsIgnoreCase("Macho") ? Sexo.MACHO : Sexo.FEMEA;
    }

    public static List<String> answerAddressPetQuestion() {
        List<String> questionsAdress = new ArrayList<>(List.of("Numero da casa:", "Cidade:", "Rua:"));
        List<String> questionsAnswers = new ArrayList<>();
        System.out.println(readFormulario().get(3));
        questionsAdress.forEach(q -> {
            System.out.println(q);
            questionsAnswers.add(SCANNER.nextLine());
        });
        return questionsAnswers;
    }

    public static Double answerAgePetQuestion() {
        System.out.println(readFormulario().get(4));
        String agePet = SCANNER.nextLine();
        if (!Validator.validateAgePet(agePet)) {
            throw new IllegalArgumentException("Idade acima de 20 não permitida");
        }
        return Double.parseDouble(agePet);
    }

//    public static void answerWeightPetQuestion() {
//        System.out.println(readFormulario().get(5));
//        String petWeight = SCANNER.nextLine();
//        if(petWeight.contains())
//
//        if (!Validator.validateWeightPet()) {
//            throw new IllegalArgumentException("Peso abaixo de 0.5kg ou acima de 20kg não permitido");
//        }
//
//    }

    public static String answerRacePetQuestion() {
        System.out.println(readFormulario().get(6));
        return Validator.validateRacePet(SCANNER.nextLine());
    }

    private static List<String> readFormulario() {
        Path formularioPath = Paths.get("src\\forms\\formulario.txt");
        try (Stream<String> lines = Files.lines(formularioPath)) {
            return lines.filter(l -> !l.isBlank())
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

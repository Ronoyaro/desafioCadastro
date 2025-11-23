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
        return Validator.validateNamePet(SCANNER.nextLine());
    }

    public static Tipo answerTypePetQuestion() {
        System.out.println(readFormulario().get(1));
        return Validator.validateTypePet(SCANNER.nextLine());
    }

    public static Sexo answerSexPetQuestion() {
        System.out.println(readFormulario().get(2));
        return Validator.validateSexPet(SCANNER.nextLine());
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
        return Validator.validateAgePet(SCANNER.nextLine());
    }

    public static Double answerWeightPetQuestion() {
        System.out.println(readFormulario().get(5));
        return Validator.validateWeightPet(SCANNER.nextLine());
    }

    public static String answerRacePetQuestion(){
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

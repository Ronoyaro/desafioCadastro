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
        try {
            System.out.println(readFormulario().getFirst());
            String namePet = SCANNER.nextLine();
            if (!Validator.validateNamePet(namePet)) throw new IllegalArgumentException("Informe um sobrenome: Não permitido carateceres especiais");
            return namePet;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return answerNameQuestion();
        }
    }

    public static Tipo answerTypePetQuestion() {
        try {
            System.out.println(readFormulario().get(1));
            String typePet = SCANNER.nextLine();
            if (!Validator.validateTypePet(typePet))
                throw new IllegalArgumentException("Digite um tipo válido: (Gato/Cachorro)");
            return typePet.equalsIgnoreCase("Cachorro") ? Tipo.CACHORRO : Tipo.GATO;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return answerTypePetQuestion();
        }
    }

    public static Sexo answerSexPetQuestion() {
        try {
            System.out.println(readFormulario().get(2));
            String sexPet = SCANNER.nextLine();
            if (!Validator.validateSexPet(sexPet))
                throw new IllegalArgumentException("Digite um sexo válido: (Macho/Femea");
            return sexPet.equalsIgnoreCase("Macho") ? Sexo.MACHO : Sexo.FEMEA;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return answerSexPetQuestion();
        }
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
        try {
            System.out.println(readFormulario().get(4));
            String agePet = SCANNER.nextLine();
            if (!Validator.validateAgePet(agePet)) {
                throw new IllegalArgumentException("Idade acima de 20 anos não permitida");
            }
            return Double.parseDouble(agePet);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return answerAgePetQuestion();
        }
    }

    public static Double answerWeightPetQuestion() {
        try {
            System.out.println(readFormulario().get(5));
            String weightPet = SCANNER.nextLine();
            if (!Validator.validateWeightPet(weightPet)) {
                throw new IllegalArgumentException("Peso abaixo de 0.5kg ou acima de 60kg não permitido");
            }
            return Double.parseDouble(weightPet.replace(",", "."));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return answerWeightPetQuestion();
        }
    }

    public static String answerRacePetQuestion() {
        try {
            System.out.println(readFormulario().get(6));
            String racePet = SCANNER.nextLine();
            if (racePet.isBlank()) {
                return "NÃO INFORMADO";
            }
            if (!Validator.validateRacePet(racePet)) {
                throw new IllegalArgumentException("Não permitido números caraceteres especiais");
            }
            return racePet;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return answerRacePetQuestion();
        }
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

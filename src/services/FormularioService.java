package services;

import enums.Sexo;
import enums.Tipo;
import utils.FilesUtils;
import utils.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FormularioService {
    static final Scanner SCANNER = new Scanner(System.in);

    public static String nameQuestion() {
        try {
            System.out.println(FilesUtils.readForm().getFirst());
            String namePet = SCANNER.nextLine();
            if (!Validator.validateNamePet(namePet)) {
                throw new IllegalArgumentException("Informe um sobrenome: Não permitido carateceres especiais");
            }
            if (namePet.isBlank()) {
                return "NÃO INFORMADO";
            }
            return namePet;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return nameQuestion();
        }
    }

    public static Tipo typeQuestion() {
        try {
            System.out.println(FilesUtils.readForm().get(1));
            String typePet = SCANNER.nextLine();
            if (!Validator.validateTypePet(typePet))
                throw new IllegalArgumentException("Digite um tipo válido: (Gato/Cachorro)");
            return typePet.equalsIgnoreCase("Cachorro") ? Tipo.CACHORRO : Tipo.GATO;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return typeQuestion();
        }
    }

    public static Sexo sexPetQuestion() {
        try {
            System.out.println(FilesUtils.readForm().get(2));
            String sexPet = SCANNER.nextLine();
            if (!Validator.validateSexPet(sexPet))
                throw new IllegalArgumentException("Digite um sexo válido: (Macho/Femea)");
            return sexPet.equalsIgnoreCase("Macho") ? Sexo.MACHO : Sexo.FEMEA;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return sexPetQuestion();
        }
    }

    public static List<String> AddressPetQuestion() {
        List<String> questionsAdress = new ArrayList<>(List.of("Numero da casa:", "Cidade:", "Rua:"));
        List<String> questionsAnswers = new ArrayList<>();
        System.out.println(FilesUtils.readForm().get(3));
        questionsAdress.forEach(q -> {
            System.out.println(q);
            questionsAnswers.add(SCANNER.nextLine());
        });
        return questionsAnswers;
    }

    public static Double agePetQuestion() {
        try {
            System.out.println(FilesUtils.readForm().get(4));
            String agePet = SCANNER.nextLine();
            if (!Validator.validateAgePet(agePet)) {
                throw new IllegalArgumentException("Idade acima de 20 anos não permitida");
            }
            return Double.parseDouble(agePet.replace(",", "."));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return agePetQuestion();
        }
    }

    public static Double weightPetQuestion() {
        try {
            System.out.println(FilesUtils.readForm().get(5));
            String weightPet = SCANNER.nextLine();
            if (!Validator.validateWeightPet(weightPet)) {
                throw new IllegalArgumentException("Peso abaixo de 0.5kg ou acima de 60kg não permitido");
            }
            return Double.parseDouble(weightPet.replace(",", "."));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("---Digite novamente---");
            return weightPetQuestion();
        }
    }

    public static String racePetQuestion() {
        try {
            System.out.println(FilesUtils.readForm().get(6));
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
            return racePetQuestion();
        }
    }


}

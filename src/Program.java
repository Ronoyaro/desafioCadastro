import entities.Pet;
import enums.Sexo;
import enums.Tipo;
import services.FormularioService;
import utils.Validator;

import java.util.List;
import java.util.Scanner;

public class Program {
    static boolean finalizar = false;
    static Integer opcao;
    static String resposta;
    static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        System.out.println("--- Seja Bem-vindo ao Sistema de Adoção de Pets ---");
        System.out.println("Escolha uma das opções a seguir:");
        System.out.println("1 - Cadastrar um novo pet");
        System.out.println("2 - Buscar dados do pet cadastrado");
        System.out.println("3 - Deletar um pet cadastrado");
        System.out.println("4 - Listar todos os pets cadastrados");
        System.out.println("5 - Sair");
        opcao = Integer.parseInt(SCANNER.nextLine());

        while (!finalizar) {
            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> buscar();
            }
        }
    }

    private static void cadastrar() {
        String namePet = FormularioService.answerNameQuestion();
        Tipo typePet = FormularioService.answerTypePetQuestion();
        Sexo sexPet = FormularioService.answerSexPetQuestion();
        List<String> adressPet = FormularioService.answerAddressPetQuestion();
        Double agePet = FormularioService.answerAgePetQuestion();
        Double weightPet = FormularioService.answerWeightPetQuestion();
        String racePet = FormularioService.answerRacePetQuestion();

        System.out.println(namePet);
        System.out.println(typePet);
        System.out.println(sexPet);
        System.out.println(adressPet);
        System.out.println(agePet);
        System.out.println(weightPet);
        System.out.println(racePet);

        finalizar = true;
    }

    private static void buscar() {
        System.out.println("a");
        finalizar = true;
    }

}

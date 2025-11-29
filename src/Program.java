import entities.Pet;
import enums.Sexo;
import enums.Tipo;
import repository.PetRepository;
import services.FormularioService;
import services.PetService;
import utils.Validator;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Program {
    static boolean finalizar = false;
    static Integer opcao;
    static String resposta;
    static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        menu();
    }

    private static void menu() {
        while (true) {
            System.out.println("--- Seja Bem-vindo ao Sistema de Adoção de Pets ---");
            System.out.println("Escolha uma das opções a seguir:");
            System.out.println("1 - Cadastrar um novo pet");
            System.out.println("2 - Listar todos os pets cadastrado");
            System.out.println("3 - Buscar um pet");
            System.out.println("4 - Editar um pet");
            System.out.println("5 - Deletar um pet cadastrado");
            System.out.println("5 - Sair");
            opcao = Integer.parseInt(SCANNER.nextLine());
            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listarTodos();
                case 3 -> buscar();
                case 4 -> editar();
                case 5 -> remover();
                case 6 -> {
                    System.out.println("Saindo...");
                    return;
                }
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

        Pet pet = new Pet(namePet, typePet, sexPet, adressPet, agePet, weightPet, racePet);
        try {
            PetService.save(pet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarTodos() {
        try {
            PetService.findAll().forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscar() {
        try {
            System.out.println("Digite o ID do pet");
            int id = Integer.parseInt(SCANNER.nextLine());
            System.out.println(PetService.find(id));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void editar() {
        try {
            System.out.println("Digite o ID do pet");
            int id = Integer.parseInt(SCANNER.nextLine());
            System.out.println(PetService.find(id));
            Pet pet = PetService.find(id);
            System.out.println(pet);
            String name = FormularioService.answerNameQuestion();
            List<String> adress = FormularioService.answerAddressPetQuestion();
            Double age = FormularioService.answerAgePetQuestion();
            Double weight = FormularioService.answerWeightPetQuestion();
            String race = FormularioService.answerRacePetQuestion();
            PetService.remove(0);
            Pet newPet = new Pet(name, pet.getTipo(), pet.getSexo(), adress, age, weight, race);
            PetService.save(newPet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void remover(){
        try {
            PetService.remove(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}

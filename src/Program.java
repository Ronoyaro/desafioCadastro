import entities.Pet;
import enums.Sexo;
import enums.Tipo;
import services.FormularioService;
import services.PetService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Program {
    static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        menu();
    }

    private static void menu() {
        System.out.println("--- Seja Bem-vindo ao Sistema de Adoção de Pets ---");
        while (true) {
            System.out.println("Escolha uma das opções a seguir:");
            System.out.println("1 - Cadastrar um novo pet");
            System.out.println("2 - Listar todos os pets cadastrado");
            System.out.println("3 - Buscar um pet");
            System.out.println("4 - Editar um pet");
            System.out.println("5 - Deletar um pet cadastrado");
            System.out.println("5 - Sair");
            int opcao = SCANNER.nextInt();
            SCANNER.nextLine();
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
        String namePet = FormularioService.nameQuestion();
        Tipo typePet = FormularioService.typeQuestion();
        Sexo sexPet = FormularioService.sexPetQuestion();
        List<String> adressPet = FormularioService.AddressPetQuestion();
        Double agePet = FormularioService.agePetQuestion();
        Double weightPet = FormularioService.weightPetQuestion();
        String racePet = FormularioService.racePetQuestion();

        Pet pet = new Pet(namePet, typePet, sexPet, adressPet, agePet, weightPet, racePet);
        try {
            PetService.save(pet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarTodos() {
        try {
            AtomicInteger count = new AtomicInteger(1);
            System.out.printf("%-10s %-22s %-15s %-15s %-15s%n", "Id", "Nome", "Sexo", "Idade", "Peso");
            PetService.findAll().forEach(p -> {
                System.out.printf("%-5d %-27s %-15s %-15s %-15s%n", count.getAndIncrement(),
                        p.getNome(),
                        p.getSexo().getSexo(),
                        (p.getIdade() < 1 ? p.getIdade() + " meses" : p.getIdade().shortValue() + " anos"),
                        (p.getPeso() < 1 ? p.getPeso() + " kgs" : p.getPeso().shortValue() + " kg"));
            });
            System.out.println("Pressione Enter para continuar");
            SCANNER.nextLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscar() {
        try {
            System.out.println("Digite o ID do pet");
            Integer id = Integer.parseInt(SCANNER.nextLine());
            Pet pet = PetService.findById(id - 1);
            System.out.printf("%-22s %-10s %-8s %-10s %-7s%n", "Nome", "Sexo", "Idade", "Peso", "Raça");

            System.out.printf("%-22s %-10s %-8s %-8s %-5s%n",
                    pet.getNome(),
                    pet.getSexo().getSexo(),
                    (pet.getIdade() < 1 ? pet.getIdade() + " meses" : pet.getIdade().shortValue() + " anos"),
                    (pet.getPeso() < 1 ? pet.getPeso() + " kgs" : pet.getPeso().shortValue() + " kg"),
                    pet.getRaca());

            System.out.println("Pressione Enter para continuar");
            SCANNER.nextLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void editar() {
        try {
            System.out.println("Digite o ID do pet");
            Integer id = Integer.parseInt(SCANNER.nextLine());
            Pet pet = PetService.findById(id - 1);
            System.out.printf("%-22s %-10s %-8s %-10s %-7s%n", "Nome", "Sexo", "Idade", "Peso", "Raça");

            System.out.printf("%-22s %-10s %-8s %-8s %-5s%n",
                    pet.getNome(),
                    pet.getSexo().getSexo(),
                    pet.getIdade().shortValue() + " anos",
                    pet.getPeso().shortValue() + " kg",
                    pet.getRaca());

            String name = FormularioService.nameQuestion();
            List<String> adress = FormularioService.AddressPetQuestion();
            Double age = FormularioService.agePetQuestion();
            Double weight = FormularioService.weightPetQuestion();
            String race = FormularioService.racePetQuestion();
            PetService.removeById(id - 1);
            Pet newPet = new Pet(name, pet.getTipo(), pet.getSexo(), adress, age, weight, race);
            PetService.save(newPet);
            System.out.println("Pressione Enter para continuar");
            SCANNER.nextLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void remover() {
        try {
            System.out.println("Digite o ID do pet");
            int id = Integer.parseInt(SCANNER.nextLine());
            Pet pet = PetService.findById(id - 1);
            System.out.printf("%-22s %-10s %-8s %-10s %-7s%n", "Nome", "Sexo", "Idade", "Peso", "Raça");

            System.out.printf("%-22s %-10s %-8s %-8s %-5s%n",
                    pet.getNome(),
                    pet.getSexo().getSexo(),
                    pet.getIdade().shortValue() + " anos",
                    pet.getPeso().shortValue() + " kg",
                    pet.getRaca());
            System.out.println("Deseja remover? (S/N)");
            String confirma = SCANNER.nextLine();
            if (confirma.equalsIgnoreCase("s")) {
                PetService.removeById(id - 1);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}

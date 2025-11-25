package repository;

import entities.Pet;
import enums.Sexo;
import enums.Tipo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class PetRepository {


    public static void main(String[] args) throws IOException {
//        save(new Pet("nick fury", Tipo.CACHORRO, Sexo.MACHO, List.of("9", "ssa", "brazil"), 5D, 6D, "vira lata"));
//        getAllPathsPet();
        list().forEach(System.out::println);

    }

    private static Path createFile(Pet pet) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        String petNameArchive = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm")) + pet.getNome().replace(" ", "") + ".txt";
        Path petPath = Paths.get("src\\db_pets\\" + petNameArchive.toUpperCase());
        if (!Files.exists(petPath)) {
            return Files.createFile(petPath);
        }
        throw new IOException("O Pet já existe!");
    }

    public static void save(Pet pet) throws IOException {
        Path filePet = createFile(pet);
        try (BufferedWriter br = Files.newBufferedWriter(filePet)) {
            br.write("1 - " + pet.getNome() + "\n");
            br.write("2 - " + pet.getTipo().getTIPO() + "\n");
            br.write("3 - " + pet.getSexo().getSEXO() + "\n");
            br.write("4 - " + pet.getEndereco().toString() + "\n");
            br.write("5 - " + pet.getIdade().toString() + "\n");
            br.write("6 - " + pet.getPeso().toString() + "\n");
            br.write("7 - " + pet.getRaca() + "\n");
            br.flush();
            System.out.println("Pet criado com sucesso!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Path> getAllPathsPet() {
        List<Path> archivesPet = new ArrayList<>();
        Path databasePets = Paths.get("src\\db_pets");
        try (DirectoryStream<Path> files = Files.newDirectoryStream(databasePets, "*.txt")) {
            Iterator<Path> paths = files.iterator();
            paths.forEachRemaining(archivesPet::add);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return archivesPet;
    }

    public static List<Pet> list() {
        List<Pet> pets = new ArrayList<>();
        getAllPathsPet().forEach(p -> {
            try (Stream<String> path = Files.lines(p)) {
                List<String> list = path.map(s -> s.replaceAll("^\\d+\\s*-\\s*", "")).toList();
                List<String> adress = Arrays.stream(list.get(3).split(",")).toList();
                Pet pet = new Pet(list.getFirst(),
                        (list.get(1).equalsIgnoreCase("Cachorro") ? Tipo.CACHORRO : Tipo.GATO),
                        (list.get(2).equalsIgnoreCase("Macho") ? Sexo.MACHO : Sexo.FEMEA),
                        adress, Double.parseDouble(list.get(4)), Double.parseDouble(list.get(5)), list.get(6));
                pets.add(pet);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return pets;
    }
}

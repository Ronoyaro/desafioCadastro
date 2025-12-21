package utils;

import entities.Pet;
import enums.Sexo;
import enums.Tipo;

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

public class FilesUtils {
    public static List<String> readForm() {
        Path formularioPath = Paths.get("src\\forms\\formulario.txt");
        try (Stream<String> lines = Files.lines(formularioPath)) {
            return lines.filter(l -> !l.isBlank())
                    .toList();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Path createFile(Pet pet) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        String petNameArchive = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm")) + pet.getNome().replace(" ", "") + ".txt";
        Path petPath = Paths.get("src\\db_pets\\" + petNameArchive.toUpperCase());
        if (!Files.exists(petPath)) {
            return Files.createFile(petPath);
        }
        throw new IllegalArgumentException("O Pet já existe!");
    }

    public static List<Path> getPaths() {
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


    public static List<Pet> fileToPet() {
        List<Pet> listPets = new ArrayList<>();
        getPaths().forEach(path -> {
            try (Stream<String> petData = Files.lines(path)) {

                List<String> list = petData
                        .map(s -> s.replaceAll("^\\d+\\s-\\s", ""))
                        .map(s -> s.replaceAll("[\\[\\]]", ""))
                        .toList();

                List<String> adress = Arrays.stream(list.get(3).split(",")).toList();

                Pet pet = new Pet(list.getFirst(),
                        (list.get(1).equalsIgnoreCase("Cachorro") ? Tipo.CACHORRO : Tipo.GATO),
                        (list.get(2).equalsIgnoreCase("Macho") ? Sexo.MACHO : Sexo.FEMEA),
                        adress, Double.parseDouble(list.get(4)), Double.parseDouble(list.get(5)), list.get(6));

                listPets.add(pet);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        return listPets;
    }

}

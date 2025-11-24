package repository;

import entities.Pet;
import enums.Sexo;
import enums.Tipo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PetRepository {

    public static void main(String[] args) throws IOException {
        savePet(new Pet("joao da", Tipo.CACHORRO, Sexo.FEMEA, List.of("9", "ssa", "br"), 5D, 6D, "baiano"));


    }

    private static Path createFilePet(Pet pet) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        String petNameArchive = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm")) + pet.getNome().replace(" ", "") + ".txt";
        Path petPath = Paths.get(petNameArchive.toUpperCase());
        if (!Files.exists(petPath)) {
            return Files.createFile(petPath);
        }
        throw new IOException("Arquivo já foi existe!");
    }

    public static void savePet(Pet pet) throws IOException {
        Path filePet = createFilePet(pet);
        try (BufferedWriter br = Files.newBufferedWriter(filePet)) {
            br.write("1 - "+pet.getNome());
            br.newLine();
            br.write("2 - "+pet.getTipo().getTIPO());
            br.newLine();
            br.write("3 - "+pet.getSexo().getSEXO());
            br.newLine();
            br.write("4 - " +pet.getEndereco().toString());
            br.newLine();
            br.write("5 - " +pet.getIdade().toString());
            br.newLine();
            br.write("6 - " +pet.getPeso().toString());
            br.newLine();
            br.write("7 - "+pet.getRaca());
            br.flush();
            System.out.println("Pet criado com sucesso!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}

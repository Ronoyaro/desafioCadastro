package repository;

import entities.Pet;
import enums.Sexo;
import enums.Tipo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PetRepository {
    private static List<String> archivesPet = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        save(new Pet("nick fury", Tipo.CACHORRO, Sexo.MACHO, List.of("9", "ssa", "brazil"), 5D, 6D, "vira lata"));


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
            String name = pet.getNome();
            Tipo type = pet.getTipo();
            Sexo sex = pet.getSexo();
            List<String> endereco = pet.getEndereco();
            Double age = pet.getIdade();
            Double weight = pet.getPeso();
            String race = pet.getRaca();
            br.write("1 - " + name);
            br.newLine();
            br.write("2 - " + type.getTIPO());
            br.newLine();
            br.write("3 - " + sex.getSEXO());
            br.newLine();
            br.write("4 - " + endereco.toString());
            br.newLine();
            br.write("5 - " + age.toString());
            br.newLine();
            br.write("6 - " + weight.toString());
            br.newLine();
            br.write("7 - " + race);
            br.flush();
            archivesPet.add(filePet.toString());
            System.out.println("Pet criado com sucesso!");
            System.out.println(archivesPet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



}

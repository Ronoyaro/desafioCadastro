package repository;

import entities.Pet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static utils.FilesUtils.*;

public class PetRepository {

    public static void savePet(Pet pet) throws IOException {
        Path filePet = createFile(pet);
        try (BufferedWriter br = Files.newBufferedWriter(filePet)) {
            br.write("1 - " + pet.getNome() + "\n");
            br.write("2 - " + pet.getTipo().getTIPO() + "\n");
            br.write("3 - " + pet.getSexo().getSexo() + "\n");
            br.write("4 - " + pet.getEndereco().toString() + "\n");
            br.write("5 - " + pet.getIdade().toString() + "\n");
            br.write("6 - " + pet.getPeso().toString() + "\n");
            br.write("7 - " + pet.getRaca() + "\n");
            br.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removePet(int index) throws IOException {
        Path path = getPaths().get(index);
        Files.delete(path);
    }

    public static Pet findById(int index) {
        return findAll().get(index);
    }

    public static List<Pet> findAll() {
        return fileToPet();
    }

}

package services;

import entities.Pet;
import repository.PetRepository;

import java.io.IOException;
import java.util.List;

public class PetService {
    public static void save(Pet pet) throws IOException {
        PetRepository.savePet(pet);
    }

    public static void removeById(int index) throws IOException {
        PetRepository.removePet(index);
    }

    public static Pet findById(int index) throws IOException {
        return PetRepository.findById(index);
    }

    public static List<Pet> findAll() throws IOException {
        return PetRepository.findAll();
    }
}

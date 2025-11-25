package services;

import entities.Pet;
import repository.PetRepository;

import java.io.IOException;

public class PetService {
    public static void save(Pet pet) throws IOException {
        PetRepository.save(pet);
    }

}

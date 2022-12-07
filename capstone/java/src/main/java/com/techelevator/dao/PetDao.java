package com.techelevator.dao;

import com.techelevator.model.Pet;

import java.util.List;

public interface PetDao {


    Pet getPet(int id);

    List<Pet> getAllPets();

    void submitPet(Pet pet);

    void updateAvailability(int id);
}

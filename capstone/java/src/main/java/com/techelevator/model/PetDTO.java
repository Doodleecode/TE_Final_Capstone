package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class PetDTO {
    @NotNull
    private Pet pet;

    public PetDTO(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet() {return pet;}
    public void setPet(Pet pet) {this.pet = pet;}
}

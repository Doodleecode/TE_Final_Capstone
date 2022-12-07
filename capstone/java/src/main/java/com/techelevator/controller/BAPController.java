package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/api")
public class BAPController {

        private PetDao petDao;

        public BAPController(PetDao petDao) {
           this.petDao=petDao;
        }

        @GetMapping("/pets")
        public List<Pet> getAllPets() {
            return petDao.getAllPets();
        }

    @GetMapping("/pets/{id}")
    public Pet findTransaction(@PathVariable int id) {
        try {
            return petDao.getPet(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet was not found");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/pets")
    public void addNewPet(@Valid @RequestBody Pet pet) {
        petDao.submitPet(pet);
    }

    @PutMapping("/pets/{id}")
    public void changePetAvailability(@PathVariable int id){
        petDao.updateAvailability(id);
    }

    @DeleteMapping("/pets/{id}")
    public void deletePet(@PathVariable int id){
            petDao.deletePet(id);
    }



    }

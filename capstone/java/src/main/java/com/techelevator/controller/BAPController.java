package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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


    }

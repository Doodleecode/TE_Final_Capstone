package com.techelevator.dao;


import com.techelevator.model.Pet;
import com.techelevator.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPetDao implements PetDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPetDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Pet getPet(int id) {
        if (id == 0) throw new IllegalArgumentException("Id cannot be null");


        String sql = "SELECT * FROM pets WHERE pet_id = ?";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);

        return mapRowToPet(rs);
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> allPets = new ArrayList<>();
       String sql = "SELECT * FROM pets";

       SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);

        while (rs.next()) {
            Pet pet = mapRowToPet(rs);
            allPets.add(pet);
        }
        return allPets;
    }


    private Pet mapRowToPet(SqlRowSet rs) {
        Pet pet = new Pet();
        pet.setId(rs.getInt("pet_id"));
        pet.setName(rs.getString("pet_name"));
        pet.setType(rs.getString("pet_type"));
        pet.setAge(rs.getInt("pet_age"));
        pet.setAvailable(rs.getBoolean("is_available"));
        return pet;
    }
}

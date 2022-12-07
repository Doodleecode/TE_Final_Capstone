package com.techelevator.dao;


import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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

    @Override
    public void submitPet(Pet pet) {
        String sql = "INSERT INTO pets (pet_name, pet_type, pet_age, is_available) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql,pet.getName(),pet.getType(),pet.getAge(),pet.isAvailable());
    }

    @Override
    public void updateAvailability(int id) {
        Pet pet = getPet(id);
        String sql;
        if (pet.isAvailable()) {
            sql = "UPDATE pets SET is_available = false WHERE pet_id = ?;";
        } else {
            sql = "UPDATE pets SET is_available = true WHERE pet_id = ?;";
        }
        jdbcTemplate.update(sql, pet.getId());
    }

    @Override
    public void deletePet(int id) {
        String sql = "DELETE FROM pets WHERE pet_id = ?;";
        jdbcTemplate.update(sql,id);
    }

    private Pet mapRowToPet(SqlRowSet rs) {
        Pet pet = new Pet();
        pet.setId(rs.getInt("pet_id"));
        pet.setName(rs.getString("pet_name"));
        pet.setType(rs.getString("pet_type"));
        pet.setAge(rs.getInt("pet_age"));
        pet.setAvailable(rs.getBoolean("is_available"));
        pet.setImageLink(rs.getString("image_link"));
        return pet;
    }
}

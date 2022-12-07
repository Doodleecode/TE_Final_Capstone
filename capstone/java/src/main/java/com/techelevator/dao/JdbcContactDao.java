package com.techelevator.dao;

import com.techelevator.model.Contact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcContactDao implements ContactDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcContactDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Get all existing contacts from contacts table
     *
     * @return List<Contact> list of all contacts
     */
    @Override
    public List<Contact> getAllContacts() {
        String sql = "SELECT * FROM contact;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        return createListOfContacts(rowSet);
    }

    /**
     * Get contacts by role,
     *
     * @param role: Admin or Volunteer
     * @return List<Contact> list of contacts by role.
     */
    @Override
    public List<Contact> getContactsByRole(String role) {
        if (role == null) throw new IllegalArgumentException("Role cannot be null");
        String sql = "SELECT * FROM contact c " +
                " JOIN user u ON c.contact_id = u.contact_id" +
                " WHERE role = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, role);
        return createListOfContacts(rowSet);
    }



    /**
     * Inserts contact info to contact table in database.
     *
     * @param contact
     */
    @Override
    public Long create(Contact contact) {
        String sql = "INSERT INTO contact (contact_name, phone, email, city, state, age, social_link) " +
                " VALUES (?, ?, ?, ?, ?, ?) RETURNING contact_id;";
        return jdbcTemplate.queryForObject(sql, Long.class, contact.getContactName(), contact.getPhone(),
                contact.getEmail(), contact.getCity(), contact.getState(), contact.getAge(), contact.getSocialLink());
    }

    /**
     * @param rowSet
     * @return List of contact objects
     */
    private List<Contact> createListOfContacts(SqlRowSet rowSet) {
        List<Contact> allContacts = new ArrayList<>();
        while (rowSet.next()) {
            Contact contact = mapRowToContact(rowSet);
            allContacts.add(contact);
        }
        return allContacts;
    }

    /**
     * Mapping each row of SQL result to contact object.
     *
     * @param rowSet
     * @return contact
     */
    private Contact mapRowToContact(SqlRowSet rowSet) {
        return new Contact(
                rowSet.getInt("contact_id"),
                rowSet.getString("contact_name"),
                rowSet.getString("phone"),
                rowSet.getString("email"),
                rowSet.getString("city"),
                rowSet.getString("state"),
                rowSet.getInt("age"),
                rowSet.getString("social_link"));
    }
}

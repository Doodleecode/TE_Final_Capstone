package com.techelevator.dao;

import com.techelevator.model.Application;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcApplicationDao implements ApplicationDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcApplicationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return list of all applications in database ordered by time registered
     */
    @Override
    public List<Application> getAllApplications() {
        String sql = "SELECT * FROM application ORDER BY time_registered;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        return createListOfApplications(rowSet);
    }

    /**
     * @param statusId
     * @return list of applications by status ordered by time registered: A for approved, P for pending, R for rejected.
     */
    @Override
    public List<Application> getApplicationsByStatus(String statusId) {
        if (statusId == null) throw new IllegalArgumentException("Status ID cannot be null");
        String sql = "SELECT * FROM application a " +
                " JOIN status s ON a.status_id " +
                " WHERE status_id = ? " +
                " ORDER BY time_registered";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, statusId);
        return createListOfApplications(rowSet);
    }

    /**
     * Note: This method is to be modified or deleted based on required functions of the app.
     *
     * @param userId
     * @return list of applications by user ID ordered by time registered.
     */
    @Override
    public List<Application> getApplicationsByUserId(int userId) {
        if (userId == 0) throw new IllegalArgumentException("User ID cannot be 0");
        String sql = "SELECT * FROM application a " +
                " JOIN contact c on a.contact_id = c.contact_id " +
                " JOIN user u on c.contact_id = u.contact_id " +
                " WHERE user_id = ?" +
                " ORDER BY time_registered";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        return createListOfApplications(rowSet);
    }

    /**
     *
     * @param applicationId : application ID to update status ID
     * @param newStatusId : new status
     */
    @Override
    public void updateStatus(int applicationId, String newStatusId) {
        String sql = "UPDATE application SET status_id = ? WHERE application_id = ?";
        jdbcTemplate.update(sql, newStatusId, applicationId);
    }

    /**
     * Inserts contact info to contact table in database.
     *
     * @param application
     */
    @Override
    public Long createApp(Application application) {
        String sql = "INSERT INTO application (contact_id, status_id, weekly_hours, is_day, preferred_animal, reason, time_registered) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING application_id;";
        return jdbcTemplate.queryForObject(sql, Long.class, application.getContactId(), application.getStatusId(),
                application.getWeeklyHours(), application.isDay(), application.getPreferredAnimal(),
                application.getReason(), application.getTimeRegistered());
    }

    /**
     * @param rowSet
     * @return List of application objects
     */
    private List<Application> createListOfApplications(SqlRowSet rowSet) {
        List<Application> allContacts = new ArrayList<>();
        while (rowSet.next()) {
            Application application = mapRowToApplication(rowSet);
            allContacts.add(application);
        }
        return allContacts;
    }

    /**
     * Mapping each row of SQL result to application object.
     *
     * @param rowSet
     * @return application
     */
    private Application mapRowToApplication(SqlRowSet rowSet) {
        return new Application(
                rowSet.getInt("application_id"),
                rowSet.getInt("contact_id"),
                rowSet.getString("status_id"),
                rowSet.getInt("weekly_hours"),
                rowSet.getBoolean("is_day"),
                rowSet.getString("preferred_animal"),
                rowSet.getString("reason"),
                rowSet.getTimestamp("time_registered").toLocalDateTime());
    }
}

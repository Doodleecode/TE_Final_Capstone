package com.techelevator.dao;

import com.techelevator.model.Application;
import java.util.List;

public interface ApplicationDao {

    List<Application> getAllApplications();

    List<Application> getApplicationsByStatus(String statusId);

    List<Application> getApplicationsByUserId(int userId);

    void updateStatus(int applicationId, String newStatusId);

    Long create(Application application);
}

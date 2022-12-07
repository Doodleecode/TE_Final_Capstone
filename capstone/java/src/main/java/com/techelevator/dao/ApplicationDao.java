package com.techelevator.dao;

import com.techelevator.model.Application;
import com.techelevator.model.User;

import java.util.List;

public interface ApplicationDao {

    List<Application> getAllApplications();

    List<Application> getApplicationsByStatus(String statusId);

    List<Application> getApplicationsByUserId(int userId);

    void updateStatus(int applicationId, String newStatusId);

    Long createApp(Application application);
}

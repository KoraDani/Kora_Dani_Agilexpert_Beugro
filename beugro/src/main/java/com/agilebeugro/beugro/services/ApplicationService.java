package com.agilebeugro.beugro.services;

import com.agilebeugro.beugro.model.Application;
import com.agilebeugro.beugro.model.User;
import com.agilebeugro.beugro.repository.ApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {

    private List<Application> runningApplications = new ArrayList<Application>();

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private UserService userService;


    public Application addIcon(String iconName) {
        return this.applicationRepository.save(new Application(iconName));
    }

    public Application updateIcon(Application application) {
        return this.applicationRepository.save(application);
    }

    public Application getApplicationByName(String appName) {
        return this.applicationRepository.findByApplicationName(appName);
    }


    public void removeApplication(Application app) {
        this.applicationRepository.delete(app);
    }

    public List<Application> getAllApplication() {
        return this.applicationRepository.findAll();
    }

    public List<Application> getRunningApplications() {
        return runningApplications;
    }

    public void setRunningApplications(String applicationName,String userName) {
        Application application = this.getApplicationByName(applicationName);
        User currentUser = this.userService.getUserByUsername(userName);
        if (currentUser != null && currentUser.getApplication() != null) {
            if (application != null && currentUser.getApplication().contains(application)) {
                this.runningApplications.add(application);
            } else {
                System.out.println("No application found with name " + applicationName);
            }
        }
    }

    public void closeApplication(String applicationName) {
        Application application = this.getApplicationByName(applicationName);
        if (application != null) {
            this.runningApplications.remove(application);
        }
    }

    public boolean installAppForUser(String app, String userName) {
        Application application = this.applicationRepository.findByApplicationName(app);
        User currentUser = this.userService.getUserByUsername(userName);
        if (application != null && currentUser != null) {
            currentUser.setApplication(application);
            return this.userService.save(currentUser) != null;
        }
        return false;
    }

    @Transactional
    public boolean deleteApplicationFromUser(String app, String userName) {
        Application application = applicationRepository.findByApplicationName(app);
        User currentUser = userService.getUserByUsername(userName);

        if (application != null && currentUser != null) {
            currentUser.deleteApplicationFromUser(application);// updates both sides
            System.out.println(currentUser);
            this.userService.save(currentUser); // required
            return true;
        }

        return false;
    }

    public String getAllApplicationForAI() {
        List<Application> applications = this.applicationRepository.findAll();
        String app = "";
        if (!applications.isEmpty()) {
            for (Application application : applications) {
                app += application.toString();
            }
        }else {
            return "No applications found";
        }
//        System.out.println("Application List: " + app);
        return app;
    }
}

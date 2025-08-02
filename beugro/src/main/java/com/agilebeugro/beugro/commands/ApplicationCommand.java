package com.agilebeugro.beugro.commands;

import com.agilebeugro.beugro.model.Application;
import com.agilebeugro.beugro.services.ApplicationService;
import com.agilebeugro.beugro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;


@ShellComponent
public class ApplicationCommand {

    private final ApplicationService applicationService;
    private final UserService userService;

    @Autowired
    public ApplicationCommand(ApplicationService applicationService, UserService userService) {
        this.applicationService = applicationService;
        this.userService = userService;
    }

    @ShellMethod(key = "application", value = "Application submenu")
    public void application() {
        System.out.println("-------------------------------------------Application----------------------------------------\n");
        System.out.println("list-all-app\t install-app\t delete-app\t start-app\t running-app\t close-app\t app-operation\n ");
        this.devider();
    }

    @ShellMethod(key = "app-operation", value = "Application operation submenu")
    public void applicationOperation() {
        System.out.println("--------------------------------------Application operation-----------------------------------\n");
        System.out.println("add-app\t update-app\t remove-app\n");
        this.devider();
    }

    @ShellMethod(key = "add-app", value = "Adding new Icon")
    public void addIcon(String iconName) {
        this.application();
        this.applicationOperation();
        if (this.applicationService.addIcon(iconName) != null) {
            System.out.println("Icon added successfully");
        } else {
            System.out.println("Icon could not be added");
        }
        this.devider();
    }

    @ShellMethod(key = "update-app", value = "Updating Icon")
    public void updateIcon(String oldName, String newName) {
        Application app = this.applicationService.getApplicationByName(oldName);
        this.application();
        this.applicationOperation();
        if (app != null) {
            app.setApplicationName(newName);
            if (this.applicationService.updateIcon(app) != null) {
                System.out.println("Icon updated successfully");
            }
        }
        this.devider();
    }

    @ShellMethod(key = "remove-app", value = "Remove Application From Database")
    public void removeIcon(String name) {
        this.application();
        this.applicationOperation();
        Application app = this.applicationService.getApplicationByName(name);
        if (app != null) {
            this.applicationService.removeApplication(app);

            System.out.println(this.applicationService.getAllApplication().toString());
        }
        this.devider();
    }

    @ShellMethod(key = "install-app", value = "Installing app to the user")
    public void installApp(String app, String username) {
        this.application();
        if (this.applicationService.installAppForUser(app, username)) {
            System.out.println("App installed successfully");
        } else {
            System.out.println("App could not be installed");
        }
        this.devider();
    }

    @ShellMethod(key = "delete-app", value = "Delete application from user device")
    public void deleteAppFromUser(String app, String username) {
        this.application();
        if (this.applicationService.deleteApplicationFromUser(app, username)) {
            System.out.println("App deleted successfully");
        } else {
            System.out.println("App could not be deleted");
        }
        this.devider();
    }

    @ShellMethod(key = "start-app", value = "Starting application")
    public void startApplication(String applicationName, String username) {
        this.application();
        this.applicationService.setRunningApplications(applicationName, username);
        this.devider();
    }

    @ShellMethod(key = "close-app", value = "Closing application")
    public void closeApplication(String applicationName) {
        this.application();
        this.applicationService.closeApplication(applicationName);
        this.devider();
    }

    @ShellMethod(key = "running-app", value = "Running applications")
    public void runningApplications() {
        this.application();
        System.out.println(this.applicationService.getRunningApplications().toString());
        this.devider();
    }

    @ShellMethod(key = "list-all-app", value = "Listing all application")
    public void listAllApplications() {
        this.application();
        List<Application> allApps = this.applicationService.getAllApplication();
        if (!allApps.isEmpty()) {
            for (Application app : allApps) {
                System.out.print(app.toString());
            }
        } else {
            System.out.println("No applications found");
        }
        this.devider();
    }

    public void devider() {
        System.out.println("\n");
        System.out.println("----------------------------------------------------------------------------------------------\n");
    }

}

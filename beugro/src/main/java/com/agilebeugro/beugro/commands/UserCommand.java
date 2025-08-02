package com.agilebeugro.beugro.commands;

import com.agilebeugro.beugro.model.User;
import com.agilebeugro.beugro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;


@ShellComponent
public class UserCommand {


    private final UserService userService;

    @Autowired
    public UserCommand(UserService userService) {
        this.userService = userService;
    }

    @ShellMethod(key = "users")
    public void users() {
        System.out.println("------------------------------------------Users------------------------------------------------\n");
        System.out.println("login \t add-user \t update-user \t remove-user \t list-all-user \n");
        this.devider();
    }


    @ShellMethod(key = "add-user", value = "Add new user to the database")
    public void userAdd(String name){
        this.users();
        User user = this.userService.save(new User(name));
        if(user != null){
            System.out.println(user.getUserId()+" added to the database");
        }
        users();
        this.devider();
    }

    @ShellMethod(key = "update-user", value = "Update the user")
    public void userUpdate(String selectedUser, String newName){
        this.users();
        User user = this.userService.getUserByUsername(selectedUser);
        if(user != null){
            System.out.println("Selected User: " + user.getUsername());
            user.setUsername(newName);
            User user2 = this.userService.save(user);
            System.out.println(user2.getUsername()+" successfully updated");
        }
        users();
        this.devider();
    }

    @ShellMethod(key = "remove-user", value = "Remove user from the database")
    public void userRemove(String name){
        this.users();
        this.userService.deleteUserByUsername(name);
        System.out.println(name+ " User removed from the database\n");
        users();
        this.devider();
    }

    @ShellMethod(key = "list-all-user", value = "List All User")
    public void listAllUser() {
        this.users();
        for (User user : this.userService.getAllUsers()) {
            System.out.print(user.getUsername()+"\t");
        }
        this.devider();
    }

    @ShellMethod(key = "list-all-info", value = "Shows all information about the selected user")
    public void listAllInfoAboutUser(String selectedUser) {
        this.users();
        User user = this.userService.getUserByUsername(selectedUser);
        if(user.getUsername() != null){
            System.out.println(user.toString());
        }
        this.devider();
    }

    public void devider() {
        System.out.println("\n");
        System.out.println("----------------------------------------------------------------------------------------------\n");
    }

//    @ShellMethodAvailability({"users","add-user", "remove-user","update-user","list-all-user"})
//    public Availability isUserAvailable() {
//        System.out.println(this.userService.getCurrentUser());
//        return this.userService.getCurrentUser().getUsername() != null ? Availability.available() : Availability.unavailable("User not found");
//    }

}

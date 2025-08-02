package com.agilebeugro.beugro.commands;

import com.agilebeugro.beugro.model.BackGround;
import com.agilebeugro.beugro.services.BackGroundService;
import com.agilebeugro.beugro.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class BackGroundCommand {

    private BackGroundService backGroundService;
    private UserService userService;

    @Autowired
    public BackGroundCommand(BackGroundService backGroundService, UserService userService) {
        this.backGroundService = backGroundService;
        this.userService = userService;
    }

    @ShellMethod(key = "background", value = "BackGround submenu")
    public void backGround() {
        System.out.println("------------------------------------------Back Ground-----------------------------------------\n");
        System.out.println("list-all-bg\t select-bg\t add-bg\t update-bg\t delete-bg\n");
        this.devider();
    }

    @ShellMethod(key = "add-bg", value = "Add new BackGround")
    public void addBackGround(String backGroundName) {
        this.backGround();
        if(this.backGroundService.save(backGroundName) != null) {
            System.out.println("Successfully added BackGround");
        }
        this.listAllBackGround();
    }

    @ShellMethod(key = "update-bg", value = "Updating BackGround")
    public void updateBackGround(String backGroundName, String newBackGroundName) {
        this.backGround();
        if (this.backGroundService.updateBackGround(backGroundName, newBackGroundName) != null){
            System.out.println("Successfully updated BackGround");
        }
        this.listAllBackGround();
    }

    @ShellMethod(key = "remove-bg", value = "Remove Background")
    public void removeBackGround(String name) {
        this.backGround();
        this.backGroundService.deleteBackGround(name);
        this.listAllBackGround();
    }

    @ShellMethod(key = "select-bg", value = "Select BackGround")
    public void selectBackGround(String backGroundName, String userName) {
        this.backGround();
        if(this.backGroundService.selectBackGround(backGroundName, userName)){
            System.out.println("Successfully selected"+ backGroundName+" BackGround to " + userName);
        }else {
            System.out.println("No such BackGround");
        }
        this.devider();
    }

    @ShellMethod(key = "list-all-bg", value = "Listing all backgrounds")
    public void listAllBackGround() {
        this.backGround();

        List<BackGround> backGrounds = this.backGroundService.getAll();
        if(!backGrounds.isEmpty()){
            for (BackGround backGround : backGrounds) {
                System.out.print(backGround.toString());
            }
        }else {
            System.out.println("No BackGrounds found");
        }
        this.devider();
    }

    public void devider(){
        System.out.println("\n");
        System.out.println("----------------------------------------------------------------------------------------------\n");
    }


}

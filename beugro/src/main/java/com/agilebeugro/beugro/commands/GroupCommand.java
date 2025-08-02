package com.agilebeugro.beugro.commands;

import com.agilebeugro.beugro.model.Group;
import com.agilebeugro.beugro.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class GroupCommand {
    private final GroupService groupService;

    @Autowired
    public GroupCommand(GroupService groupService) {
        this.groupService = groupService;
    }

    @ShellMethod(key = "group", value = "Group menu")
    public void group() {
        System.out.println("--------------------------------------------Group----------------------------------------------\n");
        System.out.println("list-all-groups\t add-user-group\t rm-user-group\t group-operqtion\t\n");
        this.devider();
    }

    @ShellMethod(key = "group-operation", value = "Group operation menu")
    public void groupOperation() {
        this.devider();
        System.out.println("create-group\t update-group\t delete-group\t change-appear\t delete-appear\t\n");
        this.devider();
    }

    @ShellMethod(key = "create-group")
    public void createGroup(String groupName) {
        this.group();
        this.groupOperation();
        this.groupService.saveGroup(groupName);
        this.devider();
    }

    @ShellMethod(key = "update-group")
    public void updateGroup(String oldGroupName, String newGroupName) {
        this.group();
        this.groupOperation();
        this.groupService.updateGroup(oldGroupName, newGroupName);
        this.devider();
    }

    @ShellMethod(key = "delete-group")
    public void deleteGroup(String groupName) {
        this.group();
        this.groupOperation();
        this.groupService.deleteGroup(groupName);
        this.devider();
    }

    @ShellMethod(key = "list-all-group")
    public void listAllGroup() {
        this.group();
        List<Group> group = this.groupService.listAllGroup();
        if (!group.isEmpty()) {
            for (Group g : group) {
                System.out.print(g.toString());
            }
        } else {
            System.out.println("No group found");
        }
        this.devider();
    }

    @ShellMethod(key = "add-user-group")
    public void addUserGroup(String groupName, String userName) {
        this.group();
        this.groupService.addUserToGroup(groupName, userName);
        this.devider();
    }

    @ShellMethod(key = "rm-user-group")
    public void removeUserFromGroup(String groupName, String username) {
        this.group();
        this.groupService.removeUserFromGroup(username, groupName);
        this.devider();
    }



    @ShellMethod(key = "change-appear", value = "Changing appearance")
    public void changeAppearance(String groupName, String appearance) {
        this.group();
        this.groupOperation();
        this.groupService.changeGroupAppearance(groupName, appearance);
        this.devider();
    }

    @ShellMethod(key = "delete-appear", value = "Delete appearance")
    public void deleteAppearance(String groupName) {
        this.group();
        this.groupOperation();
        this.groupService.changeGroupAppearance(groupName, "Sample Text");
        this.devider();
    }

    public void devider() {
        System.out.println("\n");
        System.out.println("----------------------------------------------------------------------------------------------\n");
    }

}

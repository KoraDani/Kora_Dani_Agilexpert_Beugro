package com.agilebeugro.beugro.services;

import com.agilebeugro.beugro.model.Group;
import com.agilebeugro.beugro.model.User;
import com.agilebeugro.beugro.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserService userService;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserService userService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    public Group saveGroup(String groupName) {
        return this.groupRepository.save(new Group(groupName));
    }

    public Group updateGroup(String oldGroupName, String newGroupName) {
        Group group = this.getGroupByName(oldGroupName);
        group.setGroupName(newGroupName);
        if (group != null) {
            System.out.println("Group " + oldGroupName + " updated");
            return this.groupRepository.save(group);
        }
        System.out.println("Group " + oldGroupName + " not updated");
        return null;
    }

    private Group getGroupByName(String oldGroupName) {
        return this.groupRepository.getGroupByGroupName(oldGroupName);
    }

    public boolean removeUserFromGroup(String username, String groupName) {
        Group group = this.groupRepository.getGroupByGroupName(groupName);
        User user = this.userService.getUserByUsername(username);

        if (group != null && user != null) {
            user.setGroups(null);
            System.out.println("Group " + groupName + " removed from user " + username);
            this.userService.save(user);
            return true;

        }
        System.out.println("Group " + groupName + " not removed from user " + username);
        return false;
    }

    public void addUserToGroup(String groupName, String userName) {
        Group group = this.getGroupByName(groupName);
        User user = this.userService.getUserByUsername(userName);

        if (group.getGroupName() != null && user.getUsername() != null) {
            user.setGroups(group);
            this.userService.save(user);
            System.out.println("Group " + groupName + " added to user " + userName);
        }
        System.out.println("Group " + groupName + " not added to user " + userName);

    }

    public boolean deleteGroup(String groupName) {
        Group group = this.getGroupByName(groupName);
        if (group != null) {
            this.groupRepository.delete(group);
            System.out.println("Group " + groupName + " removed");
            return true;
        }
        System.out.println("Group " + groupName + " not removed");
        return false;
    }

    public boolean changeGroupAppearance(String groupName, String appearance) {
        Group group = this.getGroupByName(groupName);
        if (group.getGroupName() != null) {
            group.setAppearance(appearance);
            this.groupRepository.save(group);
            System.out.println("Group " + groupName + " appearance set to " + appearance);
            return true;
        }
        System.out.println("Group " + groupName + " not appearance set to " + appearance);
        return false;
    }

    public List<Group> listAllGroup() {
        return this.groupRepository.findAll();
    }

    public String listAllGroupForAI() {
        List<Group> groups = this.listAllGroup();
        String groupList = "";
        if(!groups.isEmpty()) {
            for (Group group : groups) {
                groupList += group.toString() + "\n";
            }
        }else {
            return "No groups found";
        }
//        System.out.println("GroupList: " + groupList);
        return groupList;
    }
}

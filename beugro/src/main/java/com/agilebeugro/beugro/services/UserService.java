package com.agilebeugro.beugro.services;

import com.agilebeugro.beugro.model.User;
import com.agilebeugro.beugro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User getUserByUsername(String selectedUser) {
        return this.userRepository.getUserByUsername(selectedUser);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public void deleteUserByUsername(String user) {
        User userToDelete = this.userRepository.getUserByUsername(user);
        if (user != null) {
            this.userRepository.delete(userToDelete);
        }
    }


    public String getAllUsersForAI() {
        List<User> users = this.userRepository.findAll();
        String allUsers = "";
        if(!users.isEmpty()){
            for (User user : users) {
                allUsers += user.toString() + "\n";
            }
        }else {
            return "No Users Found";
        }
//        System.out.println("User List: " + allUsers);
        return allUsers;
    }
}

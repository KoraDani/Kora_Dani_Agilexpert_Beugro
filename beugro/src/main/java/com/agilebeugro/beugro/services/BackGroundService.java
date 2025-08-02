package com.agilebeugro.beugro.services;

import com.agilebeugro.beugro.model.BackGround;
import com.agilebeugro.beugro.model.User;
import com.agilebeugro.beugro.repository.BackGroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackGroundService {

    @Autowired
    private BackGroundRepository backGroundRepository;
    @Autowired
    private UserService userService;

    public List<BackGround> getAll() {
        return backGroundRepository.findAll();
    }

    public BackGround getBackGroundByName(String name) {
        return this.backGroundRepository.getBackGroundByBackGroundName(name);
    }

    public BackGround save(String backGroundName) {
        return this.backGroundRepository.save(new BackGround(backGroundName));
    }

    public BackGround updateBackGround(String backGroundName, String newBackGroundName) {
        BackGround bg = this.backGroundRepository.getBackGroundByBackGroundName(backGroundName);
        if(bg != null) {
            bg.setBackGroundName(newBackGroundName);
            return this.backGroundRepository.save(bg);
        }
        return null;
    }

    public void deleteBackGround(String backGroundName) {
        BackGround backGround = this.backGroundRepository.getBackGroundByBackGroundName(backGroundName);
        if(backGround != null) {
            this.backGroundRepository.delete(backGround);
        }
    }

    public boolean selectBackGround(String backGroundName, String userName) {
        BackGround bg = this.backGroundRepository.getBackGroundByBackGroundName(backGroundName);
        User currentUser = this.userService.getUserByUsername(userName);
        if(bg != null && currentUser != null) {
            currentUser.setBackGround(bg);
            return this.userService.save(currentUser) != null;
        }
        return false;
    }

    public String getAllBackGroundForAI() {
        List<BackGround> allbg = this.backGroundRepository.findAll();
        String result = "";
        if(!allbg.isEmpty()) {
            for(BackGround bg : allbg) {
                result += bg.toString()+"\n";
            }
        }else {
            return "No Back Ground Found";
        }
//        System.out.println("BackGround List: " + result);
        return result;
    }
}

package com.agilebeugro.beugro.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    private String username;
    @OneToOne
    @JoinColumn(name = "backGroundId")
    @Nullable
    private BackGround backGround;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> application = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group groups;

    public User(String username) {
        this.username = username;
    }

    public void setApplication(Application application) {
        application.setUser(this); //back reference to the user
        this.application.add(application);
    }

    public void deleteApplicationFromUser(Application application) {
        this.application.remove(application);
        application.setUser(null);
        System.out.println(this.application.contains(application));
    }



}

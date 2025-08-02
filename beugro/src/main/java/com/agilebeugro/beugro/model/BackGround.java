package com.agilebeugro.beugro.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BackGround {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "backGroundId")
    private int backGroundId;
    private String backGroundName;
    @OneToOne(mappedBy = "backGround")
    @Nullable
    private User user;

    public BackGround(String backGroundName) {
        this.backGroundName = backGroundName;
    }

    @Override
    public String toString() {
        return "BackGround Name: " + backGroundName + '\t';
    }
}

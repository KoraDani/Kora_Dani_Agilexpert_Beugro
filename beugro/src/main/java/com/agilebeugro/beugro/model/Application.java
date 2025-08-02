package com.agilebeugro.beugro.model;

import jakarta.persistence.*;

import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int applicationId;
    private String applicationName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Application(String applicationName) {
        this.applicationName = applicationName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;
        Application that = (Application) o;
        return applicationId != 0 && applicationId == that.applicationId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(applicationId);
    }

    @Override
    public String toString() {
        return "Application Name: " + applicationName + '\t';
    }
}

package com.agilebeugro.beugro.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;
    private String groupName;
    @OneToMany(mappedBy = "groups", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    List<User> users = new ArrayList<>();

    private String appearance;

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public void setUsers(User users) {
        this.users.add(users);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(groupId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group that = (Group) o;
        return groupId != 0 && groupId == that.groupId;
    }

    @Override
    public String toString() {
        return
                "Group Name=" + groupName + '\t' +
                ", Appearance='" + appearance +'\n';
    }
}

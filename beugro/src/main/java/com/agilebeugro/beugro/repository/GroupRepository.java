package com.agilebeugro.beugro.repository;

import com.agilebeugro.beugro.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group getGroupByGroupName(String groupName);
}

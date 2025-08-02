package com.agilebeugro.beugro.repository;

import com.agilebeugro.beugro.model.BackGround;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackGroundRepository extends JpaRepository<BackGround, Integer> {
    BackGround getBackGroundByBackGroundName(String backGroundName);
}

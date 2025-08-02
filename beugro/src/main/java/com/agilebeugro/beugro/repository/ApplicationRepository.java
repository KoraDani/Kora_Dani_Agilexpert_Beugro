package com.agilebeugro.beugro.repository;

import com.agilebeugro.beugro.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Application findByApplicationName(String appName);
}

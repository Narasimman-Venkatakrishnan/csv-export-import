package com.csv.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csv.project.model.CSVCode;

@Repository
public interface CSVDataRepo extends JpaRepository<CSVCode,String> {
}

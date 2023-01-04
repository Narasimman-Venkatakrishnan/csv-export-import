package com.csv.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csv.project.helper.CSVHelper;
import com.csv.project.model.CSVCode;
import com.csv.project.repository.CSVDataRepo;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CSVService {

    @Autowired
    CSVDataRepo repository;

    public void save(MultipartFile file) {
        try {
            List<CSVCode> codes = CSVHelper.csvToList(file.getInputStream());
            repository.saveAll(codes);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<CSVCode> getAllOrders() {
        return repository.findAll();
    }


    public List<CSVCode> getDataByCode(String code){

        return repository.findAll().stream()
                .filter(s->s.getCode().contentEquals(code))
                .collect(Collectors.toList());
    }

    public void deleteAllData(){

    	 try {
             repository.deleteAll();
         } catch (Exception e) {
             throw new RuntimeException("fail to delete csv data: " + e.getMessage());
         }
    }
    
}


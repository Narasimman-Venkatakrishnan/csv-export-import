package com.csv.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.csv.project.helper.CSVHelper;
import com.csv.project.model.CSVCode;
import com.csv.project.service.CSVService;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/api/csv")
public class CSVController {

        @Autowired
        CSVService fileService;

        @PostMapping("/upload")
        public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
            String message = "";

            if (CSVHelper.hasCSVFormat(file)) {
                fileService.save(file);

                try {
                    fileService.save(file);
                    message = "Uploaded the file successfully: " + file.getOriginalFilename();
                    return ResponseEntity.status(HttpStatus.OK).body( "\" message \": \" "+ message +" \"");
                } catch (Exception e) {
                    message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("\" message \": \" "+ message +" \"");
                }
            }
            message = "Please upload a csv file!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\" message \": \" "+ message +" \"");
        }
    //1- SELECT * FROM Order;
    @GetMapping("/fetch")
    public ResponseEntity<List<CSVCode>> getAllCodes() {
        try {
            List<CSVCode> codes = fileService.getAllOrders();

            if (codes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<CSVCode>>(codes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //2-Stream :Get Order By Customer Name
    @GetMapping(value = "/findByCode/{code}")
    public ResponseEntity<List<CSVCode>> getDataByCode (@PathVariable String code) {
        try {
            List<CSVCode> codes = fileService.getDataByCode(code);

            if (codes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<CSVCode>>(codes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
  //2-Stream :Get Order By Customer Name
    @DeleteMapping(value = "/removeAllData")
    public ResponseEntity<String> deleteAllData() {
        try {
            fileService.deleteAllData();
            String message = "records deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body( "\" message \": \" "+ message +" \"");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

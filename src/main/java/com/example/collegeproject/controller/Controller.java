package com.example.collegeproject.controller;

import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.collegeproject.models.CollegeInfo;
import com.example.collegeproject.service.CollegeService;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class Controller {

    private final CollegeService collegeService;

    public Controller(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping("/getcolleges")
    public List<CollegeInfo> getCollegeStats() {
        return collegeService.getCollegeStats();
    }
    
    
    @PostMapping(path = "/addcollege")
    public void registerNewCollege(@RequestBody CollegeInfo collegeInfo) {
        collegeService.addNewCollege(collegeInfo);
    }

    @DeleteMapping("/{collegeId}")
    public void deleteCollege(@PathVariable("collegeId") Integer CollegeId) {
        collegeService.deleteCollege(CollegeId);
    }

    @PutMapping
    public void updateCollege(
        @RequestBody(required = false) CollegeInfo college) {
        collegeService.updateCollege(college);
    }

}

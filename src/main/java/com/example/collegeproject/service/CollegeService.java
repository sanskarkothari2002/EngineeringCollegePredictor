package com.example.collegeproject.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.collegeproject.models.CollegeInfo;
import com.example.collegeproject.repository.CollegeRepository;

import jakarta.transaction.Transactional;

@Service
public class CollegeService {

    private final CollegeRepository collegeRepository;
    
    public CollegeService(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    public List<CollegeInfo> getCollegeStats() {
        return collegeRepository.findAll();
    }

    public void addNewCollege(CollegeInfo college) {

        Optional<CollegeInfo> collegeOptional = collegeRepository.findCollegeByCollegeCode(college.getCollegeCode());

        if(collegeOptional.isPresent()){
            throw new IllegalStateException("College already exist");
        }

        collegeRepository.save(college);
    }

    public void deleteCollege(Integer collegeId) {
        boolean exists = collegeRepository.existsById(collegeId);

        if(!exists){
            throw new IllegalStateException("College with id = " + collegeId + " does not found");
        }
        collegeRepository.deleteById(collegeId);
    }

    @Transactional
    public void updateCollege(CollegeInfo college) {

        Optional<CollegeInfo> collegeupdate = collegeRepository.findCollegeByCollegeCode(college.getCollegeCode());

        if(!collegeupdate.isPresent()){
            throw new IllegalStateException("College with code = " + college.getCollegeCode() + " does not exist");
        }
        
        if(college.getCollegeName() != null && college.getCollegeName().length() > 0) {
            college.setCollegeName(college.getCollegeName());
        } 

        if(college.getBranchCutoff() != null && college.getBranchCutoff().length() > 0) {
            college.setBranchCutoff(college.getBranchCutoff());
        }

        collegeRepository.save(college);

    }

}

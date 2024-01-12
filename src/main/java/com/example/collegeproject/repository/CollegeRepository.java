package com.example.collegeproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.collegeproject.models.CollegeInfo;

@Repository
public interface CollegeRepository extends JpaRepository<CollegeInfo,Integer>{
    
    Optional<CollegeInfo> findCollegeByCollegeCode(Integer collegecode);
}

package com.example.collegeproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "college_info")
public class CollegeInfo {

    @Id
    @SequenceGenerator(
        name = "college_sequence",
        sequenceName = "college_sequence",
        allocationSize = 1
    )

    private Integer collegeCode;

    private String collegeName;
    
    @Column(columnDefinition = "text")
    private String branchCutoff;

}
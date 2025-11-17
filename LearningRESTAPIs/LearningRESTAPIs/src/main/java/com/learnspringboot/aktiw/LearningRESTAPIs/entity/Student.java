package com.learnspringboot.aktiw.LearningRESTAPIs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {             //creates Student table in database

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                          //fields we require

    private String name;
    private String email;
}

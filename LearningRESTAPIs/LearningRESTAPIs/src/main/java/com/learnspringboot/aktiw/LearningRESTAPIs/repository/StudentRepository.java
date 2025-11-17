package com.learnspringboot.aktiw.LearningRESTAPIs.repository;

import com.learnspringboot.aktiw.LearningRESTAPIs.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {

}

//This Layer helps to write a query (we do not connect entity to database directly we need repository layer to write a query)
//by extending JPA Repository(Ctrl + click) we get data manipulation(CRUD) like CREATE , UPDATE , DELETE ,etc.
//type of JPA Repository is Student Entity with Long ID type.
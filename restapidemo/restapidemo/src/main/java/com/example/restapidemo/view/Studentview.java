package com.example.restapidemo.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapidemo.model.Student;

@Repository
public interface Studentview extends JpaRepository<Student, Long>{

}
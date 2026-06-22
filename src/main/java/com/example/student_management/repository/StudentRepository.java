package com.example.student_management.repository;
import com.example.student_management.model.Student;

import org.springframework.data.jpa.repository.JpaRepository; // provide ready made methods findall()
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

}

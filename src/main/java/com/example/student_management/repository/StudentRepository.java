package com.example.student_management.repository;
import com.example.student_management.model.Student;

import org.springframework.data.jpa.repository.JpaRepository; // provide ready made methods findall()
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{
    List<Student> findByCourse(String course);
    boolean existsByEmail(String mail);

}


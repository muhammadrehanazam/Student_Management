package com.example.student_management.controller;
import com.example.student_management.repository.StudentRepository;
import com.example.student_management.model.Student;

import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@RestController
@RequestMapping("api/students")

public class StudentController {
    @Autowired
    private StudentRepository studentRepository; // give me a object of that

    // Get students
    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    // post students
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }
 // update student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updateStudent){
        return studentRepository.findById(id)
                .map(student-> {
                    student.setName(updateStudent.getName());
                    student.setEmail(updateStudent.getEmail());
                    student.setCourse(updateStudent.getCourse());
                    return studentRepository.save(student);
                })
                .orElseThrow(()->new RuntimeException("Student not found with id: " + id));
    }
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        // 1. Check if the task exists in the database first
        if (!studentRepository.existsById(id)) {
            return "Error: Student with ID " + id + " does not exist or has already been deleted.";
        }

        // 2. If it exists, delete it
        studentRepository.deleteById(id);
        return "Student with ID " + id + " deleted successfully!";
    }
    @PatchMapping("/{id}")
    public Student patchStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {

        return studentRepository.findById(id)
                .map(student -> {

                    if (updatedStudent.getName() != null) {
                        student.setName(updatedStudent.getName());
                    }

                    if (updatedStudent.getEmail() != null) {
                        student.setEmail(updatedStudent.getEmail());
                    }

                    if (updatedStudent.getCourse() != null) {
                        student.setCourse(updatedStudent.getCourse());
                    }

                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }


}


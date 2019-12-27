package com.hibernate.Hibernate.controller;

import com.hibernate.Hibernate.exeption.notFoundException;
import com.hibernate.Hibernate.model.Students;
import com.hibernate.Hibernate.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Students> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Students getStudentById(@PathVariable Long id){
        Optional<Students> optStudent = studentRepository.findById(id);
        if (optStudent.isPresent()){
            return optStudent.get();
        }else {
            throw new notFoundException("Student not found with id " + id);
        }
    }

    @PostMapping("/students")
    public Students createStudent(@Valid @RequestBody Students student){
        return studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    public Students updateStudent(@PathVariable Long id, @Valid @RequestBody Students studentUpdate){
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(studentUpdate.getName());
                    student.setAge(studentUpdate.getAge());
                    return studentRepository.save(student);
                }).orElseThrow(() -> new notFoundException("Student not found with id " + id));
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id){
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.delete(student);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new notFoundException("Not Found Student with id " + id));
    }
}

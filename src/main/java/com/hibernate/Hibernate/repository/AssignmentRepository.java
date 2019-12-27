package com.hibernate.Hibernate.repository;

import com.hibernate.Hibernate.model.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignments, Long> {

    List<Assignments> findByStudentId(Long studentId);

}

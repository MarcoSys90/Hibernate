package com.hibernate.Hibernate.repository;

import com.hibernate.Hibernate.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Students, Long> {
}

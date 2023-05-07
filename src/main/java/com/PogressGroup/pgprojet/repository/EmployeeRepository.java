package com.PogressGroup.pgprojet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PogressGroup.pgprojet.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}

package com.sbszc.eduspringbootdatajpa.repository;

import com.sbszc.eduspringbootdatajpa.entity.Student;
import com.sbszc.eduspringbootdatajpa.entity.StudentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, StudentId> {

}

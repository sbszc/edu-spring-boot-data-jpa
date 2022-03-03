package com.sbszc.eduspringbootdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbszc.eduspringbootdatajpa.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}

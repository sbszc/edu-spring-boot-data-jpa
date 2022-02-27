package com.sbszc.springbootdatajpatutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbszc.springbootdatajpatutorial.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}

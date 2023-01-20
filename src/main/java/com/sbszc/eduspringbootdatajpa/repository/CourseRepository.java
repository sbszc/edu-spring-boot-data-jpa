package com.sbszc.eduspringbootdatajpa.repository;

import com.sbszc.eduspringbootdatajpa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}

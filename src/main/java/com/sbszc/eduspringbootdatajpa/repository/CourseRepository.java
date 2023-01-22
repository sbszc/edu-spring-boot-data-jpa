package com.sbszc.eduspringbootdatajpa.repository;

import com.sbszc.eduspringbootdatajpa.entity.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    //named query findBy<PropertyName>
    Course findByTitle(String title);

    //named query findBy<PropertyName><InnerPropertyName>
    List<Course> findByCourseTeacherName(String name);

    //JPQL query
    @Query("SELECT c FROM Course c WHERE c.courseMaterial.url = ?1")
    Course queryByCourseMaterialUrl(String url);

    //Native query + Named Param (Warning:with native queries, named params need a separation from the semicolon ;, semicolon is optional if there's only one statement)
    @Query(
            value = "SELECT * FROM tbl_course c WHERE c.title = :title ;",
            nativeQuery = true)
    Course nativeQueryByTitle(String title);

    @Transactional
    @Modifying
    @Query("UPDATE Course c "
            + "SET c.credits = :credits "
            + "WHERE c.title = :title")
    int updateCourseCreditsByTitle(String title, Integer credits);

    @Transactional
    @Modifying//Queries that require a `@Modifying` annotation include INSERT, UPDATE, DELETE, and DDL statements.
    @Query(
            value = "UPDATE tbl_course "
                    + "SET credits = :credits "
                    + "WHERE title = :title",
            nativeQuery = true)
    int nativeUpdateCourseCreditsByTitle(String title, Integer credits);
}

package com.sbszc.springbootdatajpatutorial.repository;

import com.sbszc.springbootdatajpatutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
	//named query
	List<Student> findByFirstName(String firstName);
	
	//named query
	Student findByEmail(String email);
	
	//query, JPQL
	@Query("SELECT s FROM Student s WHERE s.email = ?1")
	Student queryByEmail(String email);
	
	//Native query, Named Param
	@Query(
			value = "SELECT * FROM tbl_student s WHERE s.email_address = :email;", 
			nativeQuery = true)
	Student nativeQueryByEmail(String email);
	
	@Transactional
	@Modifying
	@Query("UPDATE Student s "
			+ "SET s.firstName = :firstName "
			+ "WHERE s.email = :email")
	int updateStudentNameByEmail(String email, String firstName);
	
	@Transactional
	@Modifying
	@Query(
			value = "UPDATE tbl_student "
					+ "SET first_name = :firstName "
					+ "WHERE email_address = :email ;",
			nativeQuery = true)
	int nativeUpdateStudentNameByEmail(String email, String firstName);
}

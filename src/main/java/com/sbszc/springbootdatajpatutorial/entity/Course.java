package com.sbszc.springbootdatajpatutorial.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
	@Id
	@SequenceGenerator(
			name = "seq_course",
			sequenceName = "seq_course",
			allocationSize = 1)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "seq_course")
	private Long courseId;
	private String title;
	private Integer credits;
	
	@OneToOne(
			mappedBy = "course") //bidirectional needs to exclude child entity from parent's @ToString
	private CourseMaterial courseMaterial;
	
	@ManyToOne(
			cascade = CascadeType.ALL) //cascade ALL operations to child entity
	@JoinColumn(
			name = "teacher_id",
			referencedColumnName = "teacherId")
	private Teacher teacher;
	
	@ManyToMany(
			cascade = CascadeType.ALL)
	@JoinTable(
			name = "tbl_course_student",
			joinColumns = @JoinColumn(
					name = "course_Id",
					referencedColumnName = "courseId"),
			inverseJoinColumns = @JoinColumn(
					name = "student_Id",
					referencedColumnName = "studentId"))
	private List<Student> students;
}

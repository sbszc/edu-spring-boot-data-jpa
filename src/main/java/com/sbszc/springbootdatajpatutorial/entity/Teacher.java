package com.sbszc.springbootdatajpatutorial.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {
	@Id
	@SequenceGenerator(
			name = "seq_teacher",
			sequenceName = "seq_teacher",
			allocationSize = 1)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "seq_teacher")
	private Long teacherId;
	private String firstName;
	private String lastName;
	
//Always is better map ManyToOne than OneToMany
//	@OneToMany(
//			cascade = CascadeType.ALL)
//	@JoinColumn(
//			name = "teacher_id",
//			referencedColumnName = "teacherId")
//	private List<Course> courses;
}

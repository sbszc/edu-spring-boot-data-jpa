package com.sbszc.eduspringbootdatajpa.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
		name = "tbl_student",
		uniqueConstraints = { 
				@UniqueConstraint(
						name = "unique_student_email",
						columnNames = "email_address") })
public class Student {
	@Id
	@SequenceGenerator(
			name = "seq_student",
			sequenceName = "seq_student",
			allocationSize = 1)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "seq_student")
	private Long studentId;
	
	private String firstName;
	private String lastName;
	
	@Column(
			name = "email_address",
			nullable = false)
	private String email;
	
	@Embedded
	private Guardian guardian;
}

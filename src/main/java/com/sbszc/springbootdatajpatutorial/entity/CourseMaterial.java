package com.sbszc.springbootdatajpatutorial.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_course_material")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "course")
@Builder
public class CourseMaterial {
	@Id
	@SequenceGenerator(
			name = "seq_course_material",
			sequenceName = "seq_course_material",
			allocationSize = 1)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "seq_course_material")
	private Long courseMaterialId;
	private String url;
	
	@OneToOne(
			cascade = CascadeType.ALL, //cascade ALL operations to child entity
			fetch = FetchType.LAZY, //LAZY doesn't include child entity when fetching, so it needs to be excluded from @ToString
			optional = false) //child entity is required to be saved
	@JoinColumn(
			name = "course_id",
			referencedColumnName = "courseId")
	private Course course;
}

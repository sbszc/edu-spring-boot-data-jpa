package com.sbszc.eduspringbootdatajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_course_teacher")
public class CourseTeacher {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "seq_course_teacher",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_course_teacher"
    )
    private Long courseTeacherId;

    @Column(nullable = false)
    private String name;

//Is preferable to map ManyToOne than OneToMany
//	@OneToMany(
//			cascade = CascadeType.ALL)
//	@JoinColumn(
//			name = "teacher_id",
//			referencedColumnName = "teacherId")
//	private List<Course> courses;
}

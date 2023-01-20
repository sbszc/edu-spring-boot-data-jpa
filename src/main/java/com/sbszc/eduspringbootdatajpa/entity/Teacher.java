package com.sbszc.eduspringbootdatajpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_teacher")
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

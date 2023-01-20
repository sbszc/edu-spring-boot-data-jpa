package com.sbszc.eduspringbootdatajpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_course")
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

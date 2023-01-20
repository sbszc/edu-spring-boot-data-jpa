package com.sbszc.eduspringbootdatajpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = "course")
@Entity
@Table(name = "tbl_course_material")
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

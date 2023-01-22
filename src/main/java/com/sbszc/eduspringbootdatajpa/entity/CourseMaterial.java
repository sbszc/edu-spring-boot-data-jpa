package com.sbszc.eduspringbootdatajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_course_material")
public class CourseMaterial {
    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "seq_course_material",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_course_material")
    private Long courseMaterialId;

    @Column(nullable = false)
    private String url;

//    @OneToOne(mappedBy = "courseMaterial")//this property is not added to the table, so is preferable not to put it
//    private Course course;
}

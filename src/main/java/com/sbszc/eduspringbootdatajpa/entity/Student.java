package com.sbszc.eduspringbootdatajpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(
        name = "tbl_student",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_student_email",
                        columnNames = "email_address")})
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


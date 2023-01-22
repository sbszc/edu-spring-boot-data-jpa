package com.sbszc.eduspringbootdatajpa.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_student")
public class Student {
    @EmbeddedId
    //@GeneratedValue is not supported to be used with @EmbeddedId, composed keys have to be manually generated
    private StudentId studentId;//have to be serializable
    private String name;
    private String email;
    private StudentGuardian studentGuardian;//doesn't have to be serializable
}

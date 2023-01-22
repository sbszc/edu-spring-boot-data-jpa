package com.sbszc.eduspringbootdatajpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"students"})//cuz @ManyToMany(fetch = FetchType.LAZY)

@Entity
@Table(
        name = "tbl_course",//default is <class_name>
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_tbl_course_title",//default is UK_<random_name>
                        columnNames = {"title"})//should be the <column_name>
        }
)
public class Course {
    @Id
    @Column(name = "id")//default is <property_name>
    @SequenceGenerator(
            name = "seq_course",//default is <table_name>_seq
            allocationSize = 1//default is 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,//default is auto
            generator = "seq_course"//point to @SequenceGenerator's name
    )
    private Long courseId;

    @Column(nullable = false)//default is true
    private String title;

    //@Column(unique = false)not recommendable, cuz is not possible to set a name, @Table(uniqueConstraints=..) is preferable
    private Integer credits;

    @OneToOne(
            optional = false, //default is true
            cascade = {CascadeType.ALL}//default is {}
    )
    @JoinColumn(name = "material_id")//default is <property_name>_<inner_id_column_name>
    private CourseMaterial courseMaterial;

    @ManyToOne(
            optional = false,//default is true
            cascade = {CascadeType.ALL}//default is {}
    )
    @JoinColumn(name = "teacher_id")//default is <property_name>_<inner_id_column_name>
    private CourseTeacher courseTeacher;

    //EAGER fetches child objects immediately when the parent is fetched, by doing a JOIN query,
    // what retrieves the parent object along with all the child objects(high memory costs, depending on the quantity),
    //LAZY doesn't fetch child objects immediately when the parent is fetched, so it doesn't do a JOIN query,
    // what retrieves a PROXY version of the parent object, which doesn't contain the child objects
    @ManyToMany(
            fetch = FetchType.LAZY,//for @ManyToMany or @OneToMany the default is LAZY, for @ManyToOne or @OneToOne the default is EAGER
            cascade = {CascadeType.ALL}//default is {}
    )
    @JoinTable(
            name = "tbl_course_student",//default is <table_name>_<property_name>
            joinColumns = {
                    @JoinColumn(
                            name = "course_id"//default is <id_property_name>
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "student_id",//default is <property_name>_<inner_id_column_name>
                            referencedColumnName = "id"//is preferable to specify referencedColumnName when primary key has multiple columns
                    ),
                    @JoinColumn(
                            name = "student_code",//default is <property_name>_<inner_id_column_name>
                            referencedColumnName = "code"//is preferable to specify referencedColumnName when primary key has multiple columns
                    )
            }
    )
    private List<Student> students;
}

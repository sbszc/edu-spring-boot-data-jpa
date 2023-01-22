package com.sbszc.eduspringbootdatajpa.repository;

import com.sbszc.eduspringbootdatajpa.entity.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@Slf4j

//@DataJpaTest replaces the explicit database(mysql) with an embedded in-memory database by default,
//so @AutoConfigureTestDatabase is added to not do that replacement, and execute test in mysql.
//With @DataJpaTest rollback is true by default,
//so @Rollback(false) set up false to being able to view the data after test execution.
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//with @Order(1) you can specify the execution order
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Autowired
    private CourseTeacherRepository courseTeacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void dataSetup(){
        var courseMaterial1 = new CourseMaterial(0L, "url1");
        var courseMaterial2 = new CourseMaterial(0L, "url2");

        var teacher1 = new CourseTeacher(0L, "teacherName1");
        var teacher2 = new CourseTeacher(0L, "teacherName2");

        var studentId1 = new StudentId(1L, "cod1");
        var studentGuardian1 = new StudentGuardian("guardianName1", "guardianEmail1", "guardianMobile1");
        var student1 = new Student(studentId1, "studentName1", "studentEmail1", studentGuardian1);
        var studentId2 = new StudentId(2L, "cod2");
        var studentGuardian2 = new StudentGuardian("guardianName2", "guardianEmail2", "guardianMobile2");
        var student2 = new Student(studentId2, "studentName2", "studentEmail2", studentGuardian2);

        var course1 = new Course(0L, "title1", 0, courseMaterial1, teacher1, List.of(student1));
        var course2 = new Course(0L, "title2", 0, courseMaterial2, teacher2, List.of(student1, student2));

        //when cascade is set up, just by saving an entity, child properties will be saved as well
        courseRepository.save(course1);
        courseRepository.save(course2);
    }

    @Test
    @Transactional//with @DataJpaTest @Transactional is on by default, check @DataJpaTest doc...
    @Rollback(true)//with @DataJpaTest @Rollback is true by default
    @Order(1)//requires @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public void crudTest() {
        //when cascade is set up, just by deleting an entity, child properties will be deleted as well
        courseRepository.deleteById(2L);
        //when updating, is not necessary to invoke save
        Course courseToUpdate = courseRepository.findById(1L).get();
        courseToUpdate.setTitle("updateTitle");
        courseToUpdate.setCredits(99);
        courseToUpdate.getCourseMaterial().setUrl("updatedUrl");
        courseToUpdate.getCourseTeacher().setName("updatedTeacherName");
        courseToUpdate.getStudents().get(0).setName("updatedStudentName");
        courseToUpdate.getStudents().get(0).setEmail("updatedStudentEmail");
        courseToUpdate.getStudents().get(0).getStudentGuardian().setName("updatedGuardianName");
        courseToUpdate.getStudents().get(0).getStudentGuardian().setMobile("updatedMobileName");
        courseToUpdate.getStudents().get(0).getStudentGuardian().setEmail("updatedEmailName");

        var foundCourses = courseRepository.findAll();
        log.info("foundCourses:{}", foundCourses);
        var foundCourseMaterials = courseMaterialRepository.findAll();
        log.info("foundCourseMaterials:{}", foundCourseMaterials);
        var foundTeachers = courseTeacherRepository.findAll();
        log.info("foundTeachers:{}", foundTeachers);
        var foundStudents = studentRepository.findAll();
        log.info("foundStudents:{}", foundStudents);
    }

    @Test
    @Transactional//with @DataJpaTest @Transactional is on by default, check @DataJpaTest doc...
    @Rollback(false)//with @DataJpaTest @Rollback is true by default
    @Order(2)//requires @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public void customQueriesTest() {
        Course foundByTitle = courseRepository.findByTitle("title1");
        log.info("foundByTitle:{}", foundByTitle);
        List<Course> foundByCourseTeacherName = courseRepository.findByCourseTeacherName("teacherName1");
        log.info("foundByCourseTeacherName:{}", foundByCourseTeacherName);
        Course queryByCourseMaterialUrl = courseRepository.queryByCourseMaterialUrl("url1");
        log.info("queryByCourseMaterialUrl:{}", queryByCourseMaterialUrl);
        Course nativeQueryByTitle = courseRepository.nativeQueryByTitle("title2");
        log.info("nativeQueryByTitle:{}", nativeQueryByTitle);
        int updateCourseCreditsByTitle = courseRepository.updateCourseCreditsByTitle("title1", 99);
        log.info("updateCourseCreditsByTitle:{}", updateCourseCreditsByTitle);
        int nativeUpdateCourseCreditsByTitle = courseRepository.nativeUpdateCourseCreditsByTitle("title2", 66);
        log.info("nativeUpdateCourseCreditsByTitle:{}", nativeUpdateCourseCreditsByTitle);
    }
}

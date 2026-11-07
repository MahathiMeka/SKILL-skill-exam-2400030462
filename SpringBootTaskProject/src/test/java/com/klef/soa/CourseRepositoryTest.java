package com.klef.soa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.soa.entity.Course;
import com.klef.soa.repository.CourseRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseRepositoryTest
{
    @Autowired
    private CourseRepository repository;

    @Test
    void testFindAllCourses()
    {
        Course c = new Course();
        c.setCourseName("Java Programming");
        c.setTrainer("Ravindra Kumar");
        c.setDuration("8 Months");
        c.setFee(15000);
        c.setMode("Online");

        repository.save(c);

        List<Course> courses = repository.findAll();

        assertNotNull(courses);
        assertFalse(courses.isEmpty());

        System.out.println("Total Courses: " + courses.size());
    }

    @Test
    void testFindCourseById()
    {
        Course c = new Course();
        c.setCourseName("Python Programming");
        c.setTrainer("Anita Sharma");
        c.setDuration("10 Months");
        c.setFee(25000);
        c.setMode("Offline");

        Course saved = repository.save(c);

        Optional<Course> course =
                repository.findById(saved.getcourseId());

        assertTrue(course.isPresent());
        assertEquals(saved.getcourseId(),
                     course.get().getcourseId());

        System.out.println("Course ID: " +
                course.get().getcourseId());
        System.out.println("Course Name: " +
                course.get().getCourseName());

        Optional<Course> invalidCourse =
                repository.findById(-1L);

        assertFalse(invalidCourse.isPresent());
    }

    @Test
    void testFindCourseByTrainer()
    {
        Course c = new Course();
        c.setCourseName("Java Programming");
        c.setTrainer("Ravindra Kumar");
        c.setDuration("8 Months");
        c.setFee(15000);
        c.setMode("Online");

        repository.save(c);

        List<Course> courses =
                repository.findByTrainer("Ravindra Kumar");

        assertNotNull(courses);
        assertFalse(courses.isEmpty());
        assertEquals("Ravindra Kumar", courses.get(0).getTrainer());

        System.out.println("Trainer Name: " +
                courses.get(0).getTrainer());
    }

    @Test
    void testTotalCourseCount()
    {
        long initialCount = repository.count();

        Course c = new Course();
        c.setCourseName("Spring Boot");
        c.setTrainer("Rahul Sharma");
        c.setDuration("4 Months");
        c.setFee(18000);
        c.setMode("Online");

        repository.save(c);

        long finalCount = repository.count();

        assertEquals(initialCount + 1, finalCount);

        System.out.println("Total Courses: " + finalCount);
    }

    @Test
    void testCourseDetailsAndMode()
    {
        Course c = new Course();
        c.setCourseName("Web Development");
        c.setTrainer("Suresh Kumar");
        c.setDuration("3 Months");
        c.setFee(16000);
        c.setMode("Offline");

        Course saved = repository.save(c);

        Optional<Course> course =
                repository.findById(saved.getcourseId());

        assertTrue(course.isPresent());

        Course foundCourse = course.get();

        assertNotNull(foundCourse.getCourseName());
        assertNotNull(foundCourse.getTrainer());
        assertNotNull(foundCourse.getDuration());
        assertNotNull(foundCourse.getMode());

        System.out.println("Course Name: " +
                foundCourse.getCourseName());
        System.out.println("Trainer: " +
                foundCourse.getTrainer());
        System.out.println("Duration: " +
                foundCourse.getDuration());
        System.out.println("Fee: " +
                foundCourse.getFee());
        System.out.println("Mode: " +
                foundCourse.getMode());

        List<Course> courses =
                repository.findByMode("Offline");

        assertNotNull(courses);
        assertFalse(courses.isEmpty());
        assertEquals("Offline", courses.get(0).getMode());

        System.out.println("Courses with Offline Mode: " +
                courses.size());
    }
}

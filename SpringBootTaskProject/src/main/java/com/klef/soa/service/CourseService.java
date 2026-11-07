package com.klef.soa.service;

import java.util.List;

import com.klef.soa.entity.Course;

public interface CourseService
{
	Course addCourse(Course c);
	Course updateCourse(Course c);
	List<Course> displayAllCourses();
	Course displayCourseById(Long courseId);
	String deleteCourseById(Long courseId);
	List<Course> displayCoursesByTrainer(String trainer);
	List<Course> displayCoursesByMode(String mode);
}
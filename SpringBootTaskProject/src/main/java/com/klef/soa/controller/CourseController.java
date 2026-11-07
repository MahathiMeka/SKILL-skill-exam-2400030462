package com.klef.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.soa.entity.Course;
import com.klef.soa.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController
{
	@Autowired
	private CourseService service;

	@GetMapping("/")
	public String demo()
	{
		return "Spring Boot Task Project";
	}

	@PostMapping("/add")
	public ResponseEntity<Course> addCourse(@RequestBody Course c)
	{
		Course course = service.addCourse(c);
		return ResponseEntity.status(201).body(course);
	}

	@GetMapping("/displayall")
	public ResponseEntity<List<Course>> displayAllCourses()
	{
		List<Course> courses = service.displayAllCourses();
		return ResponseEntity.status(200).body(courses);
	}

	@GetMapping("/display")
	public ResponseEntity<?> displayCourseById(@RequestParam Long courseId)
	{
		Course course = service.displayCourseById(courseId);

		if(course != null)
		{
			return ResponseEntity.status(200).body(course);
		}
		else
		{
			return ResponseEntity.status(404).body("Course ID Not Found");
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateCourse(@RequestBody Course course)
	{
		Course c = service.updateCourse(course);

		if(c != null)
		{
			return ResponseEntity.ok(c);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course ID Not Found");
		}
	}

	@DeleteMapping("/delete/{courseId}")
	public ResponseEntity<String> deleteCourseById(@PathVariable Long courseId)
	{
		String message = service.deleteCourseById(courseId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/displaybytrainer/{trainer}")
	public ResponseEntity<List<Course>> displayCoursesByTrainer(@PathVariable String trainer)
	{
		List<Course> courses = service.displayCoursesByTrainer(trainer);
		return ResponseEntity.status(200).body(courses);
	}

	@GetMapping("/displaybymode/{mode}")
	public ResponseEntity<List<Course>> displayCoursesByMode(@PathVariable String mode)
	{
		List<Course> courses = service.displayCoursesByMode(mode);
		return ResponseEntity.status(200).body(courses);
	}
}
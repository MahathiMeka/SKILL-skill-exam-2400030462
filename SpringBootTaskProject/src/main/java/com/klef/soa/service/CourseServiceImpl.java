package com.klef.soa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.entity.Course;
import com.klef.soa.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService
{
	@Autowired
	private CourseRepository repo;

	@Override
	public Course addCourse(Course c)
	{
		return repo.save(c);
	}

	@Override
	public Course updateCourse(Course c)
	{
		Optional<Course> optional = repo.findById(c.getcourseId());

		if(optional.isPresent())
		{
			Course course = optional.get();

			course.setCourseName(c.getCourseName());
			course.setTrainer(c.getTrainer());
			course.setDuration(c.getDuration());
			course.setFee(c.getFee());
			course.setMode(c.getMode());

			return repo.save(course);
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<Course> displayAllCourses()
	{
		return repo.findAll();
	}

	@Override
	public Course displayCourseById(Long courseId)
	{
		return repo.findById(courseId).orElse(null);
	}

	@Override
	public String deleteCourseById(Long courseId)
	{
		boolean flag = repo.existsById(courseId);

		if(flag)
		{
			repo.deleteById(courseId);
			return "Course Deleted Successfully";
		}
		else
		{
			return "Course ID Not Found";
		}
	}

	@Override
	public List<Course> displayCoursesByTrainer(String trainer)
	{
		return repo.findByTrainer(trainer);
	}

	@Override
	public List<Course> displayCoursesByMode(String mode)
	{
		return repo.findByMode(mode);
	}
}
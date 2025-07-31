package se.lexicon.dao.impl;

import se.lexicon.dao.CourseDao;
import se.lexicon.model.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    private List<Course> courses = new ArrayList<>();

    @Override
    public Course save(Course course) {

        // Check if course is null:
        if(course == null){
            throw new IllegalArgumentException("Course is not allowed to be null.");
        }

        // Check if course already exists:
        if (courses.contains(course)) {
            throw new IllegalArgumentException("Course already exists: " + course);
        }

        // Add the course to the list:
        courses.add(course);

        // Return the course:
        return course;
    }

    @Override
    public Course findById(int id) {
        return null;
    }

    @Override
    public List<Course> findByName(String name) {
        return List.of();
    }

    @Override
    public List<Course> findByDate(LocalDate date) {
        return List.of();
    }

    @Override
    public List<Course> findAll() {
        return List.of();
    }

    @Override
    public Boolean delete(Course course) {
        return null;
    }
}

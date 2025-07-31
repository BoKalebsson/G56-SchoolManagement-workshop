package se.lexicon.dao.impl;

import se.lexicon.dao.CourseDao;
import se.lexicon.model.Course;
import se.lexicon.model.Student;

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

        // Check if id is negative or zero:
        if(id <= 0){
            throw new IllegalArgumentException("Id is not allowed to be zero or negative.");
        }

        // Check if the id is in the list:
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        throw new IllegalArgumentException("No course found with id: " + id);
    }

    @Override
    public List<Course> findByName(String name) {

        // Check for null and empty:
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Course name cannot be null or empty.");
        }

        // Create a list to hold matching courses, and trim the name to search for:
        List<Course> result = new ArrayList<>();
        String searchName = name.trim();

        // Search for matching courses (case-insensitive):
        for (Course course : courses) {
            if (course.getCourseName().equalsIgnoreCase(searchName)) {
                result.add(course);
            }
        }
        // If no courses found, throw exception:
        if (result.isEmpty()) {
            throw new IllegalArgumentException("No course found with the following course name: " + name);
        }

        return result;
    }

    @Override
    public List<Course> findByDate(LocalDate date) {

        // Check if date is null:
        if(date == null){
            throw new IllegalArgumentException("Date is not allowed to be null.");
        }

        // Create a list to hold matching courses:
        List<Course> result = new ArrayList<>();

        for (Course course : courses) {
            if (course.getStartDate().equals(date)) {
                result.add(course);
            }
        }

        if (result.isEmpty()) {
            throw new IllegalArgumentException("No courses found with the following date: " + date);
        }

        return result;
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    @Override
    public Boolean delete(Course course) {
        // Check for null:
        if (course == null){
            throw new IllegalArgumentException("Course cannot be null.");
        }

        // Returns boolean-value:
        return courses.remove(course);
    }

    void clear() {
        courses.clear();
    }
}

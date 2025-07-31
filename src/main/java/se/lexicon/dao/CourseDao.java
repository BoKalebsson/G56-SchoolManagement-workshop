package se.lexicon.dao;

import se.lexicon.model.Course;

import java.time.LocalDate;
import java.util.List;

public interface CourseDao {

    /**
     * Saves a course to the data source.
     *
     * @param course the Course object to save
     * @return the saved Course object with any updates (e.g. assigned ID)
     */
    Course save(Course course);

    /**
     * Finds a course by its unique ID.
     *
     * @param id the ID of the course
     * @return the Course with the given ID, or null if not found
     */
    Course findById(int id);

    /**
     * Finds courses by matching their name.
     *
     * @param name the name to search for
     * @return a list of Courses whose names match the given name (case-sensitive)
     */
    List<Course> findByName(String name);

    /**
     * Finds courses that start on the given date.
     *
     * @param date the start date to search for
     * @return a list of Courses starting on the given date
     */
    List<Course> findByDate(LocalDate date);

    /**
     * Returns all courses from the data source.
     *
     * @return a list of all Courses
     */
    List<Course> findAll();

    /**
     * Deletes a course from the data source.
     *
     * @param course the Course to delete
     * @return true if deletion was successful, false otherwise
     */
    boolean delete(Course course);

}

package se.lexicon.dao;

import se.lexicon.model.Student;

import java.util.List;

public interface StudentDao {

    /**
     * Saves a student to the data source.
     * If the student does not exist, it is created.
     *
     * @param student the Student object to save
     * @return the saved Student object with any updates (e.g. assigned ID)
     */
    Student save(Student student);

    /**
     * Finds a student by their unique email.
     *
     * @param email the email to search for
     * @return the Student with the given email, or null if not found
     */
    Student findByEmail(String email);

    /**
     * Finds students by matching their name.
     *
     * @param name the name to search for
     * @return a list of Students whose names match the given name (case-sensitive)
     */
    List<Student> findByName(String name);

    /**
     * Finds a student by their unique ID.
     *
     * @param id the ID of the student
     * @return the Student with the given ID, or null if not found
     */
    Student findById(int id);

    /**
     * Returns all students from the data source.
     *
     * @return a list of all Students
     */
    List<Student> findAll();

    /**
     * Deletes a student from the data source.
     *
     * @param student the Student to delete
     * @return true if deletion was successful, false otherwise
     */
    Boolean delete(Student student);

}

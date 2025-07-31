package se.lexicon.dao.impl;

import se.lexicon.dao.StudentDao;
import se.lexicon.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private List<Student> students = new ArrayList<>();

    @Override
    public Student save(Student student) {

        // Check if student is null:
        if(student == null){
            throw new IllegalArgumentException("Student is not allowed to be null.");
        }

        // Check if student already exists by object equality (id):
        if (students.contains(student)) {
            throw new IllegalArgumentException("Student already exists: " + student);
        }

        // Optional: check if email already exists:
        for (Student s : students) {
            if (s.getEmail().equalsIgnoreCase(student.getEmail())) {
                throw new IllegalArgumentException("Student with email " + student.getEmail() + " already exists.");
            }
        }

        // Add the student to the list:
        students.add(student);

        // Return the student:
        return student;
    }

    @Override
    public Student findByEmail(String email) {

        // Basic check for the search. More robust checks are handled by the Person-class.
        if (email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }

        // Search through the list for matching email (case-insensitive)
        for (Student student : students) {
            if (student.getEmail().equalsIgnoreCase(email.trim())) {
                return student;
            }
        }
        throw new IllegalArgumentException("No one found with the following email: " + email);
    }

    @Override
    public List<Student> findByName(String name) {
        return List.of();
    }

    @Override
    public Student findById(int id) {

        // Check if id is negative or zero:
        if(id <= 0){
            throw new IllegalArgumentException("Id is not allowed to be zero or negative.");
        }

        // Check if the id is in the list:
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        throw new IllegalArgumentException("No person found with id: " + id);
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public Boolean delete(Student student) {
        return null;
    }
}

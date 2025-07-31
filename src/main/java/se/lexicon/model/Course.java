package se.lexicon.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Course {

    private static int courseIdCounter = 0;

    // Attributes:
    private final int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private List<Student> students = new ArrayList<>();;

    // Constructor without List of Students:
    public Course(String courseName, LocalDate startDate, int weekDuration) {
        this(courseName, startDate, weekDuration, new ArrayList<>());
    }

    // Constructor with List of Students:
    public Course(String courseName, LocalDate startDate, int weekDuration, List<Student> students) {
        this.id = ++courseIdCounter;
        this.setCourseName(courseName);
        this.setStartDate(startDate);
        this.setWeekDuration(weekDuration);
        this.setStudents(students);
    }

    // Getters:
    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    // Setters:
    public void setCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()){
            throw new IllegalArgumentException("Course name cannot be null or empty.");
        }
        this.courseName = courseName;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate == null || startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date cannot be null or in the past.");
        }
        this.startDate = startDate;
    }

    public void setWeekDuration(int weekDuration) {
        if (weekDuration <= 0) {
            throw new IllegalArgumentException("Duration cannot be zero or a negative number.");
        }

        this.weekDuration = weekDuration;
    }

    public void setStudents(List<Student> students) {
        if (students == null){
            throw new IllegalArgumentException("Students cannot be null.");
        }
        this.students = new ArrayList<>(students);
    }

    // Operations:
    public void register(Student student) {
        // Check if student is null:
        if (student == null) throw new IllegalArgumentException("Student cannot be null.");

        // Check if student already exists in students:
        if (!this.students.contains(student)) {
            this.students.add(student);
        }
    }

    public void unregister(Student student) {
        this.students.remove(student);
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        sb.append("-- Course Information --").append("\n");
        sb.append("Id: ").append(getId()).append("\n");
        sb.append("Course name: ").append(getCourseName()).append("\n");
        sb.append("Start Date: ").append(getStartDate().format(format)).append("\n");
        sb.append("Duration: ").append(getWeekDuration()).append("\n");
        sb.append("Number of students: ").append(students != null ? students.size() : 0).append("\n");
        sb.append("---------------------------").append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}

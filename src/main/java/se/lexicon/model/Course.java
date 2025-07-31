package se.lexicon.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {

    private static int courseIdCounter = 0;

    // Attributes:
    private int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private List<Student> students;

    // Constructor:
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
        if(startDate.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Startdate cannot be in the past.");
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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-- Course Information --").append("\n");
        sb.append("Id: ").append(getId()).append("\n");
        sb.append("Course name: ").append(getCourseName()).append("\n");
        sb.append("Start Date: ").append(getStartDate()).append("\n");
        sb.append("Duration: ").append(getWeekDuration()).append("\n");
        sb.append("Number of students: ").append(students != null ? students.size() : 0).append("\n");
        sb.append("---------------------------").append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id
                && weekDuration == course.weekDuration
                && Objects.equals(courseName, course.courseName)
                && Objects.equals(startDate, course.startDate)
                && Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, startDate, weekDuration, students);
    }
}

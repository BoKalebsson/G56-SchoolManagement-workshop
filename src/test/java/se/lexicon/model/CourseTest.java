package se.lexicon.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    @Test
    void createValidCourse() {
        // Arrange: Input values
        String name = "Java Programming";
        LocalDate start = LocalDate.now().plusDays(1);
        int duration = 10;

        // Act: Create a new Course object
        Course course = new Course(name, start, duration);

        // Assert: Check that fields are set correctly
        assertEquals(name, course.getCourseName());
        assertEquals(start, course.getStartDate());
        assertEquals(duration, course.getWeekDuration());
        assertNotNull(course.getStudents());
        assertTrue(course.getStudents().isEmpty());
        assertTrue(course.getId() > 0);
    }

    @Test
    void createCourseWithStudents() {
        // Arrange: Create sample students
        Student s1 = new Student("Alice", "alice@test.com", "Addr1");
        Student s2 = new Student("Bob", "bob@test.com", "Addr2");
        LocalDate start = LocalDate.now().plusDays(2);

        // Act: Create course with a list of students
        Course course = new Course("Test Course", start, 5, Arrays.asList(s1, s2));

        // Assert: Check students list
        assertEquals(2, course.getStudents().size());
        assertTrue(course.getStudents().contains(s1));
        assertTrue(course.getStudents().contains(s2));
    }

    @Test
    void createNullName() {
        // Arrange: Course name is null
        String name = null;
        LocalDate start = LocalDate.now().plusDays(1);

        // Act & Assert: Constructor should throw IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Course(name, start, 5);
        });
        assertEquals("Course name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void createEmptyName() {
        // Arrange: Course name is empty
        String name = " ";
        LocalDate start = LocalDate.now().plusDays(1);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Course(name, start, 5);
        });
        assertEquals("Course name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void createNullStartDate() {
        // Arrange: Start date is null
        String name = "Course 1";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Course(name, null, 5);
        });
        assertEquals("Start date cannot be null or in the past.", exception.getMessage());
    }

    @Test
    void createStartDateInPast() {
        // Arrange: Start date in the past
        String name = "Course 1";
        LocalDate past = LocalDate.now().minusDays(1);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Course(name, past, 5);
        });
        assertEquals("Start date cannot be null or in the past.", exception.getMessage());
    }

    @Test
    void createZeroOrNegativeDuration() {
        // Arrange: Invalid durations
        String name = "Course 1";
        LocalDate start = LocalDate.now().plusDays(1);

        // Act & Assert: zero duration
        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> {
            new Course(name, start, 0);
        });
        assertEquals("Duration cannot be zero or a negative number.", ex1.getMessage());

        // Act & Assert: negative duration
        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> {
            new Course(name, start, -1);
        });
        assertEquals("Duration cannot be zero or a negative number.", ex2.getMessage());
    }

    @Test
    void createNullStudentsList() {
        // Arrange
        String name = "Course 1";
        LocalDate start = LocalDate.now().plusDays(1);

        // Act & Assert: Passing null for students should throw exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Course(name, start, 5, null);
        });
        assertEquals("Students cannot be null.", exception.getMessage());
    }

    @Test
    void registerStudent() {
        // Arrange
        Course course = new Course("Test", LocalDate.now().plusDays(1), 5);
        Student student = new Student("Alice", "alice@test.com", "Addr1");

        // Act: Register student
        course.register(student);

        // Assert: Student list should contain the new student
        assertTrue(course.getStudents().contains(student));
    }

    @Test
    void registerNullStudentThrows() {
        // Arrange
        Course course = new Course("Test", LocalDate.now().plusDays(1), 5);

        // Act & Assert: Registering null should throw exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            course.register(null);
        });
        assertEquals("Student cannot be null.", exception.getMessage());
    }

    @Test
    void registerDuplicateStudentIgnored() {
        // Arrange
        Course course = new Course("Test", LocalDate.now().plusDays(1), 5);
        Student student = new Student("Alice", "alice@test.com", "Addr1");

        // Act: Register student twice
        course.register(student);
        course.register(student);

        // Assert: Student list should have only one instance
        assertEquals(1, course.getStudents().size());
    }

    @Test
    void unregisterStudent() {
        // Arrange
        Student student = new Student("Alice", "alice@test.com", "Addr1");
        Course course = new Course("Test", LocalDate.now().plusDays(1), 5, Collections.singletonList(student));

        // Act: Unregister the student
        course.unregister(student);

        // Assert: Student list should be empty
        assertFalse(course.getStudents().contains(student));
    }

    @Test
    void unregisterNullStudentThrows() {
        // Arrange
        Student student = new Student("Alice", "alice@test.com", "Addr1");
        Course course = new Course("Test", LocalDate.now().plusDays(1), 5, Collections.singletonList(student));

        // Act & Assert: unregister(null) should throw IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            course.unregister(null);
        });
        assertEquals("Student cannot be null.", exception.getMessage());
    }

    @Test
    void toStringContainsInfo() {
        // Arrange
        Course course = new Course("Java 101", LocalDate.now().plusDays(1), 6);

        // Act
        String result = course.toString();

        // Assert: String contains course info
        assertTrue(result.contains("Java 101"));
        assertTrue(result.contains(String.valueOf(course.getWeekDuration())));
        assertTrue(result.contains(String.valueOf(course.getId())));
    }

    @Test
    void equalsSameIdTrue() {
        // Arrange
        Course c1 = new Course("Course1", LocalDate.now().plusDays(1), 5);
        Course c2 = new Course("Course2", LocalDate.now().plusDays(1), 10);

        // Force same id via reflection or manual hack if needed, otherwise just test different IDs not equal
        // Here, IDs auto-increment so c1 != c2, test that equals returns false
        assertFalse(c1.equals(c2));
        assertTrue(c1.equals(c1));
    }

    @Test
    void hashCodeConsistent() {
        // Arrange
        Course course = new Course("Course1", LocalDate.now().plusDays(1), 5);

        // Act
        int h1 = course.hashCode();
        int h2 = course.hashCode();

        // Assert
        assertEquals(h1, h2);
    }
}

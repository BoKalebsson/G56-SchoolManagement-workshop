package se.lexicon.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Course;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseDaoImplTest {

    private CourseDaoImpl dao;

    @BeforeEach
    void setup() {
        dao = new CourseDaoImpl();
    }

    // Group: save()

    @Test
    void save_success() {
        // Arrange: create a course
        Course math = new Course("Math", LocalDate.of(2029, 1, 1), 10);

        // Act: save the course
        Course saved = dao.save(math);

        // Assert: returned object equals input and is stored
        assertEquals(math, saved);
        assertTrue(dao.findAll().contains(math));
    }

    @Test
    void save_null_throws() {
        // Act & Assert: saving null throws exception
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dao.save(null));
        assertEquals("Course is not allowed to be null.", ex.getMessage());
    }

    @Test
    void save_duplicate_throws() {
        // Arrange: save one course
        Course java = new Course("Java", LocalDate.now(), 5);
        dao.save(java);

        // Act & Assert: saving same course again throws exception
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dao.save(java));
        assertTrue(ex.getMessage().contains("Course already exists"));
    }

    // Group: findById()

    @Test
    void findById_found() {
        // Arrange
        Course c = new Course("C++", LocalDate.now(), 6);
        dao.save(c);

        // Act
        Course found = dao.findById(c.getId());

        // Assert
        assertEquals(c, found);
    }

    @Test
    void findById_invalid_throws() {
        // Act & Assert: zero or negative id throws exception
        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> dao.findById(0));
        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> dao.findById(-1));
        assertTrue(ex1.getMessage().contains("not allowed"));
        assertTrue(ex2.getMessage().contains("not allowed"));
    }

    @Test
    void findById_notFound_returnsNull() {
        // Act: attempt to find non-existing course
        Course result = dao.findById(999);

        // Assert: result should be null
        assertNull(result);
    }

    // Group: findByName()

    @Test
    void findByName_found() {
        // Arrange
        Course math1 = new Course("Math", LocalDate.of(2029, 1, 1), 10);
        Course math2 = new Course("Math", LocalDate.of(2029, 2, 1), 8);
        Course physics = new Course("Physics", LocalDate.of(2029, 3, 1), 6);
        dao.save(math1);
        dao.save(math2);
        dao.save(physics);

        // Act
        List<Course> found = dao.findByName("Math");

        // Assert: two courses named Math found
        assertEquals(2, found.size());
        assertTrue(found.contains(math1));
        assertTrue(found.contains(math2));
    }

    @Test
    void findByName_nullOrEmpty_throws() {
        // Act & Assert: null name throws
        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> dao.findByName(null));
        assertTrue(ex1.getMessage().contains("cannot be null or empty"));

        // Act & Assert: empty name throws
        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> dao.findByName(" "));
        assertTrue(ex2.getMessage().contains("cannot be null or empty"));
    }

    // Note: findByName returns empty list if not found, no exception.

    @Test
    void findByName_notFound_returnsEmpty() {
        // Act
        List<Course> found = dao.findByName("Nonexistent");

        // Assert
        assertNotNull(found);
        assertTrue(found.isEmpty());
    }

    // Group: findByDate()

    @Test
    void findByDate_found() {
        // Arrange
        LocalDate date1 = LocalDate.of(2029, 1, 1);
        LocalDate date2 = LocalDate.of(2029, 2, 1);
        Course course1 = new Course("Course1", date1, 5);
        Course course2 = new Course("Course2", date1, 3);
        Course course3 = new Course("Course3", date2, 4);
        dao.save(course1);
        dao.save(course2);
        dao.save(course3);

        // Act
        List<Course> found = dao.findByDate(date1);

        // Assert
        assertEquals(2, found.size());
        assertTrue(found.contains(course1));
        assertTrue(found.contains(course2));
    }

    @Test
    void findByDate_null_throws() {
        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dao.findByDate(null));
        assertTrue(ex.getMessage().contains("not allowed to be null"));
    }

    @Test
    void findByDate_notFound_returnsEmpty() {
        // Arrange
        LocalDate date = LocalDate.of(2050, 1, 1);

        // Act
        List<Course> found = dao.findByDate(date);

        // Assert
        assertNotNull(found);
        assertTrue(found.isEmpty());
    }

    // Group: findAll()

    @Test
    void findAll_returnsAll() {
        // Arrange
        Course c1 = new Course("C1", LocalDate.now(), 5);
        Course c2 = new Course("C2", LocalDate.now(), 6);
        dao.save(c1);
        dao.save(c2);

        // Act
        List<Course> all = dao.findAll();

        // Assert
        assertEquals(2, all.size());
        assertTrue(all.contains(c1));
        assertTrue(all.contains(c2));
    }

    // Group: delete()

    @Test
    void delete_existing() {
        // Arrange
        Course c = new Course("DeleteMe", LocalDate.now(), 4);
        dao.save(c);

        // Act
        boolean removed = dao.delete(c);

        // Assert
        assertTrue(removed);
        assertFalse(dao.findAll().contains(c));
    }

    @Test
    void delete_null_throws() {
        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dao.delete(null));
        assertTrue(ex.getMessage().contains("cannot be null"));
    }

    @Test
    void delete_nonExisting_returnsFalse() {
        // Arrange
        Course c = new Course("NotSaved", LocalDate.now(), 3);

        // Act
        boolean removed = dao.delete(c);

        // Assert
        assertFalse(removed);
    }

    // Group: clear()

    @Test
    void clear_emptiesList() {
        // Arrange
        Course c1 = new Course("C1", LocalDate.now(), 2);
        Course c2 = new Course("C2", LocalDate.now(), 3);
        dao.save(c1);
        dao.save(c2);

        // Act
        dao.clear();

        // Assert
        assertTrue(dao.findAll().isEmpty());
    }
}
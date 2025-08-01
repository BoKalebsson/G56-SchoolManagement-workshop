package se.lexicon.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentDaoImplTest {

    private StudentDaoImpl dao;

    @BeforeEach
    void setup() {
        dao = new StudentDaoImpl();
    }

    @Test
    void save_student() {
        // Arrange: create a student
        Student erik = new Student("Erik", "erik@test.com", "Address1");

        // Act: save student
        Student saved = dao.save(erik);

        // Assert: returned object equals input, and student is stored
        assertEquals(erik, saved);
        assertTrue(dao.findAll().contains(erik));
    }

    @Test
    void save_null() {
        // Act & Assert: saving null should throw exception
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dao.save(null));
        assertEquals("Student is not allowed to be null.", ex.getMessage());
    }

    @Test
    void save_duplicate() {
        // Arrange: save one student first
        Student anna = new Student("Anna", "anna@test.com", "Addr2");
        dao.save(anna);

        // Act & Assert: saving same student again throws exception
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dao.save(anna));
        assertTrue(ex.getMessage().contains("Student already exists"));
    }

    @Test
    void save_duplicateEmail() {
        // Arrange: save one student first
        Student bob = new Student("Bob", "bob@test.com", "Addr3");
        dao.save(bob);

        // Act & Assert: saving different student with same email throws exception
        Student duplicateEmail = new Student("Bobby", "bob@test.com", "Addr4");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dao.save(duplicateEmail));
        assertTrue(ex.getMessage().contains("already exists"));
    }

    @Test
    void find_byEmail_found() {
        // Arrange
        Student erik = new Student("Erik", "erik@test.com", "Address1");
        dao.save(erik);

        // Act
        Student found = dao.findByEmail("erik@test.com");

        // Assert
        assertEquals(erik, found);
    }

    @Test
    void find_byEmail_notFound() {
        // Act
        Student result = dao.findByEmail("notfound@example.com");

        // Assert: result should be null when no student found
        assertNull(result);
    }

    @Test
    void find_byEmail_nullOrEmpty() {
        // Act & Assert null
        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> dao.findByEmail(null));
        assertTrue(ex1.getMessage().contains("cannot be null or empty"));

        // Act & Assert empty
        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> dao.findByEmail(" "));
        assertTrue(ex2.getMessage().contains("cannot be null or empty"));
    }

    @Test
    void find_byName_found() {
        // Arrange
        Student anna1 = new Student("Anna", "anna1@test.com", "Addr1");
        Student anna2 = new Student("Anna", "anna2@test.com", "Addr2");
        Student bob = new Student("Bob", "bob@test.com", "Addr3");
        dao.save(anna1);
        dao.save(anna2);
        dao.save(bob);

        // Act
        List<Student> found = dao.findByName("Anna");

        // Assert: two results with name Anna
        assertEquals(2, found.size());
        assertTrue(found.contains(anna1));
        assertTrue(found.contains(anna2));
    }

    @Test
    void find_byName_notFound() {
        // Act
        List<Student> result = dao.findByName("NonExistingName");

        // Assert: result should not be null (preferably empty list)
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void find_byName_nullOrEmpty() {
        // Act & Assert null
        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> dao.findByName(null));
        assertTrue(ex1.getMessage().contains("cannot be null or empty"));

        // Act & Assert empty
        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> dao.findByName(" "));
        assertTrue(ex2.getMessage().contains("cannot be null or empty"));
    }

    @Test
    void find_byId_found() {
        // Arrange
        Student erik = new Student("Erik", "erik@test.com", "Addr1");
        dao.save(erik);

        // Act
        Student found = dao.findById(erik.getId());

        // Assert
        assertEquals(erik, found);
    }

    @Test
    void find_byId_notFound() {
        // Act
        Student result = dao.findById(9999);

        // Assert
        assertNull(result);
    }

    @Test
    void find_byId_invalid() {
        // Act & Assert zero and negative ids
        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> dao.findById(0));
        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> dao.findById(-1));
        assertTrue(ex1.getMessage().contains("not allowed"));
        assertTrue(ex2.getMessage().contains("not allowed"));
    }

    @Test
    void findAll_returnsAll() {
        // Arrange
        Student erik = new Student("Erik", "erik@test.com", "Addr1");
        Student anna = new Student("Anna", "anna@test.com", "Addr2");
        dao.save(erik);
        dao.save(anna);

        // Act
        List<Student> all = dao.findAll();

        // Assert
        assertEquals(2, all.size());
        assertTrue(all.contains(erik));
        assertTrue(all.contains(anna));
    }

    @Test
    void delete_existing() {
        // Arrange
        Student erik = new Student("Erik", "erik@test.com", "Addr1");
        dao.save(erik);

        // Act
        boolean removed = dao.delete(erik);

        // Assert
        assertTrue(removed);
        assertFalse(dao.findAll().contains(erik));
    }

    @Test
    void delete_null() {
        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dao.delete(null));
        assertTrue(ex.getMessage().contains("cannot be null"));
    }

    @Test
    void delete_nonExisting() {
        // Arrange
        Student anna = new Student("Anna", "anna@test.com", "Addr1");

        // Act
        boolean removed = dao.delete(anna);

        // Assert: removal should be false since not stored
        assertFalse(removed);
    }

    @Test
    void clear_emptiesList() {
        // Arrange
        Student erik = new Student("Erik", "erik@test.com", "Addr1");
        Student anna = new Student("Anna", "anna@test.com", "Addr2");
        dao.save(erik);
        dao.save(anna);

        // Act
        dao.clear();

        // Assert
        assertTrue(dao.findAll().isEmpty());
    }
}
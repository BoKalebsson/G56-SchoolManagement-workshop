package se.lexicon.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void createValidStudent() {
        // Arrange: Input values
        String name = "Erik Andersson";
        String email = "erik@test.nu";
        String address = "Storgatan 5 12345 Växjö";

        // Act: Create a new Student object
        Student student = new Student(name, email, address);

        // Assert: Check that fields are set correctly
        assertEquals(name, student.getName());
        assertEquals(email, student.getEmail());
        assertEquals(address, student.getAddress());
        assertTrue(student.getId() > 0); // ID should be assigned
    }

    @Test
    void createNullName() {
        // Arrange: Name is null
        String name = null;
        String email = "erik@test.nu";
        String address = "Storgatan 5 12345 Växjö";

        // Act & Assert: Creating Student should throw exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Student(name, email, address);
        });

        // Assert: Exception message check
        assertEquals("Name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void createEmptyName() {
        // Arrange: Name is empty
        String name = "  ";
        String email = "erik@test.nu";
        String address = "Storgatan 5 12345 Växjö";

        // Act & Assert: Creating Student should throw exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Student(name, email, address);
        });

        // Assert: Exception message check
        assertEquals("Name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void createNullEmail() {
        // Arrange: Email is null
        String name = "Erik Andersson";
        String email = null;
        String address = "Storgatan 5 12345 Växjö";

        // Act & Assert: Creating Student should throw exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Student(name, email, address);
        });

        // Assert: Exception message check
        assertEquals("Email cannot be null or empty.", exception.getMessage());
    }

    @Test
    void createEmptyEmail() {
        // Arrange: Email is empty
        String name = "Erik Andersson";
        String email = "  ";
        String address = "Storgatan 5 12345 Växjö";

        // Act & Assert: Creating Student should throw exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Student(name, email, address);
        });

        // Assert: Exception message check
        assertEquals("Email cannot be null or empty.", exception.getMessage());
    }

    @Test
    void createInvalidEmail() {
        // Arrange: Email missing '@'
        String name = "Erik Andersson";
        String email = "erik.test.nu";
        String address = "Storgatan 5 12345 Växjö";

        // Act & Assert: Creating Student should throw exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Student(name, email, address);
        });

        // Assert: Exception message check
        assertEquals("Email must contain a '@'.", exception.getMessage());
    }

    @Test
    void createNullAddress() {
        // Arrange: Address is null
        String name = "Erik Andersson";
        String email = "erik@test.nu";
        String address = null;

        // Act & Assert: Creating Student should throw exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Student(name, email, address);
        });

        // Assert: Exception message check
        assertEquals("Address cannot be null or empty.", exception.getMessage());
    }

    @Test
    void createEmptyAddress() {
        // Arrange: Address is empty
        String name = "Erik Andersson";
        String email = "erik@test.nu";
        String address = "  ";

        // Act & Assert: Creating Student should throw exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Student(name, email, address);
        });

        // Assert: Exception message check
        assertEquals("Address cannot be null or empty.", exception.getMessage());
    }

    @Test
    void setNameValid() {
        // Arrange: Create student with initial name
        Student student = new Student("Anna", "anna@test.com", "Address 1");

        // Act: Change the name
        student.setName("Anna New");

        // Assert: Name should be updated
        assertEquals("Anna New", student.getName());
    }

    @Test
    void setEmailValid() {
        // Arrange: Create student with initial email
        Student student = new Student("Anna", "anna@test.com", "Address 1");

        // Act: Change the email
        student.setEmail("new.email@test.com");

        // Assert: Email should be updated
        assertEquals("new.email@test.com", student.getEmail());
    }

    @Test
    void setAddressValid() {
        // Arrange: Create student with initial address
        Student student = new Student("Anna", "anna@test.com", "Address 1");

        // Act: Change the address
        student.setAddress("New Address 2");

        // Assert: Address should be updated
        assertEquals("New Address 2", student.getAddress());
    }

    @Test
    void equalsSameObjectTrue() {
        // Arrange: Create a student
        Student student = new Student("Anna", "anna@test.com", "Address 1");

        // Act & Assert: Object should equal itself
        assertTrue(student.equals(student));
    }

    @Test
    void equalsDifferentObjectsSameIdTrue() {
        // Arrange: Create two different students
        Student student1 = new Student("Anna", "anna@test.com", "Address 1");
        Student student2 = new Student("Bob", "bob@test.com", "Address 2");

        // Act & Assert: Different students with different IDs should not be equal
        assertFalse(student1.equals(student2));
    }

    @Test
    void equalsNullFalse() {
        // Arrange: Create a student
        Student student = new Student("Anna", "anna@test.com", "Address 1");

        // Act & Assert: Student should not equal null
        assertFalse(student.equals(null));
    }

    @Test
    void equalsDifferentClassFalse() {
        // Arrange: Create a student and a String object
        Student student = new Student("Anna", "anna@test.com", "Address 1");
        String notAStudent = "I am not a student";

        // Act & Assert: Student should not equal object of different class
        assertFalse(student.equals(notAStudent));
    }

    @Test
    void hashCodeConsistent() {
        // Arrange: Create a student
        Student student = new Student("Anna", "anna@test.com", "Address 1");

        // Act: Call hashCode twice
        int hash1 = student.hashCode();
        int hash2 = student.hashCode();

        // Assert: hashCode should be consistent
        assertEquals(hash1, hash2);
    }

    @Test
    void toStringContainsNameAndEmail() {
        // Arrange: Create a student
        Student student = new Student("Anna", "anna@test.com", "Address 1");

        // Act: Get the string representation
        String result = student.toString();

        // Assert: The string should contain key information
        assertTrue(result.contains("Anna"));
        assertTrue(result.contains("anna@test.com"));
        assertTrue(result.contains("Address 1"));
    }

}

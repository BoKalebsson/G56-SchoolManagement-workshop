package se.lexicon.model;

import java.util.Objects;

public class Student {

    private static int personIdCounter = 0;

    // Attributes:
    private final int id;
    private String name;
    private String email;
    private String address;

    // Constructor:
    public Student(String name, String email, String address) {
        this.id = ++personIdCounter;
        this.setName(name);
        this.setEmail(email);
        this.setAddress(address);
    }

    // Getters:
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    // Setters:
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain a '@'.");
        }
        this.email = email;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()){
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        this.address = address;
    }

    // Operations:
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-- Student Information --").append("\n");
        sb.append("Id: ").append(getId()).append("\n");
        sb.append("Name: ").append(getName()).append("\n");
        sb.append("Email: ").append(getEmail()).append("\n");
        sb.append("Address: ").append(getAddress()).append("\n");
        sb.append("---------------------------").append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

}


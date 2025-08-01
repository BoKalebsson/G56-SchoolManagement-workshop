package se.lexicon;

import se.lexicon.dao.CourseDao;
import se.lexicon.dao.StudentDao;
import se.lexicon.dao.impl.CourseDaoImpl;
import se.lexicon.dao.impl.StudentDaoImpl;
import se.lexicon.model.Course;
import se.lexicon.model.Student;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // Initializing DAO-collections:
        StudentDao studentDao = new StudentDaoImpl();
        CourseDao courseDao = new CourseDaoImpl();

        // Initializing students:
        Student erik = new Student("Erik Andersson", "erik@student.nu", "Storgatan 37");
        Student anna = new Student("Anna Svensson", "anna@student.nu", "Luhrpasset 31");

        // Initializing courses:
        Course python = new Course("Python for snakes!", LocalDate.now().plusYears(1), 52);
        Course economics = new Course("Ekonomitips från deltagare i Lyxfällan!", LocalDate.now().plusYears(1), 23);

        // Adding the students to DAO collection:
        studentDao.save(erik);
        studentDao.save(anna);

        // Adding the courses to DAO collection:
        courseDao.save(python);
        courseDao.save(economics);

        // Printing out the students:
        for (Student student : studentDao.findAll()) {
            System.out.println(student);
        }

        // Adding students to courses:
        python.register(erik);
        economics.register(erik);
        economics.register(anna);

        // Printing out the courses:
        for (Course course : courseDao.findAll()) {
            System.out.println(course);
        }

        // Finding a student by name:
        System.out.println("Search result for students by name: \n" + studentDao.findByName("Erik Andersson"));

        // Finding a student by id:
        System.out.println("Search result for students by id: \n" + studentDao.findById(2));

        // Finding a student by email:
        System.out.println("Search result for students by email: \n" + studentDao.findByEmail("erik@student.nu"));

        // Finding a course by id:
        System.out.println("Search result for course by id: \n" + courseDao.findById(1));

        // Finding a course by name:
        System.out.println("Search result for course by containing search-word: \n" + courseDao.findByName("python"));

        // Finding a course by date:
        System.out.println("Search result for course by with a certain start date: \n" + courseDao.findByDate(LocalDate.now().plusYears(1)));

        System.out.println();

        // Deleting a student:
        System.out.println("Result of deleting a student: " +studentDao.delete(anna));

        // Deleting a course:
        System.out.println("Result of deleting a course: " +courseDao.delete(python));

        System.out.println();

        // Printing out the students again:
        System.out.println("Printing out the students again: \n");
        for (Student student : studentDao.findAll()) {
            System.out.println(student);
        }

        // Printing out the courses again:
        System.out.println("Printing out the courses again: \n");
        for (Course course : courseDao.findAll()) {
            System.out.println(course);
        }












    }
}
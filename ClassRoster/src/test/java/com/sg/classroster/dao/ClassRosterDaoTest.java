package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;

import java.util.List;

import static org.junit.Assert.*;

public class ClassRosterDaoTest {

    private ClassRosterDao dao = new ClassRosterDaoFileImpl();

    @org.junit.Before
    public void setUp() throws Exception {
        List<Student> studentList = dao.getAllStudents();
        for (Student student : studentList) {
            dao.removeStudent(student.getStudentID());
        }
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testAddGetStudent() throws Exception {

        Student student = new Student("0001");
        student.setFirstName("Joe");
        student.setLastName("Smith");
        student.setCohort("Java May 2000");

        dao.addStudent(student.getStudentID(), student);

        Student fromDao = dao.getStudent(student.getStudentID());

        assertEquals(student, fromDao);
    }

    @org.junit.Test
    public void testGetAllStudents() throws Exception {

        Student student1 = new Student("0001");
        student1.setFirstName("Joe");
        student1.setLastName("Smith");
        student1.setCohort("Java May 2000");
        dao.addStudent(student1.getStudentID(), student1);

        Student student2 = new Student("0002");
        student2.setFirstName("John");
        student2.setLastName("Doe");
        student2.setCohort(".NET May 2000");
        dao.addStudent(student2.getStudentID(), student2);

        assertEquals(2, dao.getAllStudents().size());


    }

    @org.junit.Test
    public void testRemoveStudent() throws Exception {

        Student student1 = new Student("0001");
        student1.setFirstName("Joe");
        student1.setLastName("Smith");
        student1.setCohort("Java May 2000");
        dao.addStudent(student1.getStudentID(), student1);

        Student student2 = new Student("0002");
        student2.setFirstName("John");
        student2.setLastName("Doe");
        student2.setCohort(".NET May 2000");
        dao.addStudent(student2.getStudentID(), student2);

        dao.removeStudent(student1.getStudentID());

        assertEquals(1, dao.getAllStudents().size());
        assertNull(dao.getStudent(student1.getStudentID()));

        dao.removeStudent(student2.getStudentID());

        assertEquals(0, dao.getAllStudents().size());
        assertNull(dao.getStudent(student2.getStudentID()));

    }
}
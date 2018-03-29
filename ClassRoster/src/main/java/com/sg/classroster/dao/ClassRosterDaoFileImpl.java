package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;

import java.io.*;
import java.util.*;

public class ClassRosterDaoFileImpl implements ClassRosterDao {

    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";

    private Map<String, Student> students = new HashMap<>();

    @Override
    public Student addStudent(String studentId, Student student)
    throws ClassRosterDaoException {
        Student newStudent = students.put(studentId, student);
        writeRoster();
        return newStudent;
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterDaoException {
        loadRoster();
        return new ArrayList<Student>(students.values());
    }

    public Student getStudent(String studentId) throws ClassRosterDaoException {
        loadRoster();
        return students.get(studentId);
    }

    public Student removeStudent(String studentId) throws ClassRosterDaoException {
        Student removedStudent = students.remove(studentId);
        writeRoster();
        return removedStudent;
    }

    private void loadRoster() throws ClassRosterDaoException {
        Scanner scanner;

        try {
            //Create scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));


            } catch (FileNotFoundException e) {
            throw new ClassRosterDaoException(
                    "- - Could not load roster data into memory.", e);

        }
        //currentLine holds the most recent line read from the file
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Student currentStudent = new Student(currentTokens[0]);

            currentStudent.setFirstName(currentTokens[1]);
            currentStudent.setLastName(currentTokens[2]);
            currentStudent.setCohort(currentTokens[3]);
        }

        scanner.close();
    }

    private void writeRoster() throws ClassRosterDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new ClassRosterDaoException(
                    "Could not save student data.", e);

        }

        List<Student> studentList = this.getAllStudents();
        for (Student currentStudent : studentList) {

            out.println(currentStudent.getStudentID() + DELIMITER
            + currentStudent.getFirstName() + DELIMITER
            + currentStudent.getLastName() + DELIMITER
            + currentStudent.getCohort());

            out.flush();
        }
        out.close();
    }

}

package com.sg.classroster.dto;

import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    private String studentID;
    private String cohort; //Java or .Net + cohort month/year

    public Student(String studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(studentID, student.studentID) &&
                Objects.equals(cohort, student.cohort);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, studentID, cohort);
    }

    @Override
    public String toString() {
        return "ID: " + studentID + " |Name: " + firstName + " "
                + lastName + " |Cohort: " + cohort;
    }
}

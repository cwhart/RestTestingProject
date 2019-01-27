package com.sg.hotelreservations.viewmodels.reservation;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class InputPersonDetailsCommandModel {


//    @Length(
//            max = 50,
//            message = "Name must be no more than 50 characters in length."
//    )
@NotEmpty(
        message = "You must supply a value for Name."
)
    String firstName;
    @NotEmpty(
            message = "You must supply a value for Name."
    )
    String lastName;
    String dateOfBirth;
    String phone;
    String email;

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

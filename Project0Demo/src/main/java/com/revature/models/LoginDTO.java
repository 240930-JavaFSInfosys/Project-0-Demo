package com.revature.models;

/*What is a DTO? Data Transfer Object. It's meant to model some data that doesn't pertain to a DB table
For instance, maybe we have login functionality that only takes username/password
We want a user to be able to log in with ONLY their username/password instead of an entire Employee object
NOTE: we never store DTOs in the DB - they're solely for DATA TRANSFER */
public class LoginDTO {

    //We just need this object to store employee_id and first_name to help with login
    private int employee_id;
    private String first_name;

    //boilerplate code--------------------------------

    //no args, all args, getters/setters, toString
    public LoginDTO() {
    }

    public LoginDTO(int employee_id, String first_name) {
        this.employee_id = employee_id;
        this.first_name = first_name;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "employee_id=" + employee_id +
                ", first_name='" + first_name + '\'' +
                '}';
    }
}

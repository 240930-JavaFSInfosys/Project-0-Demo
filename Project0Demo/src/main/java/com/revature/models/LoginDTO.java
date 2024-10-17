package com.revature.models;

/*What is a DTO? Data Transfer Object. It's meant to model some data that doesn't pertain to a DB table
For instance, maybe we have login functionality that only takes username/password
We want a user to be able to log in with ONLY their username/password instead of an entire Employee object
NOTE: we never store DTOs in the DB - they're solely for DATA TRANSFER */
public class LoginDTO {
}

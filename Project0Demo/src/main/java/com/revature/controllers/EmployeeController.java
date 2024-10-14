package com.revature.controllers;

import com.revature.DAOs.EmployeeDAO;
import com.revature.models.Employee;
import io.javalin.http.Handler;

import java.util.ArrayList;

/* The Controller Layer is where HTTP Requests get sent after Javalin directs them from main()
* It's in this layer that JSON comes in and gets translated to Java and vice versa
* We'll be taking in HTTP Requests from the client, and sending back HTTP Reponses
* The Controller's job is to process HTTP Requests and respond to them appropriately*/
public class EmployeeController {

    //We need an EmployeeDAO to use the JDBC methods (get all employees, insert employee)
    EmployeeDAO eDAO = new EmployeeDAO();

    //This Handler will handle GET requests to /employees
    public Handler getEmployeesHandler = ctx -> {

        //Populate an ArrayList of Employee objects from the DAO!
        ArrayList<Employee> employees = eDAO.getAllEmployees();

        //PROBLEM: We can't send plain Java in an HTTP response - we want to use JSON

        //SOLUTION: We can use the ctx.json() method to convert this ArrayList to JSON
        //Note: This also returns the object to the client once the code block completes. Convenient!
        ctx.json(employees);

        //We can also set the status code ctx.status()
        ctx.status(200); //OK

    };


}

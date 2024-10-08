package com.revature.DAOs;

import com.revature.models.Employee;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

//DAO == "Data Access Object", it will have methods that access our DB
public class EmployeeDAO implements EmployeeDAOInterface{

    @Override
    public Employee insertEmployee(Employee emp) {

        //We will always try to connect to the DB first, before we can run any SQL
        try(Connection conn = ConnectionUtil.getConnection()){

            //create our SQL statement
            String sql = "INSERT INTO employees (first_name, last_name, role_id_fk) VALUES (?, ?, ?)";

            //use PreparedStatement to fill in the values of our variables
            PreparedStatement ps = conn.prepareStatement(sql);

            //use the .set() methods from PreparedStatement to fill in the values
            ps.setString(1, emp.getFirst_name());
            ps.setString(2, emp.getLast_name());
            ps.setInt(3, emp.getRole_id_fk());

            //Now that we've filled in the values, we can send the command to the DB
            ps.executeUpdate();
            //NOTE: executeUpdate() is used with INSERTS, UPDATES, and DELETES
                //...while executeQuery() is used with SELECTS

            //We can then return the new Employee object (we can just use the method parameter)
            return emp;

            //TODO: you could get the emp from the DB but it would be a bit more work
            //we would need some other getBy___ method for employees, then return that

        } catch (SQLException e){
            e.printStackTrace(); //tells us what went wrong
            System.out.println("Couldn't insert Employee");
        }

        //catch-all return statement - if something goes wrong, we'll return null
        return null;
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        return null;
    }



}

package com.revature.DAOs;

import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
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

        //We need to open a Connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL String - this one is easier since there are no variables
            String sql = "SELECT * FROM employees";

            //We can use a Statement object instead of PreparedStatement since there are no variables
            Statement s = conn.createStatement();

            //Execute the query, saving the results in a ResultSet
            ResultSet rs = s.executeQuery(sql);

            //We need an ArrayList to store our Employees
            ArrayList<Employee> employees = new ArrayList<>();

            //Loop through the ResultSet with rs.next()
            //rs.next() will return false when there are no more rows in the ResultSet (breaks the loop)
            while(rs.next()){

                //For every Employee found, add it to the ArrayList
                //We will need to instantiate a new Employee object for each row in the ResultSet
                //We can get each piece of Employee data with rs.get____ methods
                Employee e = new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    null //TODO: we can use our getRoleByID method to fill this Role object!
                );

                //Let's use RoleDAO.getRoleById() to add the Role to our employee
                RoleDAO rDAO = new RoleDAO();
                Role role = rDAO.getRoleById(rs.getInt("role_id_fk"));
                //"Get a new Role by using the role_id_fk from the DB"

                //Now that we have the Role, we can set it in our Employee
                e.setRole(role);

                //Now, we can finally add the Employee to our ArrayList
                employees.add(e);

            } //end of while loop

            //when the while loop breaks, we can finally return the full ArrayList
            return employees;

        } catch (SQLException e){
            e.printStackTrace(); //tells us what went wrong
            System.out.println("Couldn't get all Employees");
        }

        //catch-all return
        return null;
    }



}

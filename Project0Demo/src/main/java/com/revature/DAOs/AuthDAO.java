package com.revature.DAOs;

import com.revature.models.Employee;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//This DAO will handle Login (select where employee_id = ? and first_name = ?)
//We should have had username/password columns in the employees table,
    //but I didn't think we would get this far in week 3 and I don't want to do a major refactor
public class AuthDAO {

    public Employee login(int employee_id, String first_name){

        //open a connection
        try(Connection conn = ConnectionUtil.getConnection()){

            //Create our SQL String
            String sql = "SELECT * FROM employees WHERE employee_id = ? AND first_name = ?";

            //PreparedStatement and fill in the blanks
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, employee_id);
            ps.setString(2, first_name);

            ResultSet rs = ps.executeQuery();

            //Instantiate a RoleDAO to use the getRoleById method
            RoleDAO rDAO = new RoleDAO();

            if(rs.next()){

                Employee emp = new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rDAO.getRoleById(rs.getInt("role_id_fk")) //cleaner! harder to read?
                );

                return emp;
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Couldn't login user");
        }

        return null;
    }

}

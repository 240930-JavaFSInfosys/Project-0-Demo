package com.revature;

import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Launcher {

    public static void main(String[] args) {

        /* This is a "try with resources" block

        A resource is opened up within the parenthesis of the try block (in this case a DB connection)
        If the resource successfully creates, the rest of the try block will run
        A big benefit of this, is that the resource will then CLOSE after the try block finishes
        This is helpful for code cleanup and preventing memory leaks */
        try(Connection conn = ConnectionUtil.getConnection()){
            System.out.println("CONNECTION SUCCESSFUL :D");
        } catch(SQLException e){
            e.printStackTrace(); //this is what tells us our error message (the red text)
            System.out.println("CONNECTION FAILED D:");
        }

    }

}

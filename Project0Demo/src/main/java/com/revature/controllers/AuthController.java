package com.revature.controllers;

import com.revature.DAOs.AuthDAO;
import io.javalin.http.Handler;


//This Controller is for handling authentication functionalities (like login/regsiter)
//In the future, Register user would be here too, but for now we have insertEmployee in the EmpController
public class AuthController {

    //Instantiate the AuthDAO so we can try to log in a user
    AuthDAO aDAO = new AuthDAO();

    //TODO: Session object that will be initialized upon login
        //"Initialized"? Just means it will be given a value

    //login will be a POST request, since we're sending a username and password (id and name for this)
    public Handler loginHandler = ctx -> {

    };


}

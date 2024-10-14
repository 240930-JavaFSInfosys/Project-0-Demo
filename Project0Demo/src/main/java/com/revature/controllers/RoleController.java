package com.revature.controllers;

import com.revature.DAOs.RoleDAO;
import com.revature.models.Role;
import io.javalin.http.Handler;

public class RoleController {

    //We need a RoleDAO to use its methods
    RoleDAO rDAO = new RoleDAO();

    //This Handler will handle GET requests to /roles/{id}
    public Handler getRoleByIdHandler = ctx -> {

        //TODO: make sure the id the user sent in is > 0

        //extract the path parameter from the HTTP Request URL
        //NOTE: pathParam() returns a String, but we need it as an int
        int role_id = Integer.parseInt(ctx.pathParam("id"));

        //instantiate a Role that holds the data from the specific role ID
        Role role = rDAO.getRoleById(role_id);

        //TODO: make sure the Role that came back isn't null, and maybe some other checks

        //Send the Role back to the client in an HTTP Response
        ctx.json(role);
        ctx.status(200); //200 - OK

    };

}

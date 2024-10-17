package com.revature;

import com.revature.controllers.AuthController;
import com.revature.controllers.EmployeeController;
import com.revature.controllers.RoleController;
import io.javalin.Javalin;

public class LauncherNEW {

    public static void main(String[] args) {

        //Typical Javalin setup Syntax
        var app = Javalin.create().start(7000);

        //BEFORE HANDLER! This is what's checking for a not null session to see if a user is logged in
        //If a user is not logged (Session == null), then send throw an exception to be handled by the exception handler
        app.before("/employees", ctx -> {
            System.out.println("Inside Before Handler");
            if(AuthController.ses == null){
                System.out.println("Session is null!");
                throw new IllegalArgumentException("You must log in before doing this!");
            }
        });

        //Exception handler for the before handler, telling the user to log in if the session is null
        app.exception(IllegalArgumentException.class, (e, ctx) -> {
            ctx.status(401);
            ctx.result(e.getMessage());
        });

        /* We need create() to begin the instantiation of our Javalin object
         We need start() to actually start our Javalin app on a port of our choosing
         You can choose any port, but make sure it's a port that isn't being used (like 5432)
         A port is like a parking space, a place for your app sit where other apps can find it */

        //Very basic callable resource just for fun
        //NOTE: we sent a response from Launcher here, but Responses will really be in the Controllers
        app.get("/", ctx -> ctx.result("Hello Javalin and Postman!"));

        //Instantiate Controllers so we can access the Handlers
        EmployeeController ec = new EmployeeController();
        RoleController rc = new RoleController();
        AuthController ac = new AuthController();

        //Get All Employees
        /*app.get is the Javalin handler method that takes in GET requests
        * In this case, it's calling the getEmployeesHandler of the EmployeeController
        * So when we send a GET request to localhost:7000/employees it gets routed here. */
        app.get("/employees", ec.getEmployeesHandler);

        //Insert Employee
        /*app.post is the Javalin handler method that takes in POST requests
        * Why are we allowed to have 2 handlers that end in /employees? They have different verbs!*/
        app.post("/employees", ec.insertEmployeeHandler);

        //Get Role By ID
        /*what is {id}? this is a PATH PARAMETER. The id we're searching for is a variable*/
        app.get("/roles/{id}", rc.getRoleByIdHandler);

        //Update Role Salary
        app.patch("/roles/{id}", rc.updateRoleSalary);

        //Login employee
        app.post("/auth", ac.loginHandler);

    }

}

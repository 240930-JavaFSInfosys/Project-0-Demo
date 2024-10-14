package com.revature;

import io.javalin.Javalin;

public class LauncherNEW {

    public static void main(String[] args) {

        //Typical Javalin setup Syntax
        var app = Javalin.create().start(7000);

        /* We need create() to begin the instantiation of our Javalin object
         We need start() to actually start our Javalin app on a port of our choosing
         You can choose any port, but make sure it's a port that isn't being used (like 5432)
         A port is like a parking space, a place for your app sit where other apps can find it */

        //Very basic callable resource just for fun
        app.get("/", ctx -> ctx.result("Hello Javalin and Postman!"));

    }

}

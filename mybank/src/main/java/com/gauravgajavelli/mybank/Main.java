package com.gauravgajavelli.mybank;

import com.gauravgajavelli.mybank.web.MyBankServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class Main {
    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
//        System.out.println("Aiiiiiii");
//        for (String arg : args) {
//            System.out.println("aaaaaaa "+arg);
//        }
        String portStr = System.getProperty("server.port");
        Integer port = (portStr != null)?Integer.parseInt(portStr):8080;
        tomcat.setPort(port); // TODO Get from the command line args, default to 8080
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", null);
        Wrapper servlet = Tomcat.addServlet(ctx, "myBankServlet", new MyBankServlet());
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");

        tomcat.start();
    }
}
package com.gauravgajavelli.mybank;

import com.gauravgajavelli.mybank.context.Application;
import jakarta.servlet.ServletContext;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Main {
    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();

        String portStr = System.getProperty("server.port");
        Integer port = (portStr != null)?Integer.parseInt(portStr):8080;
        tomcat.setPort(port); // TODO Get from the command line args, default to 8080
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", null);
        WebApplicationContext appCtx = createApplicationContext(ctx.getServletContext());
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appCtx);
        Wrapper servlet = Tomcat.addServlet(ctx, "myBankServlet", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");

        tomcat.start();
    }

    public static WebApplicationContext createApplicationContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(Application.class);
        ctx.setServletContext(servletContext);
        ctx.refresh();
        ctx.registerShutdownHook();
        return ctx;
    }
}
package com.gauravgajavelli.mybank.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gauravgajavelli.mybank.context.Application;
import com.gauravgajavelli.mybank.service.TransactionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.gauravgajavelli.mybank.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.List;

public class MyBankServlet extends HttpServlet {
    // find and create transactions
        // id, amount, timestamp, and reference
        // int int string string
        // timestamp must be formatted as yyyy-MM-dd’T’HH:mm’Z'
        // Use Java 8 datetimes
        // See how to read in command line arguments

    private TransactionService transactionService;
    private ObjectMapper objectMapper;
    @Override
    public void init() throws ServletException {
        AnnotationConfigApplicationContext ctx
                = new AnnotationConfigApplicationContext(Application.class);

        ctx.registerShutdownHook();

        this.objectMapper = ctx.getBean(ObjectMapper.class);
        this.transactionService = ctx.getBean(TransactionService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equalsIgnoreCase("/")) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(
                    "<html>\n" +
                            "<body>\n" +
                            "<h1>Welcome to the Transaction API!</h1>\n" +
                            "<p>Get/Post transactions to get/post transactions!</p>\n" +
                            "</body>\n" +
                            "</html>");
        } else if (request.getRequestURI().equalsIgnoreCase("/transactions")) {
            response.setContentType("application/json; charset=UTF-8");
            List<Transaction> invoices = transactionService.findAll();
            response.getWriter().print(objectMapper.writeValueAsString(invoices));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equalsIgnoreCase("/transactions")) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Integer amount = Integer.valueOf(request.getParameter("amount"));
            String timestamp = request.getParameter("timestamp");
            String reference = request.getParameter("reference");

            Transaction transaction = transactionService.create(id, amount, timestamp, reference);

            response.setContentType("application/json; charset=UTF-8");
            String json = objectMapper.writeValueAsString(transaction);
            response.getWriter().print(json);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

package com.gauravgajavelli.myfancypdfinvoices.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gauravgajavelli.myfancypdfinvoices.service.InvoiceService;
import com.gauravgajavelli.myfancypdfinvoices.service.UserService;

public class Application {
    public static final UserService userService = new UserService();
    public static final InvoiceService invoiceService = new InvoiceService(userService);
    public static final ObjectMapper objectMapper = new ObjectMapper();
}
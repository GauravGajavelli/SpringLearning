package com.gauravgajavelli.myfancypdfinvoices.web;

import com.gauravgajavelli.myfancypdfinvoices.dto.InvoiceDto;
import com.gauravgajavelli.myfancypdfinvoices.model.Invoice;
import com.gauravgajavelli.myfancypdfinvoices.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyFancyPdfInvoicesController {

    private final InvoiceService invoiceService;

    public MyFancyPdfInvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    // @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public List<Invoice> invoices() {
        return invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestBody @Valid InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }
}
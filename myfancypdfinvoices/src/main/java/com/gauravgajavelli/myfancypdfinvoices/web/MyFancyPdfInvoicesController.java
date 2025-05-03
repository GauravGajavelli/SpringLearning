package com.gauravgajavelli.myfancypdfinvoices.web;

import com.gauravgajavelli.myfancypdfinvoices.dto.InvoiceDto;
import com.gauravgajavelli.myfancypdfinvoices.model.Invoice;
import com.gauravgajavelli.myfancypdfinvoices.service.InvoiceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
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
    public Invoice createInvoice(@RequestParam("user_id") @NotBlank String userId, @RequestParam @Min(10) @Max(50) Integer amount) {
        return invoiceService.create(userId, amount);
    }
}
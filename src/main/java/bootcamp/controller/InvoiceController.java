package bootcamp.controller;



import bootcamp.model.invoice.Invoice;

import bootcamp.model.invoice.InvoiceItem;
import bootcamp.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoice")

    Boolean receiveInvoice(@RequestBody Invoice invoiceItem) {


        invoiceService.processInvoice(invoiceItem);
        return true;

    }


}


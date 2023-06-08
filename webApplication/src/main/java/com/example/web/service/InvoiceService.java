package com.example.web.service;
import com.example.web.model.InvoiceModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface InvoiceService {

    Boolean saveInvoice(InvoiceModel invoiceModel , HttpServletRequest request , HttpServletResponse response);
}

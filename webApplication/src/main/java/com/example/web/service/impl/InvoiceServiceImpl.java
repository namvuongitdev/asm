package com.example.web.service.impl;
import com.example.web.entity.DetailsIvoice;
import com.example.web.entity.Invoice;
import com.example.web.entity.ProductDetails;
import com.example.web.entity.composite.DetalisInvoiceID;
import com.example.web.model.InvoiceModel;
import com.example.web.repository.IProductDetailsRepository;
import com.example.web.repository.InvoiceRepository;
import com.example.web.service.InvoiceService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private IProductDetailsRepository productDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean saveInvoice(InvoiceModel invoiceModel, HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        Invoice invoice = modelMapper.map(invoiceModel, Invoice.class);
        Float totalMoney = 0f;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equalsIgnoreCase("JSESSIONID")){
                    continue;
                }
                Optional<ProductDetails> productDetails = productDetailsRepository.findById(Long.parseLong(cookie.getName()));
                if(productDetails.isPresent()){
                    Integer quantity = productDetails.get().getQuantity() - Integer.parseInt(cookie.getValue());
                    productDetails.get().setQuantity(quantity);

                    DetalisInvoiceID detalisInvoiceID = new DetalisInvoiceID(productDetails.get(), invoice);
                    DetailsIvoice detailsIvoice = new DetailsIvoice(detalisInvoiceID, Integer.parseInt(cookie.getValue()), productDetails.get().getProduct().getPrice());
                     totalMoney += productDetails.get().getProduct().getPrice()  * Integer.parseInt(cookie.getValue());

                    invoice.getDetailsIvoices().add(detailsIvoice);
                    productDetailsRepository.save(productDetails.get());

                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }else{
                    return false;
                }
            }
        }else{
            return false;
        }
        invoice.setTotalMoney(totalMoney);
       invoiceRepository.save(invoice);
        return true;
    }
}

package com.example.web.entity.composite;
import com.example.web.entity.Invoice;
import com.example.web.entity.ProductDetails;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DetalisInvoiceID implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_ctsp")
    private ProductDetails productDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_hoa_don")
    private Invoice invoice;
}

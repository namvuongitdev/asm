package com.example.web.entity;
import com.example.web.entity.composite.DetalisInvoiceID;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="hoa_don_chi_tiet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DetailsIvoice {

    @EmbeddedId
    private DetalisInvoiceID invoice_details;

    @Column(name="so_luong")
    private Integer quantity;

    @Column(name="don_gia")
    private Float price;
}

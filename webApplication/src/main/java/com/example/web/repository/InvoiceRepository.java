package com.example.web.repository;
import com.example.web.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice , Long> {

    @Override
    <S extends Invoice> S save(S entity);
}

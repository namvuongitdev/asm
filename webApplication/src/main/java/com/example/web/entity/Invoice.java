package com.example.web.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hoa_don")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Invoice {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ngay_tao")
    private LocalDateTime dateCreated;

    @Column(name = "ho_ten")
    @Nationalized
    private String fullName;

    @Column(name = "so_dien_thoai")
    private String phoneNumber;

    @Column(name = "dia_chi")
    @Nationalized
    private String address;

    @Column(name = "trang_thai" ,columnDefinition = "int default 1")
    private Integer status;

    @Column(name = "tong_tien")
    private Float totalMoney;

    @ManyToOne
    @JoinColumn(name = "id_nguoi_dung")
    private User user;

    @OneToMany(mappedBy = "invoice_details.invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetailsIvoice> detailsIvoices = new HashSet<>();
}

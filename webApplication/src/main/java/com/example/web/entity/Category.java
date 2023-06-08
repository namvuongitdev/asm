package com.example.web.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="danh_muc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Category {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="ten")
    @Nationalized
    private String name;

    @OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL , orphanRemoval = true)
    private Set<ProductDetails> productsDetails = new HashSet<>();
}

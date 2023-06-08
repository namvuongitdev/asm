package com.example.web.model;
import com.example.web.config.ShowUserDetails;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class InvoiceModel implements Serializable {

    private Long id;

    private LocalDateTime dateCreated;

    private String fullName;

    private String phoneNumber;

    private String address;

    private Integer status;

    private Float totalMoney;

    private UserModel user;

}

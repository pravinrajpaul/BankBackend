package com.geevin.accounts.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "customers")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class CustomersEntity extends CreateDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private Long customerId;
    private String name;
    private String email;
    private String mobileNumber;
}

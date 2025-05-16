package com.upb.modulo_01.entity;

import com.upb.modulo_01.entity.enums.StateEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_ID_GENERATOR")
    @SequenceGenerator(name = "COMPANY_ID_GENERATOR", sequenceName = "SEQ_COMPANY_ID", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "nit", length = 10)
    private String nit;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private StateEntity state;

}

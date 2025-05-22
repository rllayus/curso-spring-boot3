package com.upb.modulo_01.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Comment("Tabla para almacenar las notas de venta")
@Entity
@Table(name = "nota_venta")
public class NotaVenta {
    @Comment("Identificador del registros")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTA_VENTA_ID_GENERATOR")
    @SequenceGenerator(name = "NOTA_VENTA_ID_GENERATOR", sequenceName = "SEQ_NOTA_VENTA_ID", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Comment("Fecha de la venta")
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Comment("Total de la venta")
    @Column(name = "total", precision = 20, scale = 10)
    private BigDecimal total;

    @JsonIgnore
    @Comment("Cliente")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private MyUser customer;

}

package com.upb.modulo_01.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Comment("Tabla para almacenar el detalle de nota de venta")
@Entity
@Table(name = "detalle_nota_venta")
public class DetalleNotaVenta {
    @Comment("Identificador del registros")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETALLE_NOTAVENTA_ID_GENERATOR")
    @SequenceGenerator(name = "DETALLE_NOTAVENTA_ID_GENERATOR", sequenceName = "SEQ_DET_NOTA_VENTA_ID", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Comment("Precio del producto")
    @Column(name = "price", precision = 20, scale = 10)
    private BigDecimal price;

    @Comment("Cantidad comprado")
    @Column(name = "quantity")
    private int quantity;

    @Comment("Total de la venta")
    @Column(name = "total", precision = 20, scale = 10)
    private BigDecimal total;

    @JsonIgnore
    @Comment("Producto")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @JsonIgnore
    @Comment("Producto")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_venta_id", referencedColumnName = "id", nullable = false)
    private NotaVenta notaVenta;

}

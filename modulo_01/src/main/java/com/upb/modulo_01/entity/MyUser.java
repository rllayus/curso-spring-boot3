package com.upb.modulo_01.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Comment("Tabla para almacenar a los clientes")
@Entity
@Table(name = "my_user")
public class MyUser {
    @Comment("Identificador del registros")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_ID_GENERATOR")
    @SequenceGenerator(name = "CUSTOMER_ID_GENERATOR", sequenceName = "seq_my_user", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Comment("Campo para almacenar el nombre del cliente")
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Comment("Campo para almacenar el apellido del cliente")
    @Column(name = "lastname", length = 60, nullable = false)
    private String lastName;

    @Comment("Campo para almacenar el apellido del cliente")
    @Column(name = "document_number", length = 20, nullable = false)
    private String documentNumber;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private String gender;


}

package com.upb.modulo_01.entity;

import com.upb.modulo_01.entity.enums.RoleType;
import com.upb.modulo_01.entity.enums.StateEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Comment("Tabla para almacenar a los clientes")
@Entity
@Table(name = "my_user")
public class MyUser extends AuditableEntity implements UserDetails {
    @Comment("Identificador del registros")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_ID_GENERATOR")
    @SequenceGenerator(name = "CUSTOMER_ID_GENERATOR", sequenceName = "seq_my_user", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Comment("Campo para almacenar el nombre del cliente")
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Comment("Campo para almacenar el nombre del cliente")
    @Column(name = "username", length = 20, nullable = false, unique = true)
    private String username;

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

    @Column(name = "password")
    private String password;

    @Comment("Estado del usuario")
    @Column(name = "status", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StateEntity status;

    @Comment("Rol del usuario")
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status == StateEntity.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

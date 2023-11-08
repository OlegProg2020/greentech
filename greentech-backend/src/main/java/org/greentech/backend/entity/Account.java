package org.greentech.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.greentech.backend.entity.enums.Role;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = -8250007303889622015L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String phone;

    @ToString.Exclude
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String name;
}

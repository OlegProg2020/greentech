package org.greentech.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.greentech.backend.entity.enums.Role;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

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

    @OneToOne(cascade = CascadeType.PERSIST)
    private Cart cart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;

        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

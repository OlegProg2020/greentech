package org.greentech.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = -7621795797627189718L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Артикул товара. Различные вариации товара должны иметь один артикул, чтобы
     * была возможность отображения в одной карточке товара на фронтенде.
     */
    private String article;
    private String name;
    private BigDecimal price;
    private String description;

    @ElementCollection
    @CollectionTable(name = "product_parameter", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyJoinColumn(name = "parameter_id")
    @Column(name = "value")
    @Builder.Default
    @ToString.Exclude
    Map<Parameter, String> characteristics = new HashMap<>();

    @Builder.Default
    @ToString.Exclude
    @OneToMany(cascade=CascadeType.ALL)
    List<Image> images = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;

        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

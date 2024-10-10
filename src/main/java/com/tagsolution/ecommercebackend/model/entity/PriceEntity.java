package com.tagsolution.ecommercebackend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "price")
@SQLDelete(sql = "UPDATE price SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class PriceEntity extends AuditableEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    private BigDecimal discount;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean isDefaultPrice;

}

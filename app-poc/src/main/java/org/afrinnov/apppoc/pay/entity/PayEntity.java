package org.afrinnov.apppoc.pay.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_pay_monthly", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"matricule", "year", "month"})
})
@Getter
@Setter
@Builder
@NonNull
@NoArgsConstructor
@AllArgsConstructor
public class PayEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PMonth month;
    @Column(nullable = false)
    private int year;
    @Column(nullable = false)
    private String matricule;
    private boolean paid;
}

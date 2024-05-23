package org.afrinnov.apppoc.pay.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_pay_monthly", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"matricule", "_year", "_month"})
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
    @Column(nullable = false, name = "_month")
    @Enumerated(EnumType.STRING)
    private PMonth month;
    @Column(nullable = false, name = "_year")
    private int year;
    @Column(nullable = false)
    private String matricule;
    private boolean paid;
}

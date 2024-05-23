package org.afrinnov.apppoc.cra.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_cra_monthly", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"matricule", "year", "month"})
})
@Getter
@Setter
@Builder
@NonNull
@NoArgsConstructor
@AllArgsConstructor
public class CraEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MMonth month;
    @Column(nullable = false)
    private int year;
    @Column(nullable = false)
    private String matricule;
    private boolean edited;
}

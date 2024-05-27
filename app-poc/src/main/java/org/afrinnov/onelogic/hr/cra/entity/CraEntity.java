package org.afrinnov.onelogic.hr.cra.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_cra_monthly", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"matricule", "_year", "_month"})
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
    @Column(nullable = false, name = "_month")
    @Enumerated(EnumType.STRING)
    private MMonth month;
    @Column(nullable = false, name = "_year")
    private int year;
    @Column(nullable = false)
    private String matricule;
    private boolean edited;
}

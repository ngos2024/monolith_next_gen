package org.afrinnov.onelogic.hr.holiday.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "m_holiday", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"matricule", "_date"})
})
@Getter
@Setter
@Builder
@NonNull
@NoArgsConstructor
@AllArgsConstructor
public class HolidayEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private String matricule;
    @Column(nullable = false, name = "_date")
    private LocalDate date;
    @Column(nullable = false)
    private boolean accepted;
}

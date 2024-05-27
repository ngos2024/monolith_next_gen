package org.afrinnov.onelogic.hr.employee.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_employee")
@Getter
@Setter
@Builder
@NonNull
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(unique = true, nullable = false)
    private String matricule;
    @Column(unique = true, nullable = false)
    private String name;
    private String country;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private boolean status;
}

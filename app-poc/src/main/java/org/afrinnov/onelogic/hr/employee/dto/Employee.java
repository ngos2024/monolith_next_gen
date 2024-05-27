package org.afrinnov.onelogic.hr.employee.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Employee {
    private String id;
    private String name;
    private String country;
}

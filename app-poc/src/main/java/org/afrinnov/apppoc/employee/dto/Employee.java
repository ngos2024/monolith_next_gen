package org.afrinnov.apppoc.employee.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Employee {
    private String id;
    private String name;
    private String country;
}

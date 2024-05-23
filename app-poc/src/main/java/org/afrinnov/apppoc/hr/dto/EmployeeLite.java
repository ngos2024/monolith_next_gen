package org.afrinnov.apppoc.hr.dto;

import lombok.Builder;
import lombok.Getter;
import org.afrinnov.apppoc.employee.spi.model.EmployeeInfo;

@Getter
@Builder
public class EmployeeLite {
    private EmployeeInfo employee;
    private boolean craEdited;
    private boolean paid;
    private long countHoliday;
}

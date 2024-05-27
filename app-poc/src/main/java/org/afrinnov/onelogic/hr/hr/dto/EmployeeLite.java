package org.afrinnov.onelogic.hr.hr.dto;

import lombok.Builder;
import lombok.Getter;
import org.afrinnov.onelogic.hr.employee.spi.model.EmployeeInfo;

@Getter
@Builder
public class EmployeeLite {
    private EmployeeInfo employee;
    private boolean craEdited;
    private boolean paid;
    private long countHoliday;
}

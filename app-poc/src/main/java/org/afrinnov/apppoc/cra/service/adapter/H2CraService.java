package org.afrinnov.apppoc.cra.service.adapter;

import lombok.RequiredArgsConstructor;
import org.afrinnov.apppoc.cra.entity.CraEntity;
import org.afrinnov.apppoc.cra.entity.MMonth;
import org.afrinnov.apppoc.cra.repository.CraRepository;
import org.afrinnov.apppoc.cra.rest.input.MonthlyCreate;
import org.afrinnov.apppoc.cra.rest.input.MonthlyEdit;
import org.afrinnov.apppoc.cra.service.CraAlertService;
import org.afrinnov.apppoc.cra.service.CraService;
import org.afrinnov.apppoc.cra.spi.CraApiService;
import org.afrinnov.apppoc.employee.spi.EmployeeApiService;
import org.afrinnov.apppoc.employee.spi.model.EmployeeInfo;
import org.afrinnov.apppoc.notify.MailMessage;
import org.afrinnov.apppoc.notify.Nature;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class H2CraService implements CraService, CraAlertService, CraApiService {
    private final EmployeeApiService employeeApiService;
    private final CraRepository craRepository;
    private final ApplicationEventPublisher events;

    @Override
    public void create(MonthlyCreate monthlyCreate) {
        int currentYear = Year.now().getValue();
        String matricule = monthlyCreate.getMatricule();
        MMonth month = monthlyCreate.getMonth();
        boolean alreadyEdited = craRepository.existsByMatriculeAndMonthAndYearAndEditedTrue(matricule, month, currentYear);
        if (alreadyEdited) {
            throw new IllegalArgumentException("Cra already edited for employee:" + monthlyCreate.getMatricule());
        }
        employeeApiService.check(monthlyCreate.getMatricule());
        CraEntity cra = CraEntity.builder()
                .month(monthlyCreate.getMonth())
                .year(currentYear)
                .matricule(monthlyCreate.getMatricule())
                .edited(true)
                .build();
        craRepository.save(cra);
    }

    @Override
    public void edit(MonthlyEdit monthlyEdit) {
        int currentYear = Year.now().getValue();
        CraEntity cra = craRepository.findByMatriculeAndYearAndMonth(monthlyEdit.matricule(), currentYear, monthlyEdit.month())
                .orElseThrow(() -> new IllegalArgumentException("Cra is not edited for employee:" + monthlyEdit.matricule()));
        cra.setEdited(!cra.isEdited());
    }

    @Override
    public void alert() {
        int currentYear = Year.now().getValue();
        Month month = MonthDay.now().getMonth();
        List<CraEntity> entities = craRepository.findByYearAndMonthAndEditedFalse(currentYear, MMonth.valueOf(month.name()));
        entities.forEach(entity -> {
            Optional<EmployeeInfo> employeeInfo = employeeApiService.getEmployee(entity.getMatricule());
            if (employeeInfo.isPresent()) {
                EmployeeInfo employee = employeeInfo.get();
                MailMessage message = MailMessage.builder()
                        .matricule(entity.getMatricule())
                        .email(employee.getEmail())
                        .name(employee.getName())
                        .nature(Nature.ALERT_CRA)
                        .build();
                events.publishEvent(message);
            }
        });

    }

    @Override
    public boolean isEdited(String matricule, int currentYear, Month month) {
        return craRepository.existsByMatriculeAndYearAndMonthAndEditedTrue(matricule,
                currentYear, MMonth.valueOf(month.name()));
    }
}

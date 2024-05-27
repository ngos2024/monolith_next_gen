package org.afrinnov.onelogic.hr.pay.service.adapter;

import lombok.RequiredArgsConstructor;
import org.afrinnov.onelogic.hr.employee.spi.EmployeeApiService;
import org.afrinnov.onelogic.hr.employee.spi.model.EmployeeInfo;
import org.afrinnov.onelogic.hr.notify.MailMessage;
import org.afrinnov.onelogic.hr.notify.Nature;
import org.afrinnov.onelogic.hr.pay.entity.PMonth;
import org.afrinnov.onelogic.hr.pay.entity.PayEntity;
import org.afrinnov.onelogic.hr.pay.repository.PayRepository;
import org.afrinnov.onelogic.hr.pay.rest.input.PayCreate;
import org.afrinnov.onelogic.hr.pay.service.PayService;
import org.afrinnov.onelogic.hr.pay.spi.PayApiService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class H2PayService implements PayService, PayApiService {
    private final EmployeeApiService employeeApiService;
    private final ApplicationEventPublisher events;

    private final PayRepository payEntityRepository;

    @Override
    public void create(PayCreate create) {
        employeeApiService.check(create.matricule());
        PayEntity entity = PayEntity.builder()
                .matricule(create.matricule())
                .year(Year.now().getValue())
                .month(create.month())
                .paid(true)
                .build();
        payEntityRepository.save(entity);
        events.publishEvent(MailMessage.builder()
                .nature(Nature.NOTIFY_COMPTABLE)
                .matricule(create.matricule())
                .data(Map.of("comptableEmail", "madame24@afrinnov.net"))
                .build());
    }

    @Override
    public void validate() {
        int currentYear = Year.now().getValue();
        Month month = MonthDay.now().getMonth();
        List<PayEntity> paies = payEntityRepository.findByYearAndMonthAndPaidTrue(currentYear, PMonth.valueOf(month.name()));
        paies.forEach(entity -> {
            Optional<EmployeeInfo> employeeInfo = employeeApiService.getEmployee(entity.getMatricule());
            if (employeeInfo.isPresent()) {
                EmployeeInfo employee = employeeInfo.get();
                MailMessage message = MailMessage.builder()
                        .matricule(entity.getMatricule())
                        .email(employee.getEmail())
                        .name(employee.getName())
                        .nature(Nature.ALERT_PAIEMENT)
                        .build();
                events.publishEvent(message);
            }
        });

    }

    @Override
    public boolean isPaid(String matricule, int currentYear, Month month) {
        return payEntityRepository.existsByMatriculeAndYearAndMonthAndPaidTrue(matricule,
                currentYear, PMonth.valueOf(month.name()));
    }
}

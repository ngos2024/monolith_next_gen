package org.afrinnov.apppoc.pay.service.adapter;

import lombok.RequiredArgsConstructor;
import org.afrinnov.apppoc.employee.spi.EmployeeApiService;
import org.afrinnov.apppoc.employee.spi.model.EmployeeInfo;
import org.afrinnov.apppoc.notify.MailMessage;
import org.afrinnov.apppoc.notify.Nature;
import org.afrinnov.apppoc.pay.entity.PMonth;
import org.afrinnov.apppoc.pay.entity.PayEntity;
import org.afrinnov.apppoc.pay.repository.PayRepository;
import org.afrinnov.apppoc.pay.rest.input.PayCreate;
import org.afrinnov.apppoc.pay.service.PayService;
import org.afrinnov.apppoc.pay.spi.PayApiService;
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

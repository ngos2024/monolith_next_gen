package org.afrinnov.onelogic.hr.notify.service;

import lombok.extern.slf4j.Slf4j;
import org.afrinnov.onelogic.hr.notify.MailMessage;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailSendService {
    @ApplicationModuleListener
    void on(MailMessage message) {
        log.info("Message: {}", message);
    }
}

package org.afrinnov.onelogic.hr.notify;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@Builder
@ToString
public class MailMessage {
    private Nature nature;
    private String email;
    private String matricule;
    private String name;
    private Map<String, Object> data;
}

package org.afrinnov.apppoc.notify;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class MailMessage {
    private Nature nature;
    private String email;
    private String matricule;
    private String name;
    private Map<String, Object> data;
}

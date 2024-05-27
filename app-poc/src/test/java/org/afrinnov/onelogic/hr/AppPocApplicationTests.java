package org.afrinnov.onelogic.hr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest
class AppPocApplicationTests {
    private final ApplicationModules applicationModules = ApplicationModules.of(AppPocApplication.class);
    @Test
    void contextLoads() {
        applicationModules.verify();
    }

    @Test
    void generateDoc() {
        new Documenter(applicationModules).writeDocumentation();
    }

}

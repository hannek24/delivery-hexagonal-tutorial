package com.tutorial.purchases.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tutorial.purchases.application.ControllerExceptionAdvice;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ApplicationLayerArchitectureTest {

    private final JavaClasses classes = new ClassFileImporter()
            .withImportOption(new ImportOption() {
                @Override
                public boolean includes(com.tngtech.archunit.core.importer.Location location) {
                    try {
                        return !"jrt".equals(location.asURI().getScheme());
                    } catch (Exception e) {
                        return true;
                    }
                }
            })
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
            .importPackages("com.tutorial.purchases");

    @Test
    void application_should_not_depend_on_domain_services_or_outgoing() {
        ArchRule rule = noClasses().that().resideInAPackage("..application..")
                .should().dependOnClassesThat().resideInAnyPackage(
                        "com.tutorial.purchases.domain.services..",
                        "com.tutorial.purchases.domain.ports.outgoing.."
                );

        rule.check(classes);
    }

    @Test
    void application_should_only_depend_on_allowed_packages() {

        // except for ControllerExceptionAdvice.class
        ArchRule rule = classes().that()
                .haveNameNotMatching(".*ExceptionAdvice*.")
                .and().resideInAPackage("..application..")
                .should().onlyDependOnClassesThat()
                .resideInAnyPackage(
                        "org.springframework.web.context..",
                        "com.tutorial.purchases.application..",
                        "com.tutorial.purchases.domain.models..",
                        "com.tutorial.purchases.domain.ports.incoming..",
                        "java..",
                        "jakarta.servlet..",
                        "org.springframework..",
                        "org.slf4j..",
                        "com.fasterxml.jackson..",
                        "org.mapstruct..",
                        "lombok..",
                        "org.apache.commons.."
                );

        rule.check(classes);
    }
}

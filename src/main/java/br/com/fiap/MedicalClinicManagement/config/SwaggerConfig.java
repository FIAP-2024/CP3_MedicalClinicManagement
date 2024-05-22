package br.com.fiap.MedicalClinicManagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI OpenApiConfiguration() {
        return new OpenAPI()
                .info(new Info()
                        .title("Medical Clinic Management - API")
                        .version("0.1.0")
                        .description("The system will make it easier to manage clinics, doctors, patients and book appointments. " +
                                "appointments. It will be necessary to implement security features and integration with modern web " +
                                "development APIs.")
                        .contact(new Contact()
                                .email("technosfiap@gmail.com")
                                .name("GitHub Technos")
                                .url("https://github.com/FIAP-2024/CP3_MedicalClinicManagement"))
                        .license(new License()));
    }
}
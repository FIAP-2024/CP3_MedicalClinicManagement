package br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic;

import jakarta.validation.constraints.NotBlank;

public record ClinicRegisterDTO(
        @NotBlank
        String name,
        @NotBlank
        String address,
        @NotBlank
        String phone,
        @NotBlank
        String email,
        @NotBlank
        String workingHours,
        @NotBlank
        String cnpj

) {
}

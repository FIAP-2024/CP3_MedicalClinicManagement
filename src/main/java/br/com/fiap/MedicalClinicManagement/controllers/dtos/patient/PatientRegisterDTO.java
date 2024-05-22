package br.com.fiap.MedicalClinicManagement.controllers.dtos.patient;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record PatientRegisterDTO(

        @NotBlank
        String name,
        @NotBlank
        LocalDate birthDate,
        @NotBlank
        String cpf,
        @NotBlank
        String rg,
        @NotBlank
        String address,
        @NotBlank
        String phone,
        @NotBlank
        String email,
        @NotBlank
        String gender,
        @NotBlank
        String maritalStatus

) {
}

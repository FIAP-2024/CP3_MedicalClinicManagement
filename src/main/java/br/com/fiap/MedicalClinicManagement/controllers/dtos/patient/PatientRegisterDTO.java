package br.com.fiap.MedicalClinicManagement.controllers.dtos.patient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

public record PatientRegisterDTO(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotNull
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
        String maritalStatus,
        @NotNull
        Long idAppointments,
        @NotNull
        Long idDoctor

) {
}

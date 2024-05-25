package br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record ClinicRegisterDTO(
        @NotNull
        Long id,
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
        String cnpj,

        @NotNull
        Long idDoctors,
        @NotNull
        Long idPacients,
        @NotNull
        Long idAppointments



) {
}

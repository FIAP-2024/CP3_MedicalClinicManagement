package br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record DoctorRegisterDTO(
        @NotNull
        Long id,

        @NotBlank
        String name,

        @NotBlank
        String address,

        @NotBlank
        String specialty

) {
}

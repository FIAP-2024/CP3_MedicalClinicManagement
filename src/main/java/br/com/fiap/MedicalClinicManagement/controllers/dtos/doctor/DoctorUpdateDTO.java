package br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateDTO(
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

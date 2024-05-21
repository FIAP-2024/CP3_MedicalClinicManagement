package br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentUpdateDTO(
        @NotNull
        Long id,

        @NotNull
        LocalDateTime dateTime,

        @NotBlank
        String appointmentType,

        @NotBlank
        String status,
        @NotBlank
        String notes
) {

}

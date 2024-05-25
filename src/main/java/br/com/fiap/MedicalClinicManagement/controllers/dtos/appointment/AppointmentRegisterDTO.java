package br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment;

import br.com.fiap.MedicalClinicManagement.models.Appointment;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AppointmentRegisterDTO(
        @NotNull
        Long id,

        @NotNull @DateTimeFormat
        LocalDateTime dateTime,

        @NotBlank
        String appointmentType,

        @NotBlank
        String status,

        @NotBlank
        String notes,

        @NotNull
        Long idDoctors,

        @NotNull
        Long idPatients,

        @NotNull
        Long idClinics
) {
}

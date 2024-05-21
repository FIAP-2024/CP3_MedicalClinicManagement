package br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment;

import br.com.fiap.MedicalClinicManagement.models.Appointment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentDetailedDTO(
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
    public AppointmentDetailedDTO(Appointment appointment){
        this(appointment.getId(), appointment.getDateTime(), appointment.getAppointmentType(), appointment.getStatus(), appointment.getNotes());
    }
}

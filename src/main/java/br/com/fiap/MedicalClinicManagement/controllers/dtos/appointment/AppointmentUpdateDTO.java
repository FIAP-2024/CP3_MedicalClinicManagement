package br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentUpdateDTO(
        Long id,
        LocalDateTime dateTime,
        String appointmentType,
        String status,
        String notes
) {

}

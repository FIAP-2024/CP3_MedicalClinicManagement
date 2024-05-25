package br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor;

import javax.validation.constraints.NotNull;

public record DoctorUpdateDTO(
        Long id,
        String name,
        String address,
        String specialty
) {
}

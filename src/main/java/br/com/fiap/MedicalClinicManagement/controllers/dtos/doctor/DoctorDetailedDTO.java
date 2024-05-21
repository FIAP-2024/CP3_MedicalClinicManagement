package br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor;

import br.com.fiap.MedicalClinicManagement.models.Doctor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorDetailedDTO(
        @NotNull
        Long id,

        @NotBlank
        String name,

        @NotBlank
        String address,

        @NotBlank
        String specialty
) {
        public DoctorDetailedDTO(Doctor doctor) {
                this(doctor.getId(), doctor.getName(), doctor.getAddress(), doctor.getSpecialty());
        }
}

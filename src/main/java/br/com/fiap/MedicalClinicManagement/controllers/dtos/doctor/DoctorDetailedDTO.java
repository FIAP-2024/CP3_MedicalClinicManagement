package br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientDetailedDTO;
import br.com.fiap.MedicalClinicManagement.models.Doctor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record DoctorDetailedDTO(
        Long id,
        String name,
        String address,
        String specialty,
        @JsonIgnore
        List<PatientDetailedDTO> patientsList
) {
        public DoctorDetailedDTO(Doctor doctor) {
                this(doctor.getId(), doctor.getName(), doctor.getAddress(), doctor.getSpecialty(),
                        doctor.getPatients().stream().map(PatientDetailedDTO::new).toList());
        }
}

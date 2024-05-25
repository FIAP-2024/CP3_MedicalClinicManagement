package br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientDetailedDTO;
import br.com.fiap.MedicalClinicManagement.models.Doctor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

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

        public Doctor toModel() {
                Doctor doctor = new Doctor();
                doctor.setId(this.id);
                doctor.setName(this.name);
                doctor.setAddress(this.address);
                doctor.setSpecialty(this.specialty);
                // Converta os pacientes de volta se necessário, caso contrário, você pode ignorar essa parte
                doctor.setPatients(this.patientsList.stream().map(PatientDetailedDTO::toModel).collect(Collectors.toList()));
                return doctor;
        }

}

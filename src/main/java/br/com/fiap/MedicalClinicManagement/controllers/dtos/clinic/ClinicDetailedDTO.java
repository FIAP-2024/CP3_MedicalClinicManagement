package br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientDetailedDTO;
import br.com.fiap.MedicalClinicManagement.models.Clinic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public record ClinicDetailedDTO(
        Long id,
        String name,
        String address,
        String phone,
        String email,
        String workingHours,
        String cnpj,
        @JsonIgnore
        List<DoctorDetailedDTO> doctorlist,
        @JsonIgnore
        List <PatientDetailedDTO> patientlist,
        @JsonIgnore
        List<AppointmentDetailedDTO> appointmentlist

) {

    public ClinicDetailedDTO(Clinic clinic) {
        this(clinic.getId(), clinic.getName(),
                clinic.getAddress(), clinic.getPhone(),
                clinic.getEmail(), clinic.getWorkingHours(), clinic.getCnpj(),
                clinic.getDoctors().stream().map(DoctorDetailedDTO::new).toList(),
                clinic.getPatients().stream().map(PatientDetailedDTO::new).toList(),
                clinic.getAppointments().stream().map(AppointmentDetailedDTO::new).toList()
        );
    }

}

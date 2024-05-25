package br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientDetailedDTO;
import br.com.fiap.MedicalClinicManagement.models.Clinic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.stream.Collectors;

public record ClinicDetailedDTO(
        Long id,
        String name,
        String address,
        String phone,
        String email,
        String workingHours,
        String cnpj,
        @JsonIgnore
        List<DoctorDetailedDTO> doctorList,
        @JsonIgnore
        List <PatientDetailedDTO> patientList,
        @JsonIgnore
        List<AppointmentDetailedDTO> appointmentList

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

    public Clinic toModel() {
        Clinic clinic = new Clinic();
        clinic.setId(this.id);
        clinic.setName(this.name);
        clinic.setAddress(this.address);
        clinic.setPhone(this.phone);
        clinic.setEmail(this.email);
        clinic.setWorkingHours(this.workingHours);
        clinic.setCnpj(this.cnpj);
        clinic.setDoctors(this.doctorList.stream().map(DoctorDetailedDTO::toModel).collect(Collectors.toList()));
        clinic.setPatients(this.patientList.stream().map(PatientDetailedDTO::toModel).collect(Collectors.toList()));
        clinic.setAppointments(this.appointmentList.stream().map(AppointmentDetailedDTO::toModel).collect(Collectors.toList()));
        return clinic;
    }

}

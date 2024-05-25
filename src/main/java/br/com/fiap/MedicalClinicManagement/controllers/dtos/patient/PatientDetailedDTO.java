package br.com.fiap.MedicalClinicManagement.controllers.dtos.patient;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorDetailedDTO;
import br.com.fiap.MedicalClinicManagement.models.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record PatientDetailedDTO(
        Long id,
        String name,
        LocalDate birthDate,
        String cpf,
        String rg,
        String address,
        String phone,
        String email,
        String gender,
        String maritalStatus,
        @JsonIgnore
        List<DoctorDetailedDTO> doctorList,
        @JsonIgnore
        List<AppointmentDetailedDTO> appointmentList
) {

    public PatientDetailedDTO(Patient patient) {
        this(patient.getId(), patient.getName(),
                patient.getBirthDate(), patient.getCpf(),
                patient.getRg(), patient.getAddress(), patient.getPhone(),
                patient.getEmail(), patient.getGender(), patient.getMaritalStatus(),
                patient.getDoctors().stream().map(DoctorDetailedDTO::new).toList(),
                patient.getAppointments().stream().map(AppointmentDetailedDTO::new).toList()
        );
    }

    public Patient toModel() {
        Patient patient = new Patient();
        patient.setId(this.id);
        patient.setName(this.name);
        patient.setBirthDate(this.birthDate);
        patient.setCpf(this.cpf);
        patient.setRg(this.rg);
        patient.setAddress(this.address);
        patient.setPhone(this.phone);
        patient.setEmail(this.email);
        patient.setGender(this.gender);
        patient.setMaritalStatus(this.maritalStatus);
        patient.setDoctors(this.doctorList.stream().map(DoctorDetailedDTO::toModel).collect(Collectors.toList()));
        patient.setAppointments(this.appointmentList.stream().map(AppointmentDetailedDTO::toModel).collect(Collectors.toList()));
        return patient;
    }


}

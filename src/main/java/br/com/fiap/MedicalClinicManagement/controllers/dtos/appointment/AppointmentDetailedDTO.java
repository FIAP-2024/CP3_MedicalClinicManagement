package br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientDetailedDTO;
import br.com.fiap.MedicalClinicManagement.models.Appointment;
import br.com.fiap.MedicalClinicManagement.models.Clinic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

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
        String notes,

        List<DoctorDetailedDTO> doctorlist,
        List <PatientDetailedDTO> patientlist,

        List<ClinicDetailedDTO> clinicList
) {
    public AppointmentDetailedDTO(Appointment appointment){
        this(appointment.getId(), appointment.getDateTime(), appointment.getAppointmentType(),
                appointment.getStatus(), appointment.getNotes(),
                appointment.getDoctor().stream().map(DoctorDetailedDTO::new).toList(),
                appointment.getPatient().stream().map(PatientDetailedDTO::new).toList(),
                appointment.getClinic().stream().map(ClinicDetailedDTO::new).toList()
        );
    }
}

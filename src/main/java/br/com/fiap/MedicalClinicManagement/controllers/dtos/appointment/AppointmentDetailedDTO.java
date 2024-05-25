package br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientDetailedDTO;
import br.com.fiap.MedicalClinicManagement.models.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record AppointmentDetailedDTO(
        Long id,

        LocalDateTime dateTime,
        String appointmentType,
        String status,
        String notes,
        DoctorDetailedDTO doctor,
        PatientDetailedDTO patient,
        ClinicDetailedDTO clinic
) {
    public AppointmentDetailedDTO(Appointment appointment){
        this(appointment.getId(), appointment.getDateTime(), appointment.getAppointmentType(),
                appointment.getStatus(), appointment.getNotes(),
                new DoctorDetailedDTO(appointment.getDoctor()),
                new PatientDetailedDTO(appointment.getPatient()),
                new ClinicDetailedDTO(appointment.getClinic()));
    }

    public Appointment toModel() {
        Appointment appointment = new Appointment();
        appointment.setId(this.id);
        appointment.setDateTime(this.dateTime);
        appointment.setAppointmentType(this.appointmentType);
        appointment.setStatus(this.status);
        appointment.setNotes(this.notes);
        appointment.setDoctor(this.doctor.toModel());
        appointment.setPatient(this.patient.toModel());
        appointment.setClinic(this.clinic.toModel());
        return appointment;
    }

}

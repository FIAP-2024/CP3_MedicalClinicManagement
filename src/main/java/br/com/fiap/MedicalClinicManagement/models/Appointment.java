package br.com.fiap.MedicalClinicManagement.models;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_APPOINTMENT")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "dt_created_at")
    private LocalDateTime dateTime;

    @Column(name = "ds_appointmentType")
    private String appointmentType;

    @Column(name = "ds_status")
    private String status;

    @Column(name = "ds_notes")
    private String notes;

    @Column(name = "dt_created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "dt_updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    public Appointment (AppointmentRegisterDTO appointmentRegisterDTO) {
        this.dateTime = appointmentRegisterDTO.dateTime();
        this.appointmentType = appointmentRegisterDTO.appointmentType();
        this.status = appointmentRegisterDTO.status();
        this.notes = appointmentRegisterDTO.notes();
        this.createdAt = LocalDateTime.now();
    }

    public void updateInformation(AppointmentUpdateDTO appointmentUpdateDTO) {
        if (appointmentUpdateDTO.dateTime() != null) {
            this.dateTime = appointmentUpdateDTO.dateTime();
        }

        if (appointmentUpdateDTO.appointmentType() != null) {
            this.appointmentType = appointmentUpdateDTO.appointmentType();
        }

        if (appointmentUpdateDTO.status() != null) {
            this.status = appointmentUpdateDTO.status();
        }

        if (appointmentUpdateDTO.notes() != null) {
            this.notes = appointmentUpdateDTO.notes();
        }

        this.updatedAt = LocalDateTime.now();
    }

}
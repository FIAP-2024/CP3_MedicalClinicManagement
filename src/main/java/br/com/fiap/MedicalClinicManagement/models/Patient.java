package br.com.fiap.MedicalClinicManagement.models;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientUpdateDTO;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_PATIENT")
public class Patient {

    @Id
    @Column(name = "id_patient")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ds_name")
    private String name;

    @Column(name = "ds_birthDate")
    private LocalDate birthDate;

    @Column(name = "ds_cpf")
    private String cpf;

    @Column(name = "ds_rg")
    private String rg;

    @Column(name = "ds_address")
    private String address;

    @Column(name = "ds_phone")
    private String phone;

    @Column(name = "ds_email")
    private String email;

    @Column(name = "ds_gender")
    private String gender;

    @Column(name = "ds_maritalStatus")
    private String maritalStatus;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doctors")
    private List<Doctor> doctors;

    @Column(name = "dt_created_at")
    private LocalDateTime createdAt;

    @Column(name = "dt_updated_at")
    private LocalDateTime updatedAt;

    public Patient(PatientRegisterDTO patientRegisterDTO) {
        this.name = patientRegisterDTO.name();
        this.birthDate = patientRegisterDTO.birthDate();
        this.cpf = patientRegisterDTO.cpf();
        this.rg = patientRegisterDTO.rg();
        this.address = patientRegisterDTO.address();
        this.phone = patientRegisterDTO.phone();
        this.email = patientRegisterDTO.email();
        this.gender = patientRegisterDTO.gender();
        this.maritalStatus = patientRegisterDTO.maritalStatus();
        this.createdAt = LocalDateTime.now();
    }

    public void updateInformation(PatientUpdateDTO patientUpdateDTO) {
        if (patientUpdateDTO.name() != null) {
            this.name = patientUpdateDTO.name();
        }

        if (patientUpdateDTO.birthDate() != null) {
            this.birthDate = patientUpdateDTO.birthDate();
        }

        if (patientUpdateDTO.cpf() != null) {
            this.cpf = patientUpdateDTO.cpf();
        }

        if (patientUpdateDTO.rg() != null) {
            this.rg = patientUpdateDTO.rg();
        }

        if (patientUpdateDTO.address() != null) {
            this.address = patientUpdateDTO.address();
        }

        if (patientUpdateDTO.phone() != null) {
            this.phone = patientUpdateDTO.phone();
        }

        if (patientUpdateDTO.email() != null) {
            this.email = patientUpdateDTO.email();
        }

        if (patientUpdateDTO.gender() != null) {
            this.gender = patientUpdateDTO.gender();
        }

        if (patientUpdateDTO.maritalStatus() != null) {
            this.maritalStatus = patientUpdateDTO.maritalStatus();
        }

        this.updatedAt = LocalDateTime.now();
    }


}

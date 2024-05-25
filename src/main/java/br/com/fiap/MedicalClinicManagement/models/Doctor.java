package br.com.fiap.MedicalClinicManagement.models;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorUpdateDTO;
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
@Table(name = "T_DOCTOR")
public class Doctor {
    @Id
    @Column(name = "id_doctor")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Identificador único do médico (gerado automaticamente)

    @Column(name = "ds_name", nullable = false)
    private String name; // Nome do médico

    @Column(name = "ds_address")
    private String address;

    @Column(name = "ds_specialty")
    private String specialty; // Especialidade do médico

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clinic")
    private Clinic clinic; // Clínica à qual o médico pertence

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "doctor_patient",
            joinColumns = @JoinColumn(name = "id_doctor"),
            inverseJoinColumns = @JoinColumn(name = "id_patient")
    )
    private List<Patient> patients; // Lista de pacientes associados ao médico

    @Column(name = "dt_created_at")
    private LocalDateTime createdAt; // Data de Criação do Médico

    @Column(name = "dt_updated_at")
    private LocalDateTime updatedAt; // Data de Atualização do Médico

    public Doctor(DoctorRegisterDTO doctorRegisterDTO) {
        this.name = doctorRegisterDTO.name();
        this.address = doctorRegisterDTO.address();
        this.specialty = doctorRegisterDTO.specialty();
        this.createdAt = LocalDateTime.now();
    }

    public void updateInformation(DoctorUpdateDTO doctorUpdateDTO) {
        if (doctorUpdateDTO.name() != null) {
            this.name = doctorUpdateDTO.name();
        }

        if (doctorUpdateDTO.address() != null) {
            this.address = doctorUpdateDTO.address();
        }

        if (doctorUpdateDTO.specialty() != null) {
            this.specialty = doctorUpdateDTO.specialty();
        }

        this.updatedAt = LocalDateTime.now();
    }
}

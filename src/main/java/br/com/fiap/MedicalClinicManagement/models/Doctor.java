package br.com.fiap.MedicalClinicManagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "ds_name")
    private String name; // Nome do médico

    @Column(name = "ds_specialty")
    private String specialty; // Especialidade do médico

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic; // Clínica à qual o médico pertence

    @ManyToMany(mappedBy = "doctors")
    private List<Patient> patients; // Lista de pacientes associados ao médico
}

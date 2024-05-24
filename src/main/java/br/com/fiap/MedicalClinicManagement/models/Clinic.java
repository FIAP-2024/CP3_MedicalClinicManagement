package br.com.fiap.MedicalClinicManagement.models;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicUpdateDTO;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_CLINIC")
public class Clinic {

    @Id
    @Column(name = "id_clinic")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ds_name")
    private String name;

    @Column(name = "ds_address")
    private String address;

    @Column(name = "ds_phone")
    private String phone;

    @Column(name = "ds_email")
    private String email;

    @Column(name = "ds_workingHours")
    private String workingHours;

    @Column(name = "ds_cnpj")
    private String cnpj;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Doctor> doctors;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Patient> patients;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @Column(name = "dt_created_at")
    private LocalDateTime createdAt;

    @Column(name = "dt_updated_at")
    private LocalDateTime updatedAt;

    public Clinic(ClinicRegisterDTO clinicRegisterDTO) {
        this.name = clinicRegisterDTO.name();
        this.address = clinicRegisterDTO.address();
        this.phone = clinicRegisterDTO.phone();
        this.email = clinicRegisterDTO.email();
        this.workingHours = clinicRegisterDTO.workingHours();
        this.cnpj = clinicRegisterDTO.cnpj();
        this.createdAt = LocalDateTime.now();
    }

    public void updateInformation(ClinicUpdateDTO clinicUpdateDTO) {
        if (clinicUpdateDTO.name() != null) {
                this.name = clinicUpdateDTO.name();
        }

        if (clinicUpdateDTO.address() != null) {
            this.address = clinicUpdateDTO.address();
        }

        if (clinicUpdateDTO.phone() != null) {
            this.phone = clinicUpdateDTO.phone();
        }

        if (clinicUpdateDTO.email() != null) {
            this.email = clinicUpdateDTO.email();
        }

        if (clinicUpdateDTO.workingHours() != null) {
            this.workingHours = clinicUpdateDTO.workingHours();
        }

        if (clinicUpdateDTO.cnpj() != null) {
            this.cnpj = clinicUpdateDTO.cnpj();
        }

        this.updatedAt = LocalDateTime.now();
    }

}

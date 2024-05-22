package br.com.fiap.MedicalClinicManagement.controllers.dtos.patient;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record PatientUpdateDTO(

        String name,
        LocalDate birthDate,
        String cpf,
        String rg,
        String address,
        String phone,
        String email,
        String gender,
        String maritalStatus

) {
}

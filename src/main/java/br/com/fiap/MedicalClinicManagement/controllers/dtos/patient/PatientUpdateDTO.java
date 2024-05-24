package br.com.fiap.MedicalClinicManagement.controllers.dtos.patient;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record PatientUpdateDTO(
        @NotNull
        Long id,
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

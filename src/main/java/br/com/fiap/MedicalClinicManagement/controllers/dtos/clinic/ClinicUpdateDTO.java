package br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic;

import javax.validation.constraints.NotNull;

public record ClinicUpdateDTO(
        @NotNull
        Long id,
        String name,
        String address,
        String phone,
        String email,
        String workingHours,
        String cnpj

) {
}

package br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic;

public record ClinicUpdateDTO(

        String name,
        String address,
        String phone,
        String email,
        String workingHours,
        String cnpj

) {
}

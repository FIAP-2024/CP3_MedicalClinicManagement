package br.com.fiap.MedicalClinicManagement.services;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicUpdateDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorDetailedDTO;
import br.com.fiap.MedicalClinicManagement.models.Clinic;
import br.com.fiap.MedicalClinicManagement.models.Doctor;
import br.com.fiap.MedicalClinicManagement.repositories.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public ClinicDetailedDTO create(ClinicRegisterDTO clinicRegisterDTO) {
        Clinic clinic = new Clinic(clinicRegisterDTO);
        return new ClinicDetailedDTO(clinicRepository.save(clinic));
    }

    public Page<ClinicDetailedDTO> list(Pageable pagination) {
        return clinicRepository.findByUpdatedAtIsNull(pagination).map(ClinicDetailedDTO::new);
    }

    public ClinicDetailedDTO get(Long id) {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Clinica com o ID: {"+ id +"} Não encontrado na base de dados."));

        return new ClinicDetailedDTO(clinic);
    }

    public ClinicDetailedDTO update(Long id, ClinicUpdateDTO clinicUpdateDTO) {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Clinica com o ID: {"+ id +"} Não encontrado na base de dados."));

        clinic.updateInformation(clinicUpdateDTO);

        clinicRepository.save(clinic);

        return new ClinicDetailedDTO(clinic);
    }

    public void delete(Long id) {
        clinicRepository.deleteById(id);
    }
}

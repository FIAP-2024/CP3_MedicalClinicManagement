package br.com.fiap.MedicalClinicManagement.services;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicUpdateDTO;
import br.com.fiap.MedicalClinicManagement.models.Clinic;
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
        return new ClinicDetailedDTO(clinicRepository.findOneByUpdatedAtIsNullAndId(id));
    }

    public void delete(Long id) {
        clinicRepository.deleteById(id);
    }

    public ClinicDetailedDTO update(Long id, ClinicUpdateDTO clinicUpdateDTO) {
        Clinic clinic = clinicRepository.findOneByUpdatedAtIsNullAndId(id);

        clinic.updateInformation(clinicUpdateDTO);

        clinicRepository.save(clinic);

        return new ClinicDetailedDTO(clinic);
    }


}

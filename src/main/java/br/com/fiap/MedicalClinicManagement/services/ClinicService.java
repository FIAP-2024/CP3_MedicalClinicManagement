package br.com.fiap.MedicalClinicManagement.services;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.ClinicDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.ClinicRegisterDTO;
import br.com.fiap.MedicalClinicManagement.models.Clinic;
import br.com.fiap.MedicalClinicManagement.repositories.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public Clinic create(ClinicRegisterDTO clinicRegisterDTO) {
        Clinic clinic = new Clinic(clinicRegisterDTO);
        return clinicRepository.save(clinic);
    }

    public Page<ClinicDetailedDTO> list(Pageable pagination) {
        return clinicRepository.findByFinishedAtIsNull(pagination).map(ClinicDetailedDTO::new);
    }

    public ClinicDetailedDTO get(Long id) {
        return new ClinicDetailedDTO(clinicRepository.findOneByFinishedAtIsNullAndIdAddress(id));
    }

    public void delete(Long id) {
        Clinic address = addressRepository.findOneByFinishedAtIsNullAndIdAddress(id);

        address.disable();

        addressRepository.save(address);
    }

    public AddressDetailedDTO update(Long id, AddressUpdateDTO addressUpdateDTO) {
        Address address = addressRepository.findOneByFinishedAtIsNullAndIdAddress(id);

        address.updateInformation(addressUpdateDTO);

        addressRepository.save(address);

        return new AddressDetailedDTO(address);
    }


}

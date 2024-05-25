package br.com.fiap.MedicalClinicManagement.services;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientUpdateDTO;
import br.com.fiap.MedicalClinicManagement.models.Patient;
import br.com.fiap.MedicalClinicManagement.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    public PatientDetailedDTO create(PatientRegisterDTO patientRegisterDTO) {
        Patient patient = new Patient(patientRegisterDTO);
        return new PatientDetailedDTO(patientRepository.save(patient));
    }

    public Page<PatientDetailedDTO> list(Pageable pagination) {
        return patientRepository.findByUpdatedAtIsNull(pagination).map(PatientDetailedDTO::new);
    }

    public PatientDetailedDTO get(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Paciente com o ID: {"+ id +"} Não encontrado na base de dados."));

        return new PatientDetailedDTO(patient);
    }

    public PatientDetailedDTO update(Long id, PatientUpdateDTO patientUpdateDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Paciente com o ID: {"+ id +"} Não encontrado na base de dados."));

        patient.updateInformation(patientUpdateDTO);

        patientRepository.save(patient);

        return new PatientDetailedDTO(patient);
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }


}

package br.com.fiap.MedicalClinicManagement.services;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorUpdateDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientUpdateDTO;
import br.com.fiap.MedicalClinicManagement.models.Doctor;
import br.com.fiap.MedicalClinicManagement.models.Patient;
import br.com.fiap.MedicalClinicManagement.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorDetailedDTO create(DoctorRegisterDTO doctorRegisterDTO) {
        Doctor doctor = new Doctor(doctorRegisterDTO);
        return new DoctorDetailedDTO(doctorRepository.save(doctor));
    }

    public Page<DoctorDetailedDTO> list(Pageable pagination) {
        return doctorRepository.findByUpdatedAtIsNull(pagination).map(DoctorDetailedDTO::new);
    }

    public DoctorDetailedDTO get(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Doutor com o ID: {"+ id +"} Não encontrado na base de dados."));

        return new DoctorDetailedDTO(doctor);
    }

    public DoctorDetailedDTO update(Long id, DoctorUpdateDTO doctorUpdateDTO) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Doutor com o ID: {"+ id +"} Não encontrado na base de dados."));

        doctor.updateInformation(doctorUpdateDTO);

        doctorRepository.save(doctor);

        return new DoctorDetailedDTO(doctor);
    }

    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }
}

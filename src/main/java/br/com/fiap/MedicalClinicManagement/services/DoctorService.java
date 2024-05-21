package br.com.fiap.MedicalClinicManagement.services;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorUpdateDTO;
import br.com.fiap.MedicalClinicManagement.models.Doctor;
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

    public Doctor create(DoctorRegisterDTO doctorRegisterDTO) {
        Doctor doctor = new Doctor(doctorRegisterDTO);
        return doctorRepository.save(doctor);
    }

    public Page<DoctorDetailedDTO> list(Pageable pagination) {
        return doctorRepository.findByFinishedAtIsNull(pagination).map(DoctorDetailedDTO::new);
    }

    public DoctorDetailedDTO get(Long id) {
        return new DoctorDetailedDTO(doctorRepository.findOneByFinishedAtIsNullAndIdDoctor(id));
    }

    public DoctorDetailedDTO update(Long id, DoctorUpdateDTO doctorUpdateDTO) {
        Doctor doctor = doctorRepository.findOneByFinishedAtIsNullAndIdDoctor(id);

        doctor.updateInformation(doctorUpdateDTO);

        doctorRepository.save(doctor);

        return new DoctorDetailedDTO(doctor);
    }

    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }
}

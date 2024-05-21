package br.com.fiap.MedicalClinicManagement.repositories;

import br.com.fiap.MedicalClinicManagement.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findByFinishedAtIsNull(Pageable pagination);

    Doctor findOneByFinishedAtIsNullAndIdDoctor(Long idDoctor);
}

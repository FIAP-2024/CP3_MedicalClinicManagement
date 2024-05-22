package br.com.fiap.MedicalClinicManagement.repositories;

import br.com.fiap.MedicalClinicManagement.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByFinishedAtIsNull(Pageable pagination);

    Patient findOneByFinishedAtIsNullAndIdPatient(Long idPatient);
}

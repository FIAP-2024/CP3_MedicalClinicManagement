package br.com.fiap.MedicalClinicManagement.repositories;

import br.com.fiap.MedicalClinicManagement.models.Clinic;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    Page<Clinic> findByFinishedAtIsNull(Pageable pagination);

    Clinic findOneByFinishedAtIsNullAndIdClinic(Long idClinic);

}

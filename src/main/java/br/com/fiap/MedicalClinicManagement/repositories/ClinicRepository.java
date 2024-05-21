package br.com.fiap.MedicalClinicManagement.repositories;

import br.com.fiap.MedicalClinicManagement.models.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}

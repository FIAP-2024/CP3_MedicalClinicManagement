package br.com.fiap.MedicalClinicManagement.services;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentUpdateDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientDetailedDTO;
import br.com.fiap.MedicalClinicManagement.models.Appointment;
import br.com.fiap.MedicalClinicManagement.models.Doctor;
import br.com.fiap.MedicalClinicManagement.models.Patient;
import br.com.fiap.MedicalClinicManagement.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    private final DoctorService doctorService;

    private final PatientService patientService;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, DoctorService doctorService, PatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @Transactional
    public AppointmentDetailedDTO create(AppointmentRegisterDTO appointmentRegisterDTO) {

        Appointment appointment = new Appointment(appointmentRegisterDTO);

        Doctor doctor = doctorService.get(appointmentRegisterDTO.idDoctors()).toModel();

        Patient patient = patientService.get(appointmentRegisterDTO.idPatients()).toModel();

        appointment.setDoctor(doctor);

        appointment.setPatient(patient);

        return new AppointmentDetailedDTO(appointmentRepository.save(appointment));
    }

    public Page<AppointmentDetailedDTO> list(Pageable pagination) {
        return appointmentRepository.findByUpdatedAtIsNull(pagination).map(AppointmentDetailedDTO::new);
    }

    public AppointmentDetailedDTO get(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Consulta com o ID: {"+ id +"} Não encontrado na base de dados."));
        return new AppointmentDetailedDTO(appointment);
    }

    @Transactional
    public AppointmentDetailedDTO update(Long id, AppointmentUpdateDTO appointmentUpdateDTO) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Consulta com o ID: {"+ id +"} Não encontrado na base de dados."));

        appointment.updateInformation(appointmentUpdateDTO);

        appointmentRepository.save(appointment);

        return new AppointmentDetailedDTO(appointment);
    }

    @Transactional
    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}

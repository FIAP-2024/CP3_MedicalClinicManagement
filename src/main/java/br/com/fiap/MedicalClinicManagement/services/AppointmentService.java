package br.com.fiap.MedicalClinicManagement.services;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentUpdateDTO;
import br.com.fiap.MedicalClinicManagement.models.Appointment;
import br.com.fiap.MedicalClinicManagement.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public AppointmentDetailedDTO create(AppointmentRegisterDTO appointmentRegisterDTO) {
        Appointment appointment = new Appointment(appointmentRegisterDTO);
        return new AppointmentDetailedDTO(appointmentRepository.save(appointment));
    }

    public Page<AppointmentDetailedDTO> list(Pageable pagination) {
        return appointmentRepository.findByUpdatedAtIsNull(pagination).map(AppointmentDetailedDTO::new);
    }

    public AppointmentDetailedDTO get(Long id) {
        return new AppointmentDetailedDTO(appointmentRepository.findOneByUpdatedAtIsNullAndId(id));
    }

    public AppointmentDetailedDTO update(Long id, AppointmentUpdateDTO appointmentUpdateDTO) {
        Appointment appointment = appointmentRepository.findOneByUpdatedAtIsNullAndId(id);

        appointment.updateInformation(appointmentUpdateDTO);

        appointmentRepository.save(appointment);

        return new AppointmentDetailedDTO(appointment);
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}

package br.com.fiap.MedicalClinicManagement.controllers.appointment;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentUpdateDTO;
import br.com.fiap.MedicalClinicManagement.models.Appointment;
import br.com.fiap.MedicalClinicManagement.services.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/public/appointments")
@Profile("dev")
public class AppointmentDevController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentDevController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid AppointmentRegisterDTO appointmentRegisterDTO, UriComponentsBuilder uriBuilder) {
        Appointment appointment = appointmentService.create(appointmentRegisterDTO);

        URI uri = uriBuilder.path("/api/dev/{id}").buildAndExpand(appointment.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentDetailedDTO>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<AppointmentDetailedDTO> page = appointmentService.list(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDetailedDTO> find(@PathVariable("id") Long id) {
        AppointmentDetailedDTO appointmentDetailedDTO = appointmentService.get(id);
        return ResponseEntity.ok(appointmentDetailedDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDetailedDTO> update(@PathVariable("id") Long id, @RequestBody AppointmentUpdateDTO appointmentUpdateDTO) {
        AppointmentDetailedDTO appointmentDetailedDTO = appointmentService.update(id, appointmentUpdateDTO);
        return ResponseEntity.ok(appointmentDetailedDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

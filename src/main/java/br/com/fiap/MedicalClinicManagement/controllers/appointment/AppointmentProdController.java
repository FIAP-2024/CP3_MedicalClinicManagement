package br.com.fiap.MedicalClinicManagement.controllers.appointment;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.appointment.AppointmentUpdateDTO;
import br.com.fiap.MedicalClinicManagement.models.Appointment;
import br.com.fiap.MedicalClinicManagement.services.AppointmentService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("api/prod/appointments")
@Profile("prod")
public class AppointmentProdController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentProdController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<AppointmentDetailedDTO>> create(@RequestBody @Valid AppointmentRegisterDTO appointmentRegisterDTO,
                                                                      UriComponentsBuilder uriBuilder) {
        AppointmentDetailedDTO appointment = appointmentService.create(appointmentRegisterDTO);

        EntityModel<AppointmentDetailedDTO> appointmentModel = EntityModel.of(appointment);

        Link selfLink = linkTo(methodOn(AppointmentProdController.class).find(appointment.id())).withSelfRel();
        Link appointmentsLink = linkTo(methodOn(AppointmentProdController.class).list(Pageable.unpaged())).withRel("all-appointments");
        appointmentModel.add(selfLink, appointmentsLink);

        URI location = uriBuilder.path("/api/prod/appointments/{id}").buildAndExpand(appointment.id()).toUri();
        return ResponseEntity.created(location).body(appointmentModel);
    }

    @GetMapping
    public ResponseEntity<Page<EntityModel<AppointmentDetailedDTO>>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<AppointmentDetailedDTO> page = appointmentService.list(pagination);

        Page<EntityModel<AppointmentDetailedDTO>> appointmentModels = page.map(appointmentDetailedDTO -> {
            EntityModel<AppointmentDetailedDTO> appointmentModel = EntityModel.of(appointmentDetailedDTO);
            Link selfLink = linkTo(methodOn(AppointmentProdController.class).find(appointmentDetailedDTO.id())).withSelfRel();
            appointmentModel.add(selfLink);
            return appointmentModel;
        });

        return ResponseEntity.ok(appointmentModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<AppointmentDetailedDTO>> find(@PathVariable("id") Long id) {
        AppointmentDetailedDTO appointmentDetailedDTO = appointmentService.get(id);

        EntityModel<AppointmentDetailedDTO> appointmentModel = EntityModel.of(appointmentDetailedDTO);
        Link selfLink = linkTo(methodOn(AppointmentProdController.class).find(id)).withSelfRel();
        appointmentModel.add(selfLink);

        return ResponseEntity.ok(appointmentModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<AppointmentDetailedDTO>> update(@PathVariable("id") Long id, @RequestBody @Valid AppointmentUpdateDTO appointmentUpdateDTO) {
        AppointmentDetailedDTO appointmentDetailedDTO = appointmentService.update(id, appointmentUpdateDTO);

        EntityModel<AppointmentDetailedDTO> appointmentModel = EntityModel.of(appointmentDetailedDTO);
        Link selfLink = linkTo(methodOn(AppointmentProdController.class).find(id)).withSelfRel();
        appointmentModel.add(selfLink);

        return ResponseEntity.ok(appointmentModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

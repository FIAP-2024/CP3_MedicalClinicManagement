package br.com.fiap.MedicalClinicManagement.controllers.patient;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientUpdateDTO;
import br.com.fiap.MedicalClinicManagement.services.PatientService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/public/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<PatientDetailedDTO>> create(@RequestBody @Valid PatientRegisterDTO patientRegisterDTO,
                                                                  UriComponentsBuilder uriBuilder) {
        PatientDetailedDTO patient = patientService.create(patientRegisterDTO);

        EntityModel<PatientDetailedDTO> patientModel = EntityModel.of(patient);

        Link selfLink = linkTo(methodOn(PatientController.class).find(patient.id())).withSelfRel();
        Link appointmentsLink = linkTo(methodOn(PatientController.class).list(Pageable.unpaged())).withRel("all-patients");
        patientModel.add(selfLink, appointmentsLink);

        URI location = uriBuilder.path("/api/public/patients/{id}").buildAndExpand(patient.id()).toUri();
        return ResponseEntity.created(location).body(patientModel);
    }

    @GetMapping
    public ResponseEntity<Page<EntityModel<PatientDetailedDTO>>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<PatientDetailedDTO> page = patientService.list(pagination);

        Page<EntityModel<PatientDetailedDTO>> entityModels = page.map(patientDetailedDTO -> {
            EntityModel<PatientDetailedDTO> entityModel = EntityModel.of(patientDetailedDTO);
            Link selfLink = linkTo(methodOn(PatientController.class).find(patientDetailedDTO.id())).withSelfRel();
            entityModel.add(selfLink);
            return entityModel;
        });

        return ResponseEntity.ok(entityModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PatientDetailedDTO>> find(@PathVariable("id") Long id) {
        PatientDetailedDTO patientDetailedDTO = patientService.get(id);

        EntityModel<PatientDetailedDTO> entityModel = EntityModel.of(patientDetailedDTO);
        Link selfLink = linkTo(methodOn(PatientController.class).find(id)).withSelfRel();
        entityModel.add(selfLink);

        return ResponseEntity.ok(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<PatientDetailedDTO>> update(@PathVariable("id") Long id, @RequestBody @Valid PatientUpdateDTO patientUpdateDTO) {
        PatientDetailedDTO patientDetailedDTO = patientService.update(id, patientUpdateDTO);

        EntityModel<PatientDetailedDTO> entityModel = EntityModel.of(patientDetailedDTO);
        Link selfLink = linkTo(methodOn(PatientController.class).find(id)).withSelfRel();
        entityModel.add(selfLink);

        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

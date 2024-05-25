package br.com.fiap.MedicalClinicManagement.controllers.clinic;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicUpdateDTO;
import br.com.fiap.MedicalClinicManagement.models.Clinic;
import br.com.fiap.MedicalClinicManagement.services.ClinicService;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
@RequestMapping("/api/dev/clinics")
@Profile("dev")
public class ClinicDevController {

    private final ClinicService clinicService;

    @Autowired
    public ClinicDevController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<ClinicDetailedDTO>> create(@RequestBody @Valid ClinicRegisterDTO clinicRegisterDTO,
                                                                 UriComponentsBuilder uriBuilder) {
        ClinicDetailedDTO clinic = clinicService.create(clinicRegisterDTO);

        EntityModel<ClinicDetailedDTO> clinicModel = EntityModel.of(clinic);

        Link selfLink = linkTo(methodOn(ClinicDevController.class).find(clinic.id())).withSelfRel();
        Link clinicsLink = linkTo(methodOn(ClinicDevController.class).list(Pageable.unpaged())).withRel("all-clinics");
        clinicModel.add(selfLink, clinicsLink);

        URI location = uriBuilder.path("/api/dev/clinic/{id}").buildAndExpand(clinic.id()).toUri();
        return ResponseEntity.created(location).body(clinicModel);
    }

    @GetMapping
    public ResponseEntity<Page<EntityModel<ClinicDetailedDTO>>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<ClinicDetailedDTO> page = clinicService.list(pagination);

        Page<EntityModel<ClinicDetailedDTO>> clinicModels = page.map(clinicDetailedDTO -> {
            EntityModel<ClinicDetailedDTO> clinicModel = EntityModel.of(clinicDetailedDTO);
            Link selfLink = linkTo(methodOn(ClinicDevController.class).find(clinicDetailedDTO.id())).withSelfRel();
            clinicModel.add(selfLink);
            return clinicModel;
        });

        return ResponseEntity.ok(clinicModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ClinicDetailedDTO>> find(@PathVariable("id") Long id) {
        ClinicDetailedDTO clinicDetailedDTO = clinicService.get(id);

        EntityModel<ClinicDetailedDTO> clinicModel = EntityModel.of(clinicDetailedDTO);
        Link selfLink = linkTo(methodOn(ClinicDevController.class).find(id)).withSelfRel();
        clinicModel.add(selfLink);

        return ResponseEntity.ok(clinicModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ClinicDetailedDTO>> update(@PathVariable("id") Long id, @RequestBody @Valid ClinicUpdateDTO clinicUpdateDTO) {
        ClinicDetailedDTO clinicDetailedDTO = clinicService.update(id, clinicUpdateDTO);

        EntityModel<ClinicDetailedDTO> clinicModel = EntityModel.of(clinicDetailedDTO);
        Link selfLink = linkTo(methodOn(ClinicDevController.class).find(id)).withSelfRel();
        clinicModel.add(selfLink);

        return ResponseEntity.ok(clinicModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        clinicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

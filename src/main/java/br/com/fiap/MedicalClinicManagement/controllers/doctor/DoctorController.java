package br.com.fiap.MedicalClinicManagement.controllers.doctor;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorUpdateDTO;
import br.com.fiap.MedicalClinicManagement.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("api/public/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<DoctorDetailedDTO>> create(@RequestBody @Valid DoctorRegisterDTO doctorRegisterDTO,
                                                                 UriComponentsBuilder uriBuilder) {
        DoctorDetailedDTO doctor = doctorService.create(doctorRegisterDTO);

        EntityModel<DoctorDetailedDTO> doctorModel = EntityModel.of(doctor);

        Link selfLink = linkTo(methodOn(DoctorController.class).find(doctor.id())).withSelfRel();
        Link doctorsLink = linkTo(methodOn(DoctorController.class).list(Pageable.unpaged())).withRel("all-doctors");
        doctorModel.add(selfLink, doctorsLink);

        URI location = uriBuilder.path("/api/public/doctors/{id}").buildAndExpand(doctor.id()).toUri();
        return ResponseEntity.created(location).body(doctorModel);
    }

    @GetMapping
    public ResponseEntity<Page<EntityModel<DoctorDetailedDTO>>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<DoctorDetailedDTO> page = doctorService.list(pagination);

        Page<EntityModel<DoctorDetailedDTO>> doctorModels = page.map(doctorDetailedDTO -> {
            EntityModel<DoctorDetailedDTO> doctorModel = EntityModel.of(doctorDetailedDTO);
            Link selfLink = linkTo(methodOn(DoctorController.class).find(doctorDetailedDTO.id())).withSelfRel();
            doctorModel.add(selfLink);
            return doctorModel;
        });

        return ResponseEntity.ok(doctorModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<DoctorDetailedDTO>> find(@PathVariable("id") Long id) {
        DoctorDetailedDTO doctorDetailedDTO = doctorService.get(id);

        EntityModel<DoctorDetailedDTO> doctorModel = EntityModel.of(doctorDetailedDTO);
        Link selfLink = linkTo(methodOn(DoctorController.class).find(id)).withSelfRel();
        doctorModel.add(selfLink);

        return ResponseEntity.ok(doctorModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<DoctorDetailedDTO>> update(@PathVariable("id") Long id, @RequestBody @Valid DoctorUpdateDTO doctorUpdateDTO) {
        DoctorDetailedDTO doctorDetailedDTO = doctorService.update(id, doctorUpdateDTO);

        EntityModel<DoctorDetailedDTO> doctorModel = EntityModel.of(doctorDetailedDTO);
        Link selfLink = linkTo(methodOn(DoctorController.class).find(id)).withSelfRel();
        doctorModel.add(selfLink);

        return ResponseEntity.ok(doctorModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

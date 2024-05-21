package br.com.fiap.MedicalClinicManagement.controllers.doctor;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.doctor.DoctorUpdateDTO;
import br.com.fiap.MedicalClinicManagement.models.Doctor;
import br.com.fiap.MedicalClinicManagement.services.DoctorService;
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
@RequestMapping("api/prod/doctors")
@Profile("prod")
public class DoctorProdController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorProdController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid DoctorRegisterDTO doctorRegisterDTO, UriComponentsBuilder uriBuilder) {
        Doctor doctor = doctorService.create(doctorRegisterDTO);

        URI uri = uriBuilder.path("/api/address/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<DoctorDetailedDTO>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<DoctorDetailedDTO> page = doctorService.list(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailedDTO> find(@PathVariable("id") Long id) {
        DoctorDetailedDTO doctorDetailedDTO = doctorService.get(id);
        return ResponseEntity.ok(doctorDetailedDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDetailedDTO> update(@PathVariable("id") Long id, @RequestBody DoctorUpdateDTO doctorUpdateDTO) {
        DoctorDetailedDTO doctorDetailedDTO = doctorService.update(id, doctorUpdateDTO);
        return ResponseEntity.ok(doctorDetailedDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

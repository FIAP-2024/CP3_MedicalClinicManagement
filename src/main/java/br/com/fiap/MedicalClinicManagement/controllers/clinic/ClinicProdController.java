package br.com.fiap.MedicalClinicManagement.controllers.clinic;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.clinic.ClinicUpdateDTO;
import br.com.fiap.MedicalClinicManagement.models.Clinic;
import br.com.fiap.MedicalClinicManagement.services.ClinicService;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Pageable;
import java.net.URI;

@RestController
@RequestMapping("/api/prod/clinic")
@Profile("prod")
public class ClinicProdController {

    private final ClinicService clinicService;

    @Autowired
    public ClinicProdController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ClinicRegisterDTO clinicRegisterDTO,
                                    UriComponentsBuilder uriBuilder)  {
        Clinic clinic = clinicService.create(clinicRegisterDTO);

        URI uri = uriBuilder.path("/api/prod/clinic/{id}").buildAndExpand(clinic.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<ClinicDetailedDTO>> list(@PageableDefault(size = 10, page = 0)
                                                        Pageable pagination) {
        Page<ClinicDetailedDTO> page = clinicService.list(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicDetailedDTO> find(@PathVariable("id") Long id) {
        ClinicDetailedDTO clinicDetailedDTO = clinicService.get(id);
        return ResponseEntity.ok(clinicDetailedDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        clinicService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicDetailedDTO> update(@PathVariable("id") Long id,
                                                    @RequestBody ClinicUpdateDTO clinicUpdateDTO) {
        ClinicDetailedDTO clinicDetailedDTO = clinicService.update(id, clinicUpdateDTO);
        return ResponseEntity.ok(clinicDetailedDTO);
    }

}

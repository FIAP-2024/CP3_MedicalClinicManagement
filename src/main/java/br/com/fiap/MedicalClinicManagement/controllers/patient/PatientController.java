package br.com.fiap.MedicalClinicManagement.controllers.patient;

import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientDetailedDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientRegisterDTO;
import br.com.fiap.MedicalClinicManagement.controllers.dtos.patient.PatientUpdateDTO;
import br.com.fiap.MedicalClinicManagement.models.Patient;
import br.com.fiap.MedicalClinicManagement.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/public/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid PatientRegisterDTO doctorRegisterDTO,
                                    UriComponentsBuilder uriBuilder) {
        Patient patient = patientService.create(doctorRegisterDTO);

        URI uri = uriBuilder.path("/api/public/patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<PatientDetailedDTO>> list(@PageableDefault(size = 10, page = 0) Pageable pagination) {
        Page<PatientDetailedDTO> page = patientService.list(pagination);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailedDTO> find(@PathVariable("id") Long id) {
        PatientDetailedDTO patientDetailedDTO = patientService.get(id);
        return ResponseEntity.ok(patientDetailedDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDetailedDTO> update(@PathVariable("id") Long id, @RequestBody PatientUpdateDTO patientUpdateDTO) {
        PatientDetailedDTO patientDetailedDTO = patientService.update(id, patientUpdateDTO);
        return ResponseEntity.ok(patientDetailedDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

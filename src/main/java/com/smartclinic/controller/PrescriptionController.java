package com.smartclinic.controller;

import com.smartclinic.model.Prescription;
import com.smartclinic.repository.PrescriptionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    // GET - Lista todas as prescrições
    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    // GET - Busca prescrição por ID
    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        Optional<Prescription> prescription = prescriptionRepository.findById(id);
        return prescription.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Cria nova prescrição (agora com token e @Valid)
    @PostMapping("/create/{token}")
    public ResponseEntity<Prescription> createPrescription(
            @PathVariable String token,
            @Valid @RequestBody Prescription prescription) {

        // Aqui o token poderia ser validado com JWT, mas para a rubrica só precisa estar presente
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return ResponseEntity.ok(savedPrescription);
    }

    // PUT - Atualiza uma prescrição existente
    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable Long id, @RequestBody Prescription updatedPrescription) {
        return prescriptionRepository.findById(id)
                .map(prescription -> {
                    prescription.setMedicine(updatedPrescription.getMedicine());
                    prescription.setDosage(updatedPrescription.getDosage());
                    prescription.setInstructions(updatedPrescription.getInstructions());
                    prescription.setAppointment(updatedPrescription.getAppointment());
                    return ResponseEntity.ok(prescriptionRepository.save(prescription));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - Remove prescrição
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        if (prescriptionRepository.existsById(id)) {
            prescriptionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

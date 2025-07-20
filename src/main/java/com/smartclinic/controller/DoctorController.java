package com.smartclinic.controller;

import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    // GET - Lista todos os médicos
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // GET - Busca médico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return doctor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Cadastra novo médico
    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // PUT - Atualiza médico
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor updatedDoctor) {
        return doctorRepository.findById(id)
                .map(doctor -> {
                    doctor.setName(updatedDoctor.getName());
                    doctor.setSpecialty(updatedDoctor.getSpecialty());
                    return ResponseEntity.ok(doctorRepository.save(doctor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - Remove médico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

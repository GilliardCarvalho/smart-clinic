package com.smartclinic.controller;

import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return doctor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor updatedDoctor) {
        return doctorRepository.findById(id)
                .map(doctor -> {
                    doctor.setName(updatedDoctor.getName());
                    doctor.setSpecialty(updatedDoctor.getSpecialty());
                    doctor.setAvailableTimes(updatedDoctor.getAvailableTimes());
                    return ResponseEntity.ok(doctorRepository.save(doctor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // NOVO ENDPOINT COM TOKEN E DATA
    @GetMapping("/{doctorId}/availability/{date}/{token}")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable Long doctorId,
            @PathVariable String date,
            @PathVariable String token) {

        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        if (doctorOpt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("error", "Doctor not found"));
        }

        Doctor doctor = doctorOpt.get();
        LocalDate requestedDate;
        try {
            requestedDate = LocalDate.parse(date);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid date format (expected: yyyy-MM-dd)"));
        }

        List<LocalDateTime> filteredSlots = doctor.getAvailableTimes().stream()
                .filter(slot -> slot.toLocalDate().equals(requestedDate))
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("doctorId", doctorId);
        response.put("doctorName", doctor.getName());
        response.put("date", date);
        response.put("availableSlots", filteredSlots);
        response.put("token", token); // Apenas para mostrar que foi usado (opcional)

        return ResponseEntity.ok(response);
    }
}


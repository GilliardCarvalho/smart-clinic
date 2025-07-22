package com.smartclinic.service;

import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Lista todos os médicos
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Busca um médico pelo ID
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // Cria um novo médico
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Atualiza um médico existente
    public Optional<Doctor> updateDoctor(Long id, Doctor updatedDoctor) {
        return doctorRepository.findById(id).map(doctor -> {
            doctor.setName(updatedDoctor.getName());
            doctor.setSpecialty(updatedDoctor.getSpecialty());
            doctor.setAvailableTimes(updatedDoctor.getAvailableTimes());
            return doctorRepository.save(doctor);
        });
    }

    // Remove um médico
    public boolean deleteDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // NOVO - Retorna os horários disponíveis do médico
    public List<LocalDateTime> getAvailability(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .map(Doctor::getAvailableTimes)
                .orElse(Collections.emptyList());
    }

    // OPCIONAL - Simula validação de login do médico (caso precise)
    public boolean validateLogin(String email, String password) {
        // Simulação de validação: ajustar conforme seu modelo tiver campo de autenticação
        // return doctorRepository.findByEmailAndPassword(email, password).isPresent();
        return true;
    }
}

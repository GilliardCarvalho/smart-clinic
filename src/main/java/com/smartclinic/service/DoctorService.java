package com.smartclinic.service;

import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

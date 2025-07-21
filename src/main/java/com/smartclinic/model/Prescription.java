package com.smartclinic.model;

import jakarta.persistence.*;

@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medicine;
    private String dosage;
    private String instructions;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    public Prescription() {}

    public Prescription(Long id, String medicine, String dosage, String instructions, Appointment appointment) {
        this.id = id;
        this.medicine = medicine;
        this.dosage = dosage;
        this.instructions = instructions;
        this.appointment = appointment;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMedicine() { return medicine; }
    public void setMedicine(String medicine) { this.medicine = medicine; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }
}

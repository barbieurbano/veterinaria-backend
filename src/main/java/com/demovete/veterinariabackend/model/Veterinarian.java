package com.demovete.veterinariabackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Veterinarian")
public class Veterinarian {
    //1. Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer licenseNumber;
    private String email;
    private Integer phone;

    @Enumerated(EnumType.STRING)
    private VetSpecialists specialty;

    //2. Constructores
    public Veterinarian() {}

    public Veterinarian(String firstName, String lastName, Integer licenseNumber, String email, Integer phone, VetSpecialists specialty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
        this.email = email;
        this.phone = phone;
        this.specialty = specialty;
    }

    //3. Getter y Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Integer licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public VetSpecialists getSpecialty() {
        return specialty;
    }

    public void setSpecialty(VetSpecialists specialty) {
        this.specialty = specialty;
    }

    //4. toString
    @Override
    public String toString() {
        return "Veterinarian{" +
                "id=" + id +
                ", licenseNumber=" + licenseNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", specialty=" + specialty +
                '}';
    }

}

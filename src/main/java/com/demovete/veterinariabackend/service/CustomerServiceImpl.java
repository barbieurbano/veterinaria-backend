package com.demovete.veterinariabackend.service;

import com.demovete.veterinariabackend.dto.CustomerCreateRequestDTO;
import com.demovete.veterinariabackend.dto.CustomerResponseDTO;
import com.demovete.veterinariabackend.dto.CustomerUpdateRequestDTO;
import com.demovete.veterinariabackend.model.Customer;
import com.demovete.veterinariabackend.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerResponseDTO createCustomer(CustomerCreateRequestDTO dto){
        if (customerRepository.existsByEmail(dto.email())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Ya existe un cliente con ese email: " + dto.email());
    }
        if (dto.documentNumber() != null && !dto.documentNumber().isBlank()
                && customerRepository.existsByDocumentNumber(dto.documentNumber())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Ya existe un cliente con ese documento: " + dto.documentNumber());
        }
        Customer customer = Customer.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .phone(dto.phone())
                .documentNumber(dto.documentNumber())
                .photoUrl(dto.photoUrl())
                .build();

        return mapToDto(customerRepository.save(customer));
}
    @Override
    @Transactional
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no encontrado con ID: " + id));
        return mapToDto(c);
    }

    @Override
    @Transactional
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerRepository.findByActiveTrue()
                .stream()
                .map(this::mapToDto)
                .toList();
    }
    @Override
    @Transactional
    public CustomerResponseDTO updateCustomer(Long id, CustomerUpdateRequestDTO dto) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no encontrado con ID: " + id));

        if (dto.firstName() != null)      c.setFirstName(dto.firstName());
        if (dto.lastName() != null)       c.setLastName(dto.lastName());
        if (dto.email() != null)          c.setEmail(dto.email());
        if (dto.phone() != null)          c.setPhone(dto.phone());
        if (dto.documentNumber() != null) c.setDocumentNumber(dto.documentNumber());
        if (dto.photoUrl() != null)       c.setPhotoUrl(dto.photoUrl());
        if (dto.active() != null)         c.setActive(dto.active());

        return mapToDto(customerRepository.save(c));
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente no encontrado con ID: " + id));
        c.setActive(false);
        customerRepository.save(c);
    }

    private CustomerResponseDTO mapToDto(Customer c) {
        return new CustomerResponseDTO(
                c.getId(),
                c.getFirstName(),
                c.getLastName(),
                c.getEmail(),
                c.getPhone(),
                c.getDocumentNumber(),
                c.getPhotoUrl(),
                c.getActive()
        );
    }
}

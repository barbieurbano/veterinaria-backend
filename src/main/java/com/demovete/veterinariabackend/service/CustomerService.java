package com.demovete.veterinariabackend.service;

import com.demovete.veterinariabackend.dto.CustomerCreateRequestDTO;
import com.demovete.veterinariabackend.dto.CustomerResponseDTO;
import com.demovete.veterinariabackend.dto.CustomerUpdateRequestDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO createCustomer(CustomerCreateRequestDTO dto);
    CustomerResponseDTO getCustomerById(Long id);
    List<CustomerResponseDTO> getAllCustomers();
    CustomerResponseDTO updateCustomer(Long id, CustomerUpdateRequestDTO dto);

    void deleteCustomer(Long id);
}

package com.demovete.veterinariabackend.api;

import com.demovete.veterinariabackend.dto.CustomerCreateRequestDTO;
import com.demovete.veterinariabackend.dto.CustomerResponseDTO;
import com.demovete.veterinariabackend.dto.CustomerUpdateRequestDTO;
import com.demovete.veterinariabackend.dto.PetResponseDTO;
import com.demovete.veterinariabackend.service.CustomerService;
import com.demovete.veterinariabackend.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerRestController {
    private final CustomerService customerService;
    private final PetService petService;

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerCreateRequestDTO dto){
        CustomerResponseDTO created = customerService.createCustomer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<CustomerResponseDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("{id}")
    public CustomerResponseDTO getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    //Tambien se podia pedir en el petRestController pero de esta forma queda natural.
    @GetMapping("{id}/pets")
    public List<PetResponseDTO> getCustomerPets(@PathVariable Long id){
        return petService.getPetsByCustomerId(id);
    }

    @PutMapping("{id}")
    public CustomerResponseDTO updateCustomer(@PathVariable Long id,
                                              @Valid @RequestBody CustomerUpdateRequestDTO dto){
        return customerService.updateCustomer(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }

}

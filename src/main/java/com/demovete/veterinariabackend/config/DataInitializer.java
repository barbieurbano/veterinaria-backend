package com.demovete.veterinariabackend.config;

import com.demovete.veterinariabackend.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Profile("!test")
public class DataInitializer implements CommandLineRunner {
    private AnimalRepository animalRepo;

    @Override
    public void run(String... args) throws Exception{
        if (animalRepo.count() > 0) return;

    }
}

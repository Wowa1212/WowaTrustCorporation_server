package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@DataJpaTest
@ActiveProfiles("test") // Aktivuje testovací profil
public class InsuredRepositoryTest {

    @Autowired
    private InsuredRepository insuredRepository;

    @BeforeEach
    void setUp() {
        // Vložíme testovací data do databáze před každým testem
        insuredRepository.save(new Insured(0, "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "123456789", "987654321"));
        insuredRepository.save(new Insured(0, "Jane", "Smith", LocalDate.of(1985, 5, 15), "456 Oak St", "987654321", "123456789"));
        insuredRepository.save(new Insured(0, "Johnny", "Appleseed", LocalDate.of(2000, 10, 10), "789 Pine St", "111222333", "555666777"));
    }


    }

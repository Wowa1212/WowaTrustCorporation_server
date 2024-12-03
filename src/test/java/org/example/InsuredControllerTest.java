package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InsuredControllerTest {

    @Mock
    private InsuredRepository insuredRepository;

    @InjectMocks
    private InsuredController insuredController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindInsured_Found() {
        Insured insured = new Insured(1, "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "123456789", "987654321");
        when(insuredRepository.findById(1)).thenReturn(Optional.of(insured));

        ResponseEntity<?> response = insuredController.findInsured(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insured, response.getBody());
    }

    @Test
    void testFindInsured_NotFound() {
        when(insuredRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<?> response = insuredController.findInsured(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Insured with ID 1 not found", response.getBody());
    }

    @Test
    void testRemoveInsured_Success() {
        when(insuredRepository.existsById(1)).thenReturn(true);

        ResponseEntity<String> response = insuredController.removeInsured(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Insured with ID 1 successfully removed", response.getBody());
        verify(insuredRepository, times(1)).deleteById(1);
    }

    @Test
    void testRemoveInsured_NotFound() {
        when(insuredRepository.existsById(1)).thenReturn(false);

        ResponseEntity<String> response = insuredController.removeInsured(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Insured with ID 1 not found", response.getBody());
    }

    @Test
    void testUpdateInsured_Found() {
        Insured existingInsured = new Insured(1, "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "123456789", "987654321");
        Insured updatedInsured = new Insured(1, "Jane", "Smith", LocalDate.of(1985, 5, 15), "456 Oak St", "987654321", "123456789");

        when(insuredRepository.findById(1)).thenReturn(Optional.of(existingInsured));
        when(insuredRepository.save(any(Insured.class))).thenReturn(updatedInsured);

        ResponseEntity<?> response = insuredController.updateInsured(1, updatedInsured);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedInsured, response.getBody());
    }

    @Test
    void testUpdateInsured_NotFound() {
        when(insuredRepository.findById(1)).thenReturn(Optional.empty());

        Insured updatedInsured = new Insured();
        ResponseEntity<?> response = insuredController.updateInsured(1, updatedInsured);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Insured with ID 1 not found", response.getBody());
    }

    @Test
    void testListAllInsured() {
        List<Insured> insuredList = Arrays.asList(
                new Insured(1, "John", "Doe", LocalDate.of(1990, 1, 1), "123 Main St", "123456789", "987654321"),
                new Insured(2, "Jane", "Smith", LocalDate.of(1985, 5, 15), "456 Oak St", "987654321", "123456789")
        );
        when(insuredRepository.findAll()).thenReturn(insuredList);

        ResponseEntity<List<Insured>> response = insuredController.listAllInsured();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insuredList, response.getBody());
    }

    @Test
    void testAddInsured() {
        Insured newInsured = new Insured(0, "Jane", "Doe", LocalDate.of(1992, 2, 2), "789 Pine St", "555666777", "987654321");
        Insured savedInsured = new Insured(1, "Jane", "Doe", LocalDate.of(1992, 2, 2), "789 Pine St", "555666777", "987654321");

        when(insuredRepository.save(newInsured)).thenReturn(savedInsured);

        ResponseEntity<Insured> response = insuredController.addInsured(newInsured);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedInsured, response.getBody());
    }
}
package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class InsuredTest {

    private Insured insured;

    @BeforeEach
    void setUp() {
        // Vytvoření instance třídy Insured s reálnými daty, které používáte
        insured = new Insured(
                1, "John", "Doe",
                LocalDate.of(1990, 1, 1),
                "123 Main St",
                "123456789",
                "987654321"
        );
    }

    @Test
    void testGetters() {
        // Ověření, že hodnoty přiřazené při vytvoření instance jsou správně vráceny gettery
        assertEquals(1, insured.getId());
        assertEquals("John", insured.getName());
        assertEquals("Doe", insured.getSurname());
        assertEquals(LocalDate.of(1990, 1, 1), insured.getBirthDate());
        assertEquals("123 Main St", insured.getAddress());
        assertEquals("123456789", insured.getTelephoneNumber());
        assertEquals("987654321", insured.getBankAccountNumber());
    }

    @Test
    void testEqualsAndHashCode() {
        // Testuje, zda je instance považována za rovnou jiné instanci se stejným ID
        Insured sameInsured = new Insured(
                1, "John", "Doe",
                LocalDate.of(1990, 1, 1),
                "123 Main St",
                "123456789",
                "987654321"
        );
        Insured differentInsured = new Insured(
                2, "Jane", "Smith",
                LocalDate.of(1985, 5, 15),
                "456 Oak St",
                "987654321",
                "123456789"
        );

        assertEquals(insured, sameInsured); // Měli by být stejní na základě ID
        assertEquals(insured.hashCode(), sameInsured.hashCode());
        assertNotEquals(insured, differentInsured); // Neměli by být stejní, protože mají různé ID
    }

    @Test
    void testToString() {
        // Ověření, že metoda toString vrací správný formát
        String expectedString = "Insured{id=1, name='John', surname='Doe', birthDate=1990-01-01, address='123 Main St', telephoneNumber='123456789', bankAccountNumber='987654321'}";
        assertEquals(expectedString, insured.toString());
    }
}
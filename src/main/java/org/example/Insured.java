package org.example;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "insureds") // Definuje název tabulky v databázi
public class Insured {

    @Id
    @Column(name = "ID", nullable = false)
    private Integer id; // Ručně nastavované ID

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "bank_account_number", nullable = false)
    private String bankAccountNumber;

    // Výchozí konstruktor
    public Insured() {}

    // Konstruktor se všemi parametry
    public Insured(Integer id, String name, String surname, LocalDate birthDate, String address, String telephoneNumber, String bankAccountNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.bankAccountNumber = bankAccountNumber;
    }

    // Gettery a settery
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    // Přepsané metody equals a hashCode pro správné porovnání objektů
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insured insured = (Insured) o;
        return Objects.equals(id, insured.id) &&
                Objects.equals(name, insured.name) &&
                Objects.equals(surname, insured.surname) &&
                Objects.equals(birthDate, insured.birthDate) &&
                Objects.equals(address, insured.address) &&
                Objects.equals(telephoneNumber, insured.telephoneNumber) &&
                Objects.equals(bankAccountNumber, insured.bankAccountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthDate, address, telephoneNumber, bankAccountNumber);
    }

    // Přepsaná metoda toString pro snadné ladění a logování
    @Override
    public String toString() {
        return "Insured{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                '}';
    }
}
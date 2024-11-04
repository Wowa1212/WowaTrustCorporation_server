package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuredService {

    private final InsuredRepository insuredRepository;

    @Autowired
    public InsuredService(InsuredRepository insuredRepository) {
        this.insuredRepository = insuredRepository;
    }

    // Vrací všechny pojištěnce
    public List<Insured> getAllInsureds() {
        return insuredRepository.findAll();
    }

    // Uložení nového nebo aktualizovaného pojištěnce
    public Insured saveInsured(Insured insured) {
        return insuredRepository.save(insured);
    }

    // Vyhledání pojištěnce podle jména/příjmení nebo ID
    public List<Insured> findInsured(String nameOrId) {
        try {
            // Pokud je zadáno ID, pokus se vyhledat podle ID
            int id = Integer.parseInt(nameOrId);
            return insuredRepository.findById(id).map(List::of).orElse(List.of());
        } catch (NumberFormatException e) {
            // Pokud to není číslo (ID), hledáme podle jména nebo příjmení
            return insuredRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(nameOrId, nameOrId);
        }
    }

    // Odstranění pojištěnce podle ID
    public void removeInsured(int id) {
        insuredRepository.deleteById(id);
    }
}
package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseOfTheInsured {

    private final InsuredRepository insuredRepository;

    @Autowired
    public DatabaseOfTheInsured(InsuredRepository insuredRepository) {
        this.insuredRepository = insuredRepository;
    }

    // Přidání nového pojištěnce
    public Insured addInsured(Insured insured) {
        return insuredRepository.save(insured);
    }


    // Vyhledání pojištěnce podle ID
    public Insured findInsuredByID(int id) {
        return insuredRepository.findById(id).orElse(null);
    }

    // Odstranění pojištěnce podle ID
    public void removeInsured(int id) {
        insuredRepository.deleteById(id);
    }

    // Získání seznamu všech pojištěnců
    public List<Insured> getAllInsured() {
        return insuredRepository.findAll();
    }
}
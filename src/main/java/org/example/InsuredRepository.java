package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuredRepository extends JpaRepository<Insured, Integer> {

    // Vyhledávání pojištěnců podle jména nebo příjmení, ignoruje velká a malá písmena
    List<Insured> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);
}
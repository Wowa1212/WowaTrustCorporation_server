package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller // Změna z @RestController na @Controller pro podporu zobrazení šablon
@RequestMapping("/api/insureds")
public class InsuredController {

    private final InsuredRepository insuredRepository;

    @Autowired
    public InsuredController(InsuredRepository insuredRepository) {
        this.insuredRepository = insuredRepository;
    }

    // Najde pojištěnce podle ID
    @GetMapping("/find/{id}")
    @ResponseBody
    public ResponseEntity<?> findInsured(@PathVariable("id") int id) {
        Optional<Insured> insured = insuredRepository.findById(id);
        if (insured.isPresent()) {
            return ResponseEntity.ok(insured.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Insured with ID " + id + " not found");
        }
    }

    // Smaže pojištěnce podle ID
    @DeleteMapping("/remove/{id}")
    @ResponseBody
    public ResponseEntity<String> removeInsured(@PathVariable("id") int id) {
        if (insuredRepository.existsById(id)) {
            insuredRepository.deleteById(id);
            return ResponseEntity.ok("Insured with ID " + id + " successfully removed");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Insured with ID " + id + " not found");
        }
    }

    // Aktualizuje údaje pojištěnce podle ID
    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<?> updateInsured(@PathVariable("id") int id, @RequestBody Insured updatedInsured) {
        Optional<Insured> optionalInsured = insuredRepository.findById(id);
        if (optionalInsured.isPresent()) {
            Insured insured = optionalInsured.get();
            insured.setName(updatedInsured.getName());
            insured.setSurname(updatedInsured.getSurname());
            insured.setBirthDate(updatedInsured.getBirthDate());
            insured.setAddress(updatedInsured.getAddress());
            insured.setTelephoneNumber(updatedInsured.getTelephoneNumber());
            insured.setBankAccountNumber(updatedInsured.getBankAccountNumber());
            insuredRepository.save(insured);
            return ResponseEntity.ok(insured);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Insured with ID " + id + " not found");
        }
    }

    // Vrátí seznam všech pojištěnců
    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<Insured>> listAllInsured() {
        List<Insured> insuredList = insuredRepository.findAll();
        return ResponseEntity.ok(insuredList);
    }

    // Přidá nového pojištěnce
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Insured> addInsured(@RequestBody Insured newInsured) {
        Insured savedInsured = insuredRepository.save(newInsured);
        return ResponseEntity.ok(savedInsured);
    }

    // Přesměruje na index.html
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "redirect:/index.html";
    }

    // Přidání metody pro obsluhu přihlašovací stránky

    @Controller
    public class LoginController {

        @GetMapping("/login")
        public String login() {
            return "login"; // Vrací název šablony v templates/login.html
        }
    }
}
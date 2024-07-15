package com.ups.oop.controller;

import com.ups.oop.dto.AnimalDTO;
import com.ups.oop.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/get-all-animals")
    public ResponseEntity getAllPeople() {
        return this.animalService.getAllAnimals();
    }

    @GetMapping("/get-animal")
    public ResponseEntity getPersonById(@RequestParam String id) {
        return this.animalService.getAnimalById(id);
    }

    @PostMapping("/animal")
    public ResponseEntity createPerson(@RequestBody AnimalDTO animalDTO) {
        return this.animalService.createAnimal(animalDTO);
    }

    @PutMapping("/update-animal")
    public ResponseEntity updatePerson(@RequestBody AnimalDTO animalDTO) {
        return this.animalService.updateAnimal(animalDTO);
    }

    @DeleteMapping("/remove-animal")
    public ResponseEntity deletePerson(@RequestParam String id) {
        return this.animalService.deleteAnimalById(id);
    }
}

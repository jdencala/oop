package com.ups.oop.service;

import com.ups.oop.dto.Animal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {
    private List<Animal> animalList = new ArrayList<>();

    public ResponseEntity createAnimal(Animal animal) {
        String animalId = animal.getId();
        boolean wasFound = findAnimal(animalId);
        if(wasFound) {
            String errorMessage = "Animal with id " + animalId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            animalList.add(animal);
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        }
    }

    private boolean findAnimal(String id) {
        for(Animal animal: animalList) {
            if(id.equalsIgnoreCase(animal.getId())) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity getAllAnimals() {
        if(animalList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animalList);
    }

    public ResponseEntity getAnimalById(String id) {
        for(Animal animal : animalList) {
            if(id.equalsIgnoreCase(animal.getId())) {
                return ResponseEntity.status(HttpStatus.OK).body(animal);
            }
        }
        String errorMessage = "Person with id " + id + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    public ResponseEntity updateAnimal(Animal animal) {
        String requestId = animal.getId();
        int index = 0;
        for(Animal anim: animalList) {
            if(requestId.equalsIgnoreCase(animal.getId())) {
                animalList.set(index, animal);
                return ResponseEntity.status(HttpStatus.OK).body(animal);
            }
            index++;
        }
        String errorMessage = "Animal with id " + requestId + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    public ResponseEntity deleteAnimalById(String id) {
        String message = "Animal with id " + id;
        for(Animal animal : animalList) {
            if(id.equalsIgnoreCase(animal.getId())) {
                animalList.remove(animal);
                return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
    }
}

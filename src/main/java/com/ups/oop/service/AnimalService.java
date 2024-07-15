package com.ups.oop.service;

import com.ups.oop.dto.AnimalDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {
    private List<AnimalDTO> animalDTOList = new ArrayList<>();

    public ResponseEntity createAnimal(AnimalDTO animalDTO) {
        String animalId = animalDTO.getId();
        boolean wasFound = findAnimal(animalId);
        if(wasFound) {
            String errorMessage = "Animal with id " + animalId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            animalDTOList.add(animalDTO);
            return ResponseEntity.status(HttpStatus.OK).body(animalDTO);
        }
    }

    private boolean findAnimal(String id) {
        for(AnimalDTO animalDTO : animalDTOList) {
            if(id.equalsIgnoreCase(animalDTO.getId())) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity getAllAnimals() {
        if(animalDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animalDTOList);
    }

    public ResponseEntity getAnimalById(String id) {
        for(AnimalDTO animalDTO : animalDTOList) {
            if(id.equalsIgnoreCase(animalDTO.getId())) {
                return ResponseEntity.status(HttpStatus.OK).body(animalDTO);
            }
        }
        String errorMessage = "Person with id " + id + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    public ResponseEntity updateAnimal(AnimalDTO animalDTO) {
        String requestId = animalDTO.getId();
        int index = 0;
        for(AnimalDTO anim: animalDTOList) {
            if(requestId.equalsIgnoreCase(animalDTO.getId())) {
                animalDTOList.set(index, animalDTO);
                return ResponseEntity.status(HttpStatus.OK).body(animalDTO);
            }
            index++;
        }
        String errorMessage = "Animal with id " + requestId + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    public ResponseEntity deleteAnimalById(String id) {
        String message = "Animal with id " + id;
        for(AnimalDTO animalDTO : animalDTOList) {
            if(id.equalsIgnoreCase(animalDTO.getId())) {
                animalDTOList.remove(animalDTO);
                return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
    }
}

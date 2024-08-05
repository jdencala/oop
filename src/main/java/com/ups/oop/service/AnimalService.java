package com.ups.oop.service;

import com.ups.oop.dto.AnimalDTO;
import com.ups.oop.entity.Animal;
import com.ups.oop.entity.Person;
import com.ups.oop.repository.AnimalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private List<AnimalDTO> animalDTOList = new ArrayList<>();

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

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
        List<AnimalDTO> animals = getAnimals();
        if(animals.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(animals);
    }

    public List<AnimalDTO> getAnimals() {
        Iterable<Animal> animalIterable = animalRepository.findAll();
        List<AnimalDTO> animals = new ArrayList<>();
        for(Animal anim : animalIterable) {
            AnimalDTO animal = new AnimalDTO();
            animal.setAnimalCode(anim.getName() + "-" + anim.getBreed() + "-" + anim.getColor());
            animal.setName(anim.getPetName());
            animal.setWeight(anim.getWeight());
            animal.setLength(anim.getLength());
            animal.setHeight(anim.getHeight());
            animals.add(animal);
        }
        return animals;
    }

    public ResponseEntity getAnimalById(String id) {
        Optional<Animal> animalOptional = animalRepository.findById(Long.valueOf(id));
        if(animalOptional.isPresent()) {
            Animal animalFound = animalOptional.get();
            AnimalDTO animal = new AnimalDTO();
            animal.setAnimalCode(animalFound.getName() + "-" + animalFound.getBreed() + "-" + animalFound.getColor());
            animal.setWeight(animalFound.getWeight());
            animal.setLength(animalFound.getLength());
            animal.setHeight(animalFound.getHeight());
            return ResponseEntity.status(HttpStatus.OK).body(animal);
        } else {
            String errorMessage = "Person with id " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
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

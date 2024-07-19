package com.ups.oop.service;

import com.ups.oop.dto.PersonDTO;
import com.ups.oop.entity.Person;
import com.ups.oop.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private List<PersonDTO> personDTOList = new ArrayList<>();

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ResponseEntity createPerson(PersonDTO personDTO) {
        String personId = personDTO.getId();
        boolean wasFound = findPerson(personId);
        if(wasFound) {
            String errorMessage = "Person with id " + personId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            personDTOList.add(personDTO);
            return ResponseEntity.status(HttpStatus.OK).body(personDTO);
        }
    }

    private boolean findPerson(String id) {
        for(PersonDTO personDTO : personDTOList) {
            if(id.equalsIgnoreCase(personDTO.getId())) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity getAllPeople() {

        Iterable<Person> personIterable = personRepository.findAll();
        List<PersonDTO> peopleList = new ArrayList<>();
        for(Person per : personIterable) {
//            PersonDTO person = new PersonDTO(
//                    per.getPersonId(),
//                    per.getName() + "-" + per.getLastname(),
//                    per.getAge()
//            );
            PersonDTO person = new PersonDTO();
            person.setId(per.getPersonId());
            person.setName(per.getName() + "-" + per.getLastname());
            person.setAge(per.getAge());
            peopleList.add(person);
        }

        if(peopleList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(peopleList);
    }

    public ResponseEntity getPersonById(String personId) {
        Optional<Person> personOptional = personRepository.findById(Long.valueOf(personId));
        //Optional<Person> personOptional = personRepository.findByPersonId(personId);
        if(personOptional.isPresent()) {
            //if record was found
            Person personFound = personOptional.get();
            PersonDTO person = new PersonDTO(personFound.getPersonId(),
                    personFound.getName() + "-" + personFound.getLastname(),
                    personFound.getAge());
            return ResponseEntity.status(HttpStatus.OK).body(person);
        } else {
            //if record wasn't found
            String errorMessage = "Person with id " + personId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity updatePerson(PersonDTO personDTO) {
        String requestId = personDTO.getId();
        int index = 0;
        for(PersonDTO pers : personDTOList) {
            if(requestId.equalsIgnoreCase(pers.getId())) {
                personDTOList.set(index, personDTO);
                return ResponseEntity.status(HttpStatus.OK).body(personDTO);
            }
            index++;
        }
        String errorMessage = "Person with id " + requestId + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private int findIndexById(String id){
        int index = 0;
        for(PersonDTO p : personDTOList) {
            if(id.equalsIgnoreCase(p.getId())) {
                return index;
            }
            index ++;
        }
        return -1;
    }

    public ResponseEntity updatePerson2(PersonDTO personDTO) {
        String requestId = personDTO.getId();
        int updateIndex = findIndexById(requestId);
        if(updateIndex != -1) {
            personDTOList.set(updateIndex, personDTO);
            return ResponseEntity.status(HttpStatus.OK).body(personDTO);
        }
        String errorMessage = "Person with id " + requestId + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    public ResponseEntity deletePersonById(String id) {
        String message = "Person with id " + id;
        for(PersonDTO per : personDTOList) {
            if(id.equalsIgnoreCase(per.getId())) {
                personDTOList.remove(per);
                return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
    }
}

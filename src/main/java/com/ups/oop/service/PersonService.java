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
        //check repository if record exist
        Optional<Person> personOptional = personRepository.findByPersonId(personId);
        if(personOptional.isPresent()) {
            String errorMessage = "Person with id " + personId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            //Before Register Person, name and lastname are present
            if(personDTO.getName().contains(" ")) {
                //Build Person and save in Repository
                Person personRecord = new Person();
                personRecord.setPersonId(personId);
                String[] nameStrings = personDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                personRecord.setName(name);
                personRecord.setLastname(lastname);
                personRecord.setAge(personDTO.getAge());
                personRepository.save(personRecord);
                return ResponseEntity.status(HttpStatus.OK).body(personDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person name must contain two strings separated by a whitespace");
            }
        }
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
        Optional<Person> personOptional = personRepository.findByPersonId(personId);
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
        //check repository if record exist
        Optional<Person> personOptional = personRepository.findByPersonId(requestId);
        if(personOptional.isPresent()) {
            //If record exists, then perform Update
            Person personRecord = personOptional.get();
            if(personDTO.getName().contains(" ")) {
                //Build Person and save in Repository
                personRecord.setPersonId(requestId);
                String[] nameStrings = personDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                personRecord.setName(name);
                personRecord.setLastname(lastname);
                personRecord.setAge(personDTO.getAge());
                personRepository.save(personRecord);
                return ResponseEntity.status(HttpStatus.OK).body(personDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person name must contain two strings separated by a whitespace");
            }
        } else {
            String errorMessage = "Person with id " + requestId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deletePersonById(String id) {
        String message = "Person with id " + id;
        Optional<Person> personOptional = personRepository.findByPersonId(id);
        if(personOptional.isPresent()) {
            //If record was found, then delete record
            personRepository.delete(personOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
        } else {
            //Return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
        }
    }
}

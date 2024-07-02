package com.ups.oop.service;

import com.ups.oop.dto.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private List<Person> personList = new ArrayList<>();

    public ResponseEntity createPerson(Person person) {
        String personId = person.getId();
        boolean wasFound = findPerson(personId);
        if(wasFound) {
            String errorMessage = "Person with id " + personId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            personList.add(person);
            return ResponseEntity.status(HttpStatus.OK).body(person);
        }
    }

    private boolean findPerson(String id) {
        for(Person person: personList) {
            if(id.equalsIgnoreCase(person.getId())) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity getAllPeople() {
        if(personList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }

    public ResponseEntity getPersonById(String id) {
        for(Person per : personList) {
            if(id.equalsIgnoreCase(per.getId())) {
                return ResponseEntity.status(HttpStatus.OK).body(per);
            }
        }
        String errorMessage = "Person with id " + id + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    public ResponseEntity updatePerson(Person person) {
        String requestId = person.getId();
        int index = 0;
        for(Person pers : personList) {
            if(requestId.equalsIgnoreCase(pers.getId())) {
                personList.set(index, person);
                return ResponseEntity.status(HttpStatus.OK).body(person);
            }
            index++;
        }
        String errorMessage = "Person with id " + requestId + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private int findIndexById(String id){
        int index = 0;
        for(Person p : personList) {
            if(id.equalsIgnoreCase(p.getId())) {
                return index;
            }
            index ++;
        }
        return -1;
    }

    public ResponseEntity updatePerson2(Person person) {
        String requestId = person.getId();
        int updateIndex = findIndexById(requestId);
        if(updateIndex != -1) {
            personList.set(updateIndex, person);
            return ResponseEntity.status(HttpStatus.OK).body(person);
        }
        String errorMessage = "Person with id " + requestId + " not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    public ResponseEntity deletePersonById(String id) {
        String message = "Person with id " + id;
        for(Person per : personList) {
            if(id.equalsIgnoreCase(per.getId())) {
                personList.remove(per);
                return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
    }
}

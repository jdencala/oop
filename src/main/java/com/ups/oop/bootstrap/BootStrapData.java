package com.ups.oop.bootstrap;

import com.ups.oop.entity.Person;
import com.ups.oop.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final PersonRepository personRepository;

    public BootStrapData(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Person p1 = new Person();
        p1.setPersonId("0925849630");
        p1.setName("Jorge");
        p1.setLastname("Encalada");
        p1.setAge(28);

        Person p2 = new Person();
        p2.setPersonId("0925849622");
        p2.setName("Yorch");
        p2.setLastname("Encalada");
        p2.setAge(35);

        Person p3 = new Person();
        p3.setPersonId("0929069532");
        p3.setName("Daniel");
        p3.setLastname("Dominguez");
        p3.setAge(31);

        personRepository.save(p1);
        personRepository.save(p2);
        personRepository.save(p3);

        System.out.println("--------- Started BootstrapData -------------");
        System.out.println("Number of Persons: " + personRepository.count());
    }
}

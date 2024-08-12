package com.ups.oop.bootstrap;

import com.ups.oop.entity.Animal;
import com.ups.oop.entity.Author;
import com.ups.oop.entity.Book;
import com.ups.oop.entity.Person;
import com.ups.oop.repository.AnimalRepository;
import com.ups.oop.repository.AuthorRepository;
import com.ups.oop.repository.BookRepository;
import com.ups.oop.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AnimalRepository animalRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(PersonRepository personRepository, AnimalRepository animalRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
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

        //Animals
        Animal a1 = new Animal();
        a1.setPetName("Mily");
        a1.setName("Golden");
        a1.setBreed("Retriever");
        a1.setColor("Caramel");
        a1.setWeight(22.5);
        a1.setHeight(0.8);
        a1.setLength(1.1);

        Animal a2 = new Animal();
        a2.setPetName("Milo");
        a2.setName("Labrador");
        a2.setBreed("Retriever");
        a2.setColor("White");
        a2.setWeight(24.5);
        a2.setHeight(0.5);
        a2.setLength(1.3);

        animalRepository.save(a1);
        animalRepository.save(a2);

        //Books and Authors

        Author au1 = new Author();
        au1.setName("Alejandro");
        au1.setLastname("Dumas");
        authorRepository.save(au1);

        Book b1 = new Book();
        b1.setTitle("Conde de Montecristo");
        b1.setEditorial("Pearson");
        b1.setAuthor(au1);
        bookRepository.save(b1);

        Book b3 = new Book();
        b3.setTitle("Los 3 Mosqueteros");
        b3.setEditorial("S.A. Editorial");
        b3.setAuthor(au1);
        bookRepository.save(b3);

        au1.getBooks().add(b1);
        au1.getBooks().add(b3);
        authorRepository.save(au1);

        Author au2 = new Author();
        au2.setName("J.K.");
        au2.setLastname("Rowling");
        authorRepository.save(au2);

        Book b2 = new Book();
        b2.setTitle("Harry Potter");
        b2.setEditorial("Us Editorial");
        b2.setAuthor(au2);
        bookRepository.save(b2);

        au1.getBooks().add(b2);
        authorRepository.save(au2);



        System.out.println("--------- Started BootstrapData -------------");
        System.out.println("Number of Persons: " + personRepository.count());
        System.out.println("Number of Animals: " + animalRepository.count());
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
    }
}

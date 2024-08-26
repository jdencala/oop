package com.ups.oop.bootstrap;

import com.ups.oop.entity.Animal;
import com.ups.oop.entity.Author;
import com.ups.oop.entity.Book;
import com.ups.oop.entity.Client;
import com.ups.oop.entity.Editorial;
import com.ups.oop.entity.Loan;
import com.ups.oop.entity.LoanDetail;
import com.ups.oop.entity.Person;
import com.ups.oop.entity.Worker;
import com.ups.oop.repository.AnimalRepository;
import com.ups.oop.repository.AuthorRepository;
import com.ups.oop.repository.BookRepository;
import com.ups.oop.repository.ClientRepository;
import com.ups.oop.repository.EditorialRepository;
import com.ups.oop.repository.LoanDetailRepository;
import com.ups.oop.repository.LoanRepository;
import com.ups.oop.repository.PersonRepository;
import com.ups.oop.repository.WorkerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class BootStrapData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AnimalRepository animalRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final WorkerRepository workerRepository;
    private final EditorialRepository editorialRepository;
    private final LoanRepository loanRepository;
    private final LoanDetailRepository loanDetailRepository;

    public BootStrapData(PersonRepository personRepository, AnimalRepository animalRepository,
                         AuthorRepository authorRepository, BookRepository bookRepository,
                         ClientRepository clientRepository, WorkerRepository workerRepository,
                         EditorialRepository editorialRepository, LoanRepository loanRepository,
                         LoanDetailRepository loanDetailRepository) {
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
        this.workerRepository = workerRepository;
        this.editorialRepository = editorialRepository;
        this.loanRepository = loanRepository;
        this.loanDetailRepository = loanDetailRepository;
    }

    public void createPeople() {
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
    }

    public void createAnimals() {
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
    }

    public void createBooksAuthorsAndEditorials(){
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

        //Editorials
        Editorial e1 = new Editorial();
        e1.setName("Pearson");
        e1.getBooks().add(b1);
        e1.getBooks().add(b2);
        editorialRepository.save(e1);

        Editorial e2 = new Editorial();
        e2.setName("LNS");
        e2.getBooks().add(b2);
        e2.getBooks().add(b3);
        editorialRepository.save(e2);

        //books Join with Editorial
        b1.getEditorials().add(e1);
        bookRepository.save(b1);

        b2.getEditorials().add(e1);
        b2.getEditorials().add(e2);
        bookRepository.save(b2);

        b3.getEditorials().add(e2);
        bookRepository.save(b3);
    }

    public void createClients() {
        Client c1 = new Client("c-00001", "0925849630", "Jorge", "Encalada", 28);
        Client c2 = new Client("c-00002", "0925849622", "Yorch", "Encalada", 35);
        clientRepository.save(c1);
        clientRepository.save(c2);
    }

    public void createWorkers() {
        Worker w1 = new Worker("w-00001", "0925849630", "Jorge", "Encalada", 28);
        Worker w2 = new Worker("w-00002", "0925849622", "Yorch", "Encalada", 35);
        Worker w3 = new Worker("w-00003", "0925849655", "Danny", "Encalada", 30);
        workerRepository.save(w1);
        workerRepository.save(w2);
        workerRepository.save(w3);
    }

    public void createLoans() {
        Optional<Client> clientOptional = clientRepository.findById(4l);
        Client client = new Client();
        if(clientOptional.isPresent()) {
            client = clientOptional.get();
        }

        Optional<Worker> workerOptional = workerRepository.findById(8l);
        Worker worker = new Worker();
        if(workerOptional.isPresent()) {
            worker = workerOptional.get();
        }

        Loan loan = new Loan();
        loan.setSerial("l-0001");
        loan.setLoanDate(new Date());
        loan.setClient(client);
        loan.setDays(30);
        loan.setWorker(worker);
        loanRepository.save(loan);

        Optional<Book> bookOptional = bookRepository.findById(1l);
        Book b1 = new Book();
        if(bookOptional.isPresent()) {
            b1 = bookOptional.get();
        }
        bookOptional = bookRepository.findById(2l);
        Book b2 = new Book();
        if(bookOptional.isPresent()) {
            b2 = bookOptional.get();
        }

        LoanDetail l1 = new LoanDetail();
        l1.setLoan(loan);
        l1.setBook(b1);
        loanDetailRepository.save(l1);

        LoanDetail l2 = new LoanDetail();
        l2.setLoan(loan);
        l2.setBook(b2);
        loanDetailRepository.save(l2);

        loan.getDetailList().add(l1);
        loan.getDetailList().add(l2);
        loanRepository.save(loan);
    }


    @Override
    public void run(String... args) throws Exception {
        createPeople();
        createAnimals();
        createBooksAuthorsAndEditorials();
        createClients();
        createWorkers();
        createLoans();

        System.out.println("--------- Started BootstrapData -------------");
        System.out.println("Number of Persons: " + personRepository.count());
        System.out.println("Number of Animals: " + animalRepository.count());
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Clients: " + clientRepository.count());
        System.out.println("Number of Workers: " + workerRepository.count());
        System.out.println("Number of Loans: " + loanRepository.count());
        System.out.println("Number of Loans Details: " + loanDetailRepository.count());
    }
}

package com.ups.oop.controller;

import com.ups.oop.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    private final PersonService personService;
    private final AnimalService animalService;
    private final ClientService clientService;
    private final WorkerService workerService;
    private final BookService bookService;
    private final LoanService loanService;
    private final LoanDetailService loanDetailService;

    public TemplateController(PersonService personService, AnimalService animalService,
                              ClientService clientService, WorkerService workerService,
                              BookService bookService, LoanService loanService,
                              LoanDetailService loanDetailService) {
        this.personService = personService;
        this.animalService = animalService;
        this.clientService = clientService;
        this.workerService = workerService;
        this.bookService = bookService;
        this.loanService = loanService;
        this.loanDetailService = loanDetailService;
    }

    @GetMapping("/template")
    public String getTemplate(Model model) {
        return "template";
    }

    @GetMapping("/people")
    public String getPeople(Model model) {
        model.addAttribute("people", personService.getPeople());
        return "person/list";
    }

    @GetMapping("/animals")
    public String getAnimal(Model model) {
        model.addAttribute("animals", animalService.getAnimals());
        return "animal/list";
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book/list";
    }

    @GetMapping("/clients")
    public String getClients(Model model) {
        model.addAttribute("clients", clientService.getClients());
        return "client/list";
    }

    @GetMapping("/workers")
    public String getWorkers(Model model) {
        model.addAttribute("workers", workerService.getWorkers());
        return "worker/list";
    }

    @GetMapping("/loans")
    public String getLoans(Model model) {
        model.addAttribute("loans", loanService.getLoans());
        return "loan/list";
    }

    @GetMapping("/loans-details")
    public String getLoanDetails(Model model) {
        model.addAttribute("loansDetails", loanDetailService.getLoanDetails());
        return "loan-details/list";
    }
}

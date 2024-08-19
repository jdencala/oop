package com.ups.oop.controller;

import com.ups.oop.service.AnimalService;
import com.ups.oop.service.ClientService;
import com.ups.oop.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    private final PersonService personService;
    private final AnimalService animalService;
    private final ClientService clientService;

    public TemplateController(PersonService personService, AnimalService animalService,
                              ClientService clientService) {
        this.personService = personService;
        this.animalService = animalService;
        this.clientService = clientService;
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
        model.addAttribute("animals", animalService.getAnimals());
        return "book/list";
    }

    @GetMapping("/clients")
    public String getClients(Model model) {
        model.addAttribute("clients", clientService.getClients());
        return "client/list";
    }
}

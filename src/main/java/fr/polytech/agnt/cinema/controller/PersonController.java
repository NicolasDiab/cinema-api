package fr.polytech.agnt.cinema.controller;

import fr.polytech.agnt.cinema.entity.Actor;
import fr.polytech.agnt.cinema.entity.Movie;
import fr.polytech.agnt.cinema.entity.Person;
import fr.polytech.agnt.cinema.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(path = "/people")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    @ResponseBody
    public Iterable<Person> list() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Person getById(
            @PathVariable("id") Integer id,
            HttpServletResponse response) {
        Person person = personRepository.findOne(id);
        if (person == null) {
            response.setStatus(404);
            return null;
        }
        return person;
    }

    @GetMapping("/{id}/movies")
    @ResponseBody
    public Set<Actor> add(
            @PathVariable("id") Integer id,
            HttpServletResponse response
    ) {
        Person person = this.personRepository.findOne(id);
        if(person == null) {
            response.setStatus(404);
            return null;
        }
        return person.getMovies();
    }


    @PostMapping("/")
    @ResponseBody
    public Person add(@RequestBody Person person) {
        return this.personRepository.save(person);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Person update(
            @PathVariable("id") Integer id,
            @RequestBody Person person,
            HttpServletResponse response
    ) {
        if (this.personRepository.exists(id)) {
            person.setId(id);
            return this.personRepository.save(person);
        }
        response.setStatus(404);
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void update(
            @PathVariable("id") Integer id,
            HttpServletResponse response
    ) {
        Person person = personRepository.findOne(id);
        if (person != null) {
            this.personRepository.delete(person);
            return;
        }
        response.setStatus(404);
    }


}

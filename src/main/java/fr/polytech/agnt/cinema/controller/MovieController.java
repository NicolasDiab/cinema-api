package fr.polytech.agnt.cinema.controller;

import fr.polytech.agnt.cinema.entity.Actor;
import fr.polytech.agnt.cinema.entity.ActorId;
import fr.polytech.agnt.cinema.entity.Movie;
import fr.polytech.agnt.cinema.entity.Person;
import fr.polytech.agnt.cinema.repository.ActorRepository;
import fr.polytech.agnt.cinema.repository.MovieRepository;
import fr.polytech.agnt.cinema.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    @ResponseBody
    public Iterable<Movie> list() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Movie getById(
            @PathVariable("id") Integer id,
            HttpServletResponse response) {
        Movie movie = movieRepository.findOne(id);
        if (movie == null) {
            response.setStatus(404);
            return null;
        }
        return movie;
    }

    @PostMapping("/")
    @ResponseBody
    public Movie add(@RequestBody Movie movie) {
        return this.movieRepository.save(movie);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Movie update(
            @PathVariable("id") Integer id,
            @RequestBody Movie movie,
            HttpServletResponse response
    ) {
        if (this.movieRepository.exists(id)) {
            movie.setId(id);
            return this.movieRepository.save(movie);
        }
        response.setStatus(404);
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(
            @PathVariable("id") Integer id,
            HttpServletResponse response
    ) {
        Movie movie = movieRepository.findOne(id);
        if (movie != null) {
            this.movieRepository.delete(movie);
        } else {
            response.setStatus(404);
        }
    }

    @PostMapping("/{idMovie}/actor/{idPerson}")
    @ResponseBody
    public void add(
            @RequestBody String role,
            @PathVariable("idMovie") Integer idMovie,
            @PathVariable("idPerson") Integer idPerson,
            HttpServletResponse response
    ) {
        Movie movie = this.movieRepository.findOne(idMovie);
        Person person = this.personRepository.findOne(idPerson);
        if(person == null || movie == null || role.isEmpty()) {
            response.setStatus(404);
            return ;
        }
        Actor actor = new Actor(person,movie,role);
        this.actorRepository.save(actor);
    }


    @PutMapping("/{idMovie}/actor")
    @ResponseBody
    public void updateActorList(
            @RequestBody List<Actor> actors,
            @PathVariable("idMovie") Integer idMovie,
            HttpServletResponse response
    ) {
        Movie movie = this.movieRepository.findOne(idMovie);
        if(actors == null || movie == null) {
            response.setStatus(404);
            return ;
        }
        actors.stream().map(actor -> {
            actor.getId().setMovieId(movie.getId());
            actor.setMovie(movie);
            return actor;
        });
        this.actorRepository.delete(movie.getActors());
        this.actorRepository.save(actors);
    }

    @DeleteMapping("/{idMovie}/actor/{idPerson}")
    @ResponseBody
    public void update(
            @PathVariable("idMovie") Integer idMovie,
            @PathVariable("idPerson") Integer idPerson,
            HttpServletResponse response
    ) {
        ActorId id = new ActorId(idPerson,idMovie);
        if (this.actorRepository.exists(id)) {
            this.actorRepository.delete(id);
        } else {
            response.setStatus(404);
        }
    }



}

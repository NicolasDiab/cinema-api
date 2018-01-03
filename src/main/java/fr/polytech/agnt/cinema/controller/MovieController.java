package fr.polytech.agnt.cinema.controller;

import fr.polytech.agnt.cinema.entity.Movie;
import fr.polytech.agnt.cinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

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
    public void update(
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

}

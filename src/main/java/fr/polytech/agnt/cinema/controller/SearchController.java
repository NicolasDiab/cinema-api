package fr.polytech.agnt.cinema.controller;

import fr.polytech.agnt.cinema.entity.Movie;
import fr.polytech.agnt.cinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/search")
public class SearchController {
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movie/{query}")
    @ResponseBody
    public List<Movie> list(
            @PathVariable("query") String query
    ) {
        List<Movie> movies = movieRepository.search(query);
        System.out.println(movies);
        return movies;
    }
}

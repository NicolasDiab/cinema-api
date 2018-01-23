package fr.polytech.agnt.cinema.repository;

import fr.polytech.agnt.cinema.entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    @Query("SELECT m FROM Movie m join m.category c join m.director d where title like %:text% or c.name like %:text% or d.firstname like %:text% or d.lastname like %:text%")
    public List<Movie> search(@Param("text") String text);
}

package fr.polytech.agnt.cinema.repository;

import fr.polytech.agnt.cinema.entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    @Query("SELECT m FROM Movie m inner join m.category c where title like %:text% or c.name like %:text%")
    public List<Movie> search(@Param("text") String text);
}

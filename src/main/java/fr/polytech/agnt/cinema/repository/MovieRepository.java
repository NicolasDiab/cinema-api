package fr.polytech.agnt.cinema.repository;

import fr.polytech.agnt.cinema.entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
//    @Query("SELECT m FROM Movies m  join Person p on p.id = m.id where p.id = :directorId")
//    public List<Movie> findByDirector(@Param("directorId") Integer directorId);
}

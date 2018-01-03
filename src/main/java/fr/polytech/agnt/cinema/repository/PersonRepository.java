package fr.polytech.agnt.cinema.repository;

import fr.polytech.agnt.cinema.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {
//    @Query("SELECT p FROM Person p LEFT JOIN Movies m ON p.id = m.id")
//    List<Person> findAllDirector();
}

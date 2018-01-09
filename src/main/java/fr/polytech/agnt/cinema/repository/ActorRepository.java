package fr.polytech.agnt.cinema.repository;

import fr.polytech.agnt.cinema.entity.Actor;
import fr.polytech.agnt.cinema.entity.ActorId;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, ActorId> {
}

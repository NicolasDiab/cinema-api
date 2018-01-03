package fr.polytech.agnt.cinema.repository;

import fr.polytech.agnt.cinema.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}

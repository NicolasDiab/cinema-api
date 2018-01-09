package fr.polytech.agnt.cinema.controller;

import fr.polytech.agnt.cinema.entity.Category;
import fr.polytech.agnt.cinema.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping(path = "/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    @ResponseBody
    public Iterable<Category> list() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Category getById(
            @PathVariable("id") Integer id,
            HttpServletResponse response) {
        Category category = categoryRepository.findOne(id);
        if(category == null){
            response.setStatus(404);
            return null;
        }
        return category;
    }

    @PostMapping("/")
    @ResponseBody
    public Category add(@RequestBody Category category) {
        return this.categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Category update(
            @PathVariable("id") Integer id,
            @RequestBody Category category,
            HttpServletResponse response
    ) {
        if (this.categoryRepository.exists(id)) {
            category.setId(id);
            return this.categoryRepository.save(category);
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
        Category category = categoryRepository.findOne(id);
        if (category != null) {
            this.categoryRepository.delete(category);
            return;
        }
        response.setStatus(404);
    }


}

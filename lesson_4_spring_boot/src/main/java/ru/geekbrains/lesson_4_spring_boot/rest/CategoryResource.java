package ru.geekbrains.lesson_4_spring_boot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.lesson_4_spring_boot.controller.NotFoundException;
import ru.geekbrains.lesson_4_spring_boot.service.CategoryService;
import ru.geekbrains.lesson_4_spring_boot.service.dto.CategoryDto;
import ru.geekbrains.lesson_4_spring_boot.service.dto.ErrorDto;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryResource {

    private CategoryService categoryService;

    @Autowired
    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }



    @GetMapping
    public Page<CategoryDto> search(@RequestParam("categoryFilter") Optional<String> categoryFilter,
                                    @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size,
                                    @RequestParam("sort") Optional<String> sort){
        return categoryService.findAll(
                categoryFilter,
                page.orElse(1) - 1,
                size.orElse(5),
                sort.filter(s -> !s.isBlank()).orElse("id"));
    }
    @GetMapping("/{id}")
    public  CategoryDto findOne(@PathVariable("id") Long id){
        return categoryService.findById(id).orElseThrow(()-> new NotFoundException("Category with id " + id + "not found"));

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@RequestBody CategoryDto categoryDto){
        if(categoryDto.getId() != null){
            throw  new IllegalArgumentException("For category creation id have to be null");
        }
        return categoryService.save(categoryDto);
    }
    @PutMapping
    public CategoryDto update(@RequestBody CategoryDto categoryDto){
        if(categoryDto.getId() == null){
            throw  new IllegalArgumentException("For category update id have to be not null");
        }
        return categoryService.save(categoryDto);

    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        categoryService.deleteById(id);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto notFoundExceptionHandler(NotFoundException ex) {
        return  new ErrorDto(ex.getMessage());
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto illegalArgumentException(IllegalArgumentException ex) {
        return  new ErrorDto(ex.getMessage());
    }

}

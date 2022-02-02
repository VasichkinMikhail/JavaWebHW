package ru.geekbrains.lesson_4_spring_boot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.lesson_4_spring_boot.controller.NotFoundException;
import ru.geekbrains.lesson_4_spring_boot.persist.Product;
import ru.geekbrains.lesson_4_spring_boot.service.CartServise;
import ru.geekbrains.lesson_4_spring_boot.service.ProductService;
import ru.geekbrains.lesson_4_spring_boot.service.dto.ErrorDto;
import ru.geekbrains.lesson_4_spring_boot.service.dto.ProductDto;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
public class CartRest {


    private CartServise cartServise;

    @Autowired
    public CartRest(CartServise cartServise) {
        this.cartServise = cartServise;
    }

    @GetMapping
    public Page<ProductDto> search(@RequestParam("nameFilter") Optional<String> nameFilter,
                                   @RequestParam("page") Optional<Integer> page,
                                   @RequestParam("size") Optional<Integer> size,
                                   @RequestParam("sort") Optional<String> sort){
        return cartServise.findAll(
                nameFilter,
                page.orElse(1) - 1,
                size.orElse(5),
                sort.filter(s -> !s.isBlank()).orElse("id"));
    }


    @PostMapping("sub/{id}")
    public void add(@PathVariable("id") Long id) {
        cartServise.addToCart(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        cartServise.removeFromCart(id);
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

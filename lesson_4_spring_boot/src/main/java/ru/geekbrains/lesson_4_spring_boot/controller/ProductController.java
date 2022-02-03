package ru.geekbrains.lesson_4_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.lesson_4_spring_boot.persist.Product;
import ru.geekbrains.lesson_4_spring_boot.persist.ProductRepository;


import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.lesson_4_spring_boot.persist.ProductSpecification;


    @Controller
    @RequestMapping("/product")
    public class ProductController {

         private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

        private final ProductRepository productRepository;



        @Autowired
        public ProductController(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        @GetMapping
        public String listPage(Model model,
                               @RequestParam("nameFilter") Optional<String> nameFilter) {
            logger.info("Product filter with name pattern {}", nameFilter.orElse(null));

//            List<Product> products;
//
//            if(nameFilter.isPresent() && !nameFilter.get().isBlank()){
//                products = productRepository.findAllByNameLike("%" + nameFilter.get() + "%");
//            }else {
//                products = productRepository.findAll();
//            }


            Specification<Product> spec = Specification.where(null);
            if (nameFilter.isPresent() && !nameFilter.get().isBlank()) {
               spec = spec.and(ProductSpecification.nameLike(nameFilter.get()));
            }else {
                productRepository.findAll();
            }

            model.addAttribute("products",productRepository.findAll(spec));
            return "product";
        }
        @GetMapping("/min")
        public String minPrice(Model model,
                               @RequestParam("minPrice") BigDecimal minPrice) {
//
//            List<Product> products;
//            if(minPrice!=null) {
//                products = productRepository.findByFilter(null,minPrice,null);
//            }else {
//                products = productRepository.findAll();
//
//            }
//            model.addAttribute("products",products);

            Specification<Product> spec = Specification.where(null);
            if (minPrice != null) {
                spec = spec.and(ProductSpecification.minPriceFilter(minPrice));
            }else {
                productRepository.findAll();
            }

            model.addAttribute("products",productRepository.findAll(spec));
            return "product";
        }
        @GetMapping("/max")
        public String maxPrice(Model model,
                               @RequestParam("maxPrice") BigDecimal maxPrice) {
//            List<Product> products;
//            if(maxPrice != null){
//                products = productRepository.findByFilter(null,null,maxPrice);
//            }else {
//                products = productRepository.findAll();
//            }
//
//            model.addAttribute("products",products);

            Specification<Product> spec = Specification.where(null);
            if (maxPrice!= null) {
                spec = spec.and(ProductSpecification.minPriceFilter(maxPrice));
            }else {
                productRepository.findAll();
            }

            model.addAttribute("products",productRepository.findAll(spec));
            return "product";
        }

        @GetMapping("/new")
        public String create(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }
        @GetMapping("/delete{id}")
        public String deleteById(@PathVariable("id") Long id) {
            productRepository.deleteById(id);
            return"redirect:/product";

        }

        @PostMapping
        public String save(@Valid Product product, BindingResult result) {
            if(result.hasErrors()){
                return "product_form";
            }
            productRepository.save(product);
            return "redirect:/product";
        }

        @ExceptionHandler
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public String notFoundExceptionHandler(NotFoundException ex, Model model) {
            model.addAttribute("message", ex.getMessage());
            return "not_found";
        }

    }



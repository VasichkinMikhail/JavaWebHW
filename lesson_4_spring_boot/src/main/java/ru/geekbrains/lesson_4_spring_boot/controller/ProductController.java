package ru.geekbrains.lesson_4_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.lesson_4_spring_boot.persist.CategoryRepository;
import javax.validation.Valid;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.lesson_4_spring_boot.service.ProductService;
import ru.geekbrains.lesson_4_spring_boot.service.dto.ProductDto;


    @Controller
    @RequestMapping("/product")
    public class ProductController {

         private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

        private final ProductService productService;

        private final CategoryRepository categoryRepository;





        @Autowired
        public ProductController(ProductService productService, CategoryRepository categoryRepository) {
            this.productService = productService;
            this.categoryRepository = categoryRepository;
        }

        @GetMapping
        public String listPage(Model model,
                               @RequestParam("nameFilter") Optional<String> nameFilter,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size,
                               @RequestParam("sort") Optional<String> sort) {
            logger.info("Product filter with name pattern {}", nameFilter.orElse(null));

            model.addAttribute("products", productService.findAll(
                    nameFilter,
                    page.orElse(1) - 1,
                    size.orElse(5),
                    sort.filter(s -> !s.isBlank()).orElse("id")));
            return "product";
        }
//        @GetMapping("/min")
//        public String minPrice(Model model,
//                               @RequestParam("minPrice") BigDecimal minPrice) {
//
//
//            Specification<ProductDto> spec = Specification.where(null);
//            if (minPrice != null) {
//                spec = spec.and(ProductSpecification.minPriceFilter(minPrice));
//            }
//
//           // model.addAttribute("products", productService.findAll(spec));
//            return "product";
//        }
//        @GetMapping("/max")
//        public String maxPrice(Model model,
//                               @RequestParam("maxPrice") BigDecimal maxPrice) {
//
//            Specification<ProductDto> spec = Specification.where(null);
//            if (maxPrice != null) {
//                spec = spec.and(ProductSpecification.minPriceFilter(maxPrice));
//            } else {
//
//                model.addAttribute("products", categoryRepository.findAll(spec)).;
//                return "product";
//            }
//        }

        @GetMapping("/new")
        public String create(Model model) {
        model.addAttribute("product", new ProductDto());
        model.addAttribute("categories",categoryRepository.findAll());
        return "product_form";
    }
        @DeleteMapping("/{id}")
        public String deleteById(@PathVariable("id") Long id) {
            productService.deleteById(id);
            return"redirect:/product";

        }
        @GetMapping("/{id}")
        public String edit(@PathVariable("id") Long id, Model model) {
            model.addAttribute("product", productService.findById(id)
                    .orElseThrow(() -> new NotFoundException("Product not found")));
            model.addAttribute("categories",categoryRepository.findAll());
            return "product_form";
        }

        @PostMapping
        public String save(@Valid ProductDto productDto, BindingResult result) {
            if(result.hasErrors()){
                return "product_form";
            }
            productService.save(productDto);
            return "redirect:/product";
        }

        @ExceptionHandler
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public String notFoundExceptionHandler(NotFoundException ex, Model model) {
            model.addAttribute("message", ex.getMessage());
            return "not_found";
        }

    }



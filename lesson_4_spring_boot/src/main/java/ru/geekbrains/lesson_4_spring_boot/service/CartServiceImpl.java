package ru.geekbrains.lesson_4_spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.lesson_4_spring_boot.persist.*;
import ru.geekbrains.lesson_4_spring_boot.service.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartServise{

    private final ProductRepository productRepository;

    @Autowired
    public CartServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private final CategoryRepository categoryRepository;

    private List<Product> cartList = new ArrayList<>();



    @Override
    public Page<ProductDto> findAll(Optional<String> nameFilter, Integer page, Integer size, String sort) {
        Specification<Product> spec = Specification.where(null);
        if (nameFilter.isPresent() && !nameFilter.get().isBlank()) {
            spec = spec.and(ProductSpecification.nameLike(nameFilter.get()));
        }
        return productRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sort)))
                .map(CartServiceImpl::convertToDto);
    }


    @Override
    public void addToCart(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        cartList.add(product);
    }

    @Override
    public void removeFromCart(long id) {
        for (Product product : cartList) {
            if (product.getId().equals(id)) {
                cartList.remove(product);
            }
        }
    }
        private static ProductDto convertToDto(Product prod) {
            return new ProductDto
                    (prod.getId(),
                            prod.getName(),
                            prod.getDescription(),
                            prod.getPrice(),
                            prod.getCategory()!=null ? prod.getCategory().getId():null,
                            prod.getCategory()!=null ? prod.getCategory().getName():null);
        }


}

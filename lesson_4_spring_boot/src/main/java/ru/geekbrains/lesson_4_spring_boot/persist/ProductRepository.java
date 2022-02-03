package ru.geekbrains.lesson_4_spring_boot.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface ProductRepository  extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByNameLike(String pattern);

    @Override
    void deleteById(Long id);


    @Query("select p from Product p " +
            "where (p.name like :pattern or :pattern is null ) and " +
            "(p.price >= :minPrice or :minPrice is null) and " +
            "(p.price <= :maxPrice or :maxPrice is null)")
    List<Product> findByFilter(@Param("pattern") String pattern,
                               @Param("minPrice") BigDecimal minPrice,
                               @Param("maxPrice") BigDecimal maxPrice);
}

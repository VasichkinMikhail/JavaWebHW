package ru.geekbrains.persist;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);



    @PostConstruct
    public void init() {
        this.save(new Product(1L, "Product 1",new BigDecimal(160),"bla bla bla"));
        this.save(new Product(2L, "Product 2",new BigDecimal(320),"bla bla bla"));
        this.save(new Product(3L, "Product 3",new BigDecimal(655),"bla bla bla"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public Optional<Product> findById(long id) {
        return Optional.ofNullable(productMap.get(id));
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            long id = lastId()+1;
            product.setId(id);
        }
        productMap.put(product.getId(), product);
    }

    public Long lastId() {
        ArrayList<Product> products = new ArrayList<>(productMap.values());
        long lastId = 0;
        for (Product product : products) {
            if (product.getId() > lastId) {
                lastId = product.getId();
            }
        }
        return lastId;
    }

    @Override
    public void delete(long id) {
        productMap.remove(id);
    }

}


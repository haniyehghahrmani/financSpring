package org.java.financespring.service;

import org.java.financespring.model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    Product edit(Long id, Product product);

    void remove(Long id);

    List<Product> findAll();

    Product findById(Long id);
}
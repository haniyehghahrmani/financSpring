package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Product;
import org.java.financespring.repository.ProductRepository;
import org.java.financespring.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product edit(Long id, Product product) throws NoContentException {
        Product existingProduct = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Product Found with id " + id + " To Update!")
                );

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStockQuantity(product.getStockQuantity());
        existingProduct.setIsActive(product.getIsActive());

        return repository.saveAndFlush(existingProduct);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(Long id) throws NoContentException {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No Product found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }    }
}

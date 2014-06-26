package com.thoughtworks.zhouxuan.resource;

import com.thoughtworks.zhouxuan.domain.Product;

import java.util.List;

public interface ProductRepository {

    public List<Product> getAllProducts();


    Product getProductById(int id);

    void saveProduct(Product product);
}

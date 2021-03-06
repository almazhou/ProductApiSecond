package com.thoughtworks.zhouxuan.resource;

import com.thoughtworks.zhouxuan.domain.Pricing;
import com.thoughtworks.zhouxuan.domain.Product;

import java.util.List;

public interface ProductRepository {

    public List<Product> getAllProducts();


    Product getProductById(int id);

    void saveProduct(Product product);

    List<Pricing> getAllPricingsOfProduct(int productId);

    void savePricingOfProduct(Product product, Pricing pricing);

    Pricing getAllPricingsOfProductById(int productId, int pricingId);
}

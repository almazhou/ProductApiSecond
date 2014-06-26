package com.thoughtworks.zhouxuan.repository;

import com.thoughtworks.zhouxuan.domain.Pricing;
import com.thoughtworks.zhouxuan.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductRepository {

    public List<Product> getAllProducts();


    Product getProductById(int id);

    void saveProduct(Product product);

    List<Pricing> getAllPricingsOfProduct(int productId);

    void savePricingOfProduct(@Param("product")Product product, @Param("pricing")Pricing pricing);

    Pricing getAllPricingsOfProductById(@Param("productId") int productId,@Param("pricingId") int pricingId);
}

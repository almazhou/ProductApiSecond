package com.thoughtworks.zhouxuan.repository;

import com.thoughtworks.zhouxuan.db.Mybatis;
import com.thoughtworks.zhouxuan.domain.Pricing;
import com.thoughtworks.zhouxuan.domain.Product;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProductRepositoryTest {

    private SqlSession sqlSession;
    private ProductRepository productRepository;
    private Product product1;
    private Product product;
    private Pricing pricing;

    @Before
    public void setUp() throws Exception {
        sqlSession = Mybatis.getSessionFactory().openSession();
        productRepository = sqlSession.getMapper(ProductRepository.class);

        product1 = new Product("product1");
        product = new Product("product1");
        pricing = new Pricing(54.00);
    }

    @After
    public void tearDown() throws Exception {
        sqlSession.rollback();

    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> allProducts0 = productRepository.getAllProducts();
        productRepository.saveProduct(product1);
        List<Product> allProducts = productRepository.getAllProducts();

        assertThat(allProducts.size()-allProducts0.size(),is(1));
    }

    @Test
    public void testGetProductById() throws Exception {
        productRepository.saveProduct(product);

        Product productById = productRepository.getProductById(product.getId());

        assertThat(productById.getId(),is(product.getId()));
        assertThat(productById.getName(),is(product.getName()));
    }

    @Test
    public void testGetAllPricingsOfProduct() throws Exception {
        productRepository.saveProduct(product);
        productRepository.savePricingOfProduct(product, pricing);
        List<Pricing> allPricingsOfProduct = productRepository.getAllPricingsOfProduct(product.getId());
        assertThat(allPricingsOfProduct.size(),is(1));
        assertThat(allPricingsOfProduct.get(0).getAmount(),is(54.00));


    }

    @Test
    public void testGetAllPricingsOfProductById() throws Exception {

    }
}

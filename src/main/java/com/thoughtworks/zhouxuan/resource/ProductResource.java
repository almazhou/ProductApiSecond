package com.thoughtworks.zhouxuan.resource;

import com.thoughtworks.zhouxuan.domain.Product;
import com.thoughtworks.zhouxuan.json.ProductJson;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
public class ProductResource {
    @Inject
    ProductRepository productRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductJson> getAllProducts(@Context UriInfo uriInfo){
        List<Product> allProducts = productRepository.getAllProducts();
        List<ProductJson> productsJsons = allProducts.stream().map(product -> new ProductJson(uriInfo, product)).collect(Collectors.toList());
        return productsJsons;

    }
}

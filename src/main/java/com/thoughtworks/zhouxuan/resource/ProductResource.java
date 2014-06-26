package com.thoughtworks.zhouxuan.resource;

import com.thoughtworks.zhouxuan.domain.Product;
import com.thoughtworks.zhouxuan.json.ProductJson;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
public class ProductResource {
    @Inject
    ProductRepository productRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductJson> getAllProducts(@Context UriInfo uriInfo) {
        List<Product> allProducts = productRepository.getAllProducts();
        List<ProductJson> productsJsons = allProducts.stream().map(product -> new ProductJson(uriInfo, product)).collect(Collectors.toList());
        return productsJsons;
    }


    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createProduct(@Context UriInfo uriInfo, Form form){
        Product product = new Product(form.asMap().getFirst("name"));
        productRepository.saveProduct(product);

        return Response.created(URI.create(new ProductJson(uriInfo,product).getUri())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public ProductJson getProductById(@PathParam("id") int id, @Context UriInfo uriInfo) {
        Product productById = productRepository.getProductById(id);
        return new ProductJson(uriInfo, productById);
    }
}

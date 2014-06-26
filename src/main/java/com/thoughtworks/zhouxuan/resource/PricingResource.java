package com.thoughtworks.zhouxuan.resource;

import com.thoughtworks.zhouxuan.domain.Pricing;
import com.thoughtworks.zhouxuan.domain.Product;
import com.thoughtworks.zhouxuan.json.PricingJson;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

public class PricingResource {
    private UriInfo uriInfo;
    private Product product;
    private ProductRepository productRepository;

    public PricingResource(UriInfo uriInfo, Product product, ProductRepository productRepository) {
        this.uriInfo = uriInfo;
        this.product = product;
        this.productRepository = productRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PricingJson> getAllPricingsOfProduct(){
        return productRepository.getAllPricingsOfProduct(product.getId()).stream().map(pricing -> new PricingJson(uriInfo, product, pricing)).collect(Collectors.toList());
    }


    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createPricing(Form form){
        String amount = form.asMap().getFirst("amount");
        Pricing pricing = new Pricing(Double.valueOf(amount).doubleValue());
        productRepository.savePricingOfProduct(product,pricing);
        return Response.created(URI.create(new PricingJson(uriInfo,product,pricing).getUri())).build();
    }


}

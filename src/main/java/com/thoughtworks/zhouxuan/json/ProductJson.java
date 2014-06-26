package com.thoughtworks.zhouxuan.json;

import com.thoughtworks.zhouxuan.domain.Product;

import javax.ws.rs.core.UriInfo;

public class ProductJson {

    private UriInfo uriInfo;
    private Product product;

    public ProductJson(UriInfo uriInfo, Product product) {

        this.uriInfo = uriInfo;
        this.product = product;
    }

    public String getName(){
        return product.getName();
    }

    public int getId(){
        return product.getId();
    }

    public String getUri(){
        return uriInfo.getBaseUri()+"products/"+String.valueOf(product.getId());
    }
}

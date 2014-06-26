package com.thoughtworks.zhouxuan.json;

import com.thoughtworks.zhouxuan.domain.Pricing;
import com.thoughtworks.zhouxuan.domain.Product;

import javax.ws.rs.core.UriInfo;

public class PricingJson {

    private UriInfo uriInfo;
    private Product product;
    private Pricing pricing;

    public PricingJson(UriInfo uriInfo, Product product, Pricing pricing) {

        this.uriInfo = uriInfo;
        this.product = product;
        this.pricing = pricing;
    }

    public int geId(){
        return pricing.getId();
    }
    public int getProductId(){
        return product.getId();
    }

    public double getAmount(){
        return pricing.getAmount();
    }

    public String getUri(){
        return uriInfo.getBaseUri()+"products/"+String.valueOf(product.getId())+"/pricings/"+String.valueOf(pricing.getId());
    }

}

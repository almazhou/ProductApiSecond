package com.thoughtworks.zhouxuan;

import com.thoughtworks.zhouxuan.domain.Pricing;
import com.thoughtworks.zhouxuan.domain.Product;
import com.thoughtworks.zhouxuan.exception.ProductNotFoundException;
import com.thoughtworks.zhouxuan.exception.ProductNotFoundExceptionMapper;
import com.thoughtworks.zhouxuan.resource.ProductRepository;
import com.thoughtworks.zhouxuan.resource.ProductResource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductResourceTest extends JerseyTest {

    private Product  product1 = new Product(1, "product1");
    private Pricing  pricing = new Pricing(54.00);

    @Override
    protected Application configure() {
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                bind(mockProductRepository).to(ProductRepository.class);
            }
        };
        return new ResourceConfig().register(ProductResource.class).register(binder).register(ProductNotFoundExceptionMapper.class);
    }

    @Mock
    ProductRepository mockProductRepository;


    @Captor
    ArgumentCaptor<Product> productArgumentCaptor;

    @Test
    public void should_return_200_when_get_all_products_successful() throws Exception {
        when(mockProductRepository.getAllProducts()).thenReturn(Arrays.asList(new Product(1,"product1")));

        Response response = target("/products").request().get();

        assertThat(response.getStatus(),is(200));

        List list = response.readEntity(List.class);

        Map product = (Map) list.get(0);

        assertThat(product.get("id"),is(1));
        assertThat(product.get("name"),is("product1"));
    }

    @Test
    public void should_return_404_when_products_not_fond() throws Exception {
        when(mockProductRepository.getAllProducts()).thenThrow(ProductNotFoundException.class);

        Response response = target("/products").request().get();

        assertThat(response.getStatus(),is(404));

    }

    @Test
    public void should_get_200_when_get_product_by_id() throws Exception {
        when(mockProductRepository.getProductById(1)).thenReturn(product1);

        Response response = target("/products/1").request().get();

        assertThat(response.getStatus(),is(200));

        Map list = response.readEntity(Map.class);


        assertThat(list.get("id"),is(1));
        assertThat(list.get("name"),is("product1"));
    }

    @Test
    public void should_return_201_for_post_one_product() throws Exception {
        Response post = target("/products").request().post(Entity.form(new Form().param("name", "product1")));

        assertThat(post.getStatus(),is(201));

        verify(mockProductRepository).saveProduct(productArgumentCaptor.capture());

        assertThat(productArgumentCaptor.getValue().getName(),is("product1"));

        assertTrue(post.getHeaderString("location").contains("/products/"));
    }

    @Test
    public void should_get_200_for_get_all_pricing_of_product() throws Exception {
        when(mockProductRepository.getProductById(eq(1))).thenReturn(product1);
        when(mockProductRepository.getAllPricingsOfProduct(eq(1))).thenReturn(Arrays.asList(pricing));

        Response response = target("/products/1/pricings").request().get();

        assertThat(response.getStatus(),is(200));

        List list = response.readEntity(List.class);

        Map pricing = (Map) list.get(0);
        assertThat(pricing.get("amount"),is(54.00));

    }
}

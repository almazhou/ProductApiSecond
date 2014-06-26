package com.thoughtworks.zhouxuan;

import com.thoughtworks.zhouxuan.domain.Product;
import com.thoughtworks.zhouxuan.resource.ProductRepository;
import com.thoughtworks.zhouxuan.resource.ProductResource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductResourceTest extends JerseyTest {
    @Override
    protected Application configure() {
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                bind(mockProductRepository).to(ProductRepository.class);
            }
        };
        return new ResourceConfig().register(ProductResource.class).register(binder);
    }

    @Mock
    ProductRepository mockProductRepository;


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
}

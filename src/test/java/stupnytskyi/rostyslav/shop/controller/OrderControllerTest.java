package stupnytskyi.rostyslav.shop.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import stupnytskyi.rostyslav.shop.dto.request.OrderCreationRequest;
import stupnytskyi.rostyslav.shop.dto.response.ItemResponse;
import stupnytskyi.rostyslav.shop.dto.response.OrderResponse;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class OrderControllerTest extends AbstractControllerTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createOrderTest() throws Exception {
        String url = "/order";
        OrderCreationRequest orderCreationRequest = new OrderCreationRequest();
        orderCreationRequest.setQuantity(10);
        orderCreationRequest.setItemId(2L);

        String inputJson = super.mapToJson(orderCreationRequest);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteOrder() throws Exception {
        String uri = "/order?id=1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void getAllOrders() throws Exception {
        String uri = "/order/all";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OrderResponse[] orders = super.mapFromJson(content, OrderResponse[].class);

        System.out.println(Arrays.toString(orders));
    }
}

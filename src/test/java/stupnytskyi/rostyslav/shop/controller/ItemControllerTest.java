package stupnytskyi.rostyslav.shop.controller;

import stupnytskyi.rostyslav.shop.dto.request.ItemCreationRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import stupnytskyi.rostyslav.shop.dto.request.ItemUpdateQuantityRequest;
import stupnytskyi.rostyslav.shop.dto.response.ItemResponse;

import java.util.Arrays;


public class ItemControllerTest extends AbstractControllerTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createItemTest() throws Exception {
        String url = "/item";
        ItemCreationRequest itemCreationRequest = new ItemCreationRequest();
        itemCreationRequest.setPrice(25.0);
        itemCreationRequest.setQuantity(200);
        itemCreationRequest.setTitle("Ginger Juice");
        itemCreationRequest.setMaker("ATB");

        String inputJson = super.mapToJson(itemCreationRequest);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void updateItemQuantity() throws Exception {
        String uri = "/item/quantity";
        ItemUpdateQuantityRequest itemUpdateQuantityRequest = new ItemUpdateQuantityRequest();
        itemUpdateQuantityRequest.setItemId(1L);
        itemUpdateQuantityRequest.setQuantity(100);

        String inputJson = super.mapToJson(itemUpdateQuantityRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }

    @Test
    public void getItemsBySearchRequest() throws Exception {
        String uri = "/item/search?title=juice&quantity=100";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        ItemResponse[] items = super.mapFromJson(content, ItemResponse[].class);

        System.out.println(Arrays.toString(items));
    }
}

package springboot.rest.greeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@WebMvcTest(GreetingController.class)
class GreetingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void greeting() throws Exception {
        // Load Spring
        RequestBuilder request = MockMvcRequestBuilders.get("/greeting");
        // Perform request
        MvcResult result = mvc.perform(request).andReturn();
        // Assert
        assertEquals("{\"id\":1,\"content\":\"Hello, World!\"}", result.getResponse().getContentAsString());
    }

    // Alternative without the RequestBuilder
    //
    @Test
    public void greetingWithName() throws Exception {
        // Load Spring
        RequestBuilder request = MockMvcRequestBuilders.get("/greeting?name=Dan");
        // Perform request
        MvcResult result = mvc.perform(request).andReturn();
        // Assert
        assertEquals("{\"id\":1,\"content\":\"Hello, Dan!\"}", result.getResponse().getContentAsString());
    }
}
package com.udacity.pricing;

import com.udacity.pricing.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PriceRepository priceRepository;

    @Test
    public void test_getting_price_by_id() throws Exception {
        mockMvc.perform(get("/prices/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.currency").exists())
                .andExpect(jsonPath("$.currency").value("IDR"));
    }
    @Test
    public void test_getting_all_prices() throws Exception {
        mockMvc.perform(get("/prices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded").exists())
                .andExpect(jsonPath("$._embedded.prices").exists())
                .andExpect(jsonPath("$._embedded.prices", hasSize(5)));
    }

}

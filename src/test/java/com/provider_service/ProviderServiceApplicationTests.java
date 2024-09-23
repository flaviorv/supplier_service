package com.provider_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.provider_service.model.domain.Supplier;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProviderServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    @DisplayName("Register a supplier")
    void register() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setId(1);
        supplier.setName("FRV Construction SA");
        supplier.setCnpj("654315489");
        supplier.setEmail("frv@gmail.com");
        supplier.setPhone("(32) 3261-1552");

        ObjectMapper objectMapper = new ObjectMapper();
        String supplerJson = objectMapper.writeValueAsString(supplier);

        mockMvc.perform(post("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(supplerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("FRV Construction SA"))
                .andExpect(jsonPath("$.cnpj").value("654315489"))
                .andExpect(jsonPath("$.email").value("frv@gmail.com"))
                .andExpect(jsonPath("$.phone").value("(32) 3261-1552"));
    }

    @Test
    @Order(2)
    @DisplayName("Get a supplier by id")
    void getById() throws Exception {
        mockMvc.perform(get("/supplier/1")).andExpect(status().isOk());
    }

    @Test
    @Order(3)
    @DisplayName("Return status 404 Supplier id not found")
    void getByNonExistentId() throws Exception {
        mockMvc.perform(get("/supplier/100")).andExpect(status().isNotFound());
    }
}

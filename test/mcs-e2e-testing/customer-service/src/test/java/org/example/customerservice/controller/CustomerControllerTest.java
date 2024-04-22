package org.example.customerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.customerservice.dto.CustomerDTO;
import org.example.customerservice.exceptions.CustomerNotFoundException;
import org.example.customerservice.service.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;

@ActiveProfiles("test")
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    List<CustomerDTO> customerDTOS;

    @BeforeEach
    void setup() {
        customerDTOS = List.of(
                CustomerDTO.builder()
                        .id(1L).firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build(),
                CustomerDTO.builder()
                        .id(2L).firstName("Randrianandrianina").lastName("Nambinina").email("rnambinina@gmail.com").build(),
                CustomerDTO.builder()
                        .id(3L).firstName("Razafindratsimba").lastName("Ninah").email("rninah@gmail.com").build()
        );
    }

    /**
     * Method under test: {@link CustomerController#findAll()}
     * @throws Exception
     */
    @Test
    public void shouldFindAllCustomers() throws Exception {
        Mockito.when(customerService.findAll()).thenReturn(customerDTOS);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(customerDTOS)));
    }

    /**
     * Method under test: {@link CustomerController#findById(Long)}
     * @throws Exception
     */
    @Test
    public void shouldFindCustomerById() throws Exception {
        Long customerId = 1L;
        Mockito.when(customerService.findById(customerId)).thenReturn(customerDTOS.get(0));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{id}", customerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(customerDTOS.get(0))));
    }

    /**
     * Method under test: {@link CustomerController#findById(Long)}
     * @throws Exception
     */
    @Test
    public void shouldNotFindCustomerByInvalidId() throws Exception {
        Long customerId = 2L;
        Mockito.when(customerService.findById(customerId)).thenThrow(CustomerNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{id}", customerId))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    /**
     * Method under test: {@link CustomerController#search(String)}
     * @throws Exception
     */
    @Test
    public void shouldSearchCustomer() throws Exception {
        String keyword = "n";
        Mockito.when(customerService.search(keyword)).thenReturn(customerDTOS);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/search?keyword="+keyword))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(customerDTOS)));
    }

    /**
     * Method under test: {@link CustomerController#save(CustomerDTO)}
     * @throws Exception
     */
    @Test
    public void shouldSaveCustomer() throws Exception {
        String expected = """
                {
                    "id":3, "firstName":"Razafindratsimba", "lastName":"Ninah", "email":"rninah@gmail.com"
                }
                """;
        Mockito.when(customerService.save(Mockito.any())).thenReturn(customerDTOS.get(2));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTOS.get(2))))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().json(expected));
    }

    /**
     * Method under test: {@link CustomerController#update(Long, CustomerDTO)}
     * @throws Exception
     */
    @Test
    public void shouldUpdateCustomer() throws Exception {
        Long customerId = 2L;
        Mockito.when(customerService.update(Mockito.eq(customerId), Mockito.any())).thenReturn(customerDTOS.get(2));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/customers/{id}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTOS.get(2))))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(customerDTOS.get(2))));
    }

    /**
     * Method under test: {@link CustomerController#delete(Long)}
     * @throws Exception
     */
    @Test
    public void shouldDeleteCustomer() throws Exception {
        Long customerId = 3L;
        doNothing().when(customerService).delete(customerId);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/{id}", customerId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
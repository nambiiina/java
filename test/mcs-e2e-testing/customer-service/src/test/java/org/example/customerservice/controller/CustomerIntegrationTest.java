package org.example.customerservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.customerservice.dto.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class CustomerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ObjectMapper objectMapper;

//    @Container
//    @ServiceConnection
//    private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:16");

    List<CustomerDTO> customerDTOS;

    @BeforeEach
    void setUp() {
        customerDTOS = List.of(
                CustomerDTO.builder().id(1L).firstName("Randrianandrianina").lastName("Thierry").email("rthierry@mail.com").build(),
                CustomerDTO.builder().id(2L).firstName("Randrianandrianina").lastName("Nambinina").email("rnambinina@mail.com").build(),
                CustomerDTO.builder().id(3L).firstName("Randrianandrianina").lastName("Harti").email("rharti@mail.com").build()
        );
    }

    /**
     * Method under test: {@link CustomerController#findAll()}
     */
    @Test
    void shouldFindAllCustomers() {
        // GIVEN

        // WHEN
        ResponseEntity<CustomerDTO[]> response = testRestTemplate.exchange("/api/customers", HttpMethod.GET, null, CustomerDTO[].class);
        List<CustomerDTO> content = Arrays.asList(Objects.requireNonNull(response.getBody()));

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(content.size()).isEqualTo(3);
        assertThat(content).usingRecursiveComparison().isEqualTo(customerDTOS);
    }

    /**
     * Method under test: {@link CustomerController#search(String)}
     */
    @Test
    void shouldFindCustomerByFirstName() {
        // GIVEN
        String keyword = "n";
        List<CustomerDTO> expected = customerDTOS.stream().filter(customerDTO -> customerDTO.getFirstName().toLowerCase().contains(keyword.toLowerCase())).toList();

        // WHEN
        ResponseEntity<CustomerDTO[]> response = testRestTemplate.exchange("/api/customers/search?keyword="+keyword, HttpMethod.GET, null, CustomerDTO[].class);
        List<CustomerDTO> content = Arrays.asList(Objects.requireNonNull(response.getBody()));

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(content.size()).isEqualTo(3);
        assertThat(content).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * Method under test: {@link CustomerController#findById(Long)}
     */
    @Test
    void shouldFindCustomerById() {
        // GIVEN
        long customerId = 1L;

        // WHEN
        ResponseEntity<CustomerDTO> response = testRestTemplate.exchange("/api/customers/"+customerId, HttpMethod.GET, null, CustomerDTO.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(customerDTOS.get(0));
    }

    /**
     * Method under test: {@link CustomerController#findById(Long)}
     */
    @Test
    void shouldNotFindCustomerById() {
        // GIVEN
        long customerId = 4L;

        // WHEN
        ResponseEntity<CustomerDTO> response = testRestTemplate.exchange("/api/customers/"+customerId, HttpMethod.GET, null, CustomerDTO.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    /**
     * Method under test: {@link CustomerController#save(CustomerDTO)}
     */
    @Test
    @Rollback
    void shouldSaveValidCustomer() {
        // GIVEN
        CustomerDTO customerDTO = CustomerDTO.builder().id(3L).firstName("Razafindratsimba").lastName("Ninah").email("rninah@mail.com").build();

        // WHEN
        ResponseEntity<CustomerDTO> response = testRestTemplate.exchange("/api/customers", HttpMethod.POST, new HttpEntity<>(customerDTO), CustomerDTO.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).usingRecursiveComparison().ignoringFields("id").isEqualTo(customerDTO);
    }

    /**
     * Method under test: {@link CustomerController#save(CustomerDTO)}
     * @throws JsonProcessingException
     */
    @Test
    @Rollback
    void shouldNotSaveInvalidCustomer() throws JsonProcessingException {
        // GIVEN
        CustomerDTO customerDTO = CustomerDTO.builder().firstName("").lastName("").email("").build();

        // WHEN
        ResponseEntity<String> response = testRestTemplate.exchange("/api/customers", HttpMethod.POST, new HttpEntity<>(customerDTO), String.class);
        Map<String, ArrayList<String>> errors = objectMapper.readValue(response.getBody(), HashMap.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(errors.keySet().size()).isEqualTo(3);
        assertThat(errors.get("firstName").size()).isEqualTo(2);
        assertThat(errors.get("lastName").size()).isEqualTo(2);
        assertThat(errors.get("email").size()).isEqualTo(2);
    }

    /**
     * Method under test: {@link CustomerController#update(Long, CustomerDTO)}
     */
    @Test
    @Rollback
    void shouldUpdateValidCustomer() {
        // GIVEN
        long customerId = 2L;
        CustomerDTO customerDTO = CustomerDTO.builder().id(2L).firstName("Razafindratsimba").lastName("Hasimino").email("rhasimino@mail.com").build();

        // WHEN
        ResponseEntity<CustomerDTO> response = testRestTemplate.exchange("/api/customers/"+customerId, HttpMethod.PUT, new HttpEntity<>(customerDTO), CustomerDTO.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).usingRecursiveComparison().ignoringFields("id").isEqualTo(customerDTO);
    }

    /**
     * Method under test: {@link CustomerController#delete(Long)}
     */
    @Test
    @Rollback
    void shouldDeleteCustomer() {
        // GIVEN
        Long customerId = 3L;

        // WHEN
        ResponseEntity<String> response = testRestTemplate.exchange("/api/customers/"+customerId, HttpMethod.DELETE, null, String.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}

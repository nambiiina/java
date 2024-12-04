package org.example.customerservice.repository;

import org.assertj.core.api.AssertionsForClassTypes;
import org.example.customerservice.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@Testcontainers
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryWithContainerTest {

    @Container
    @ServiceConnection
    private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest");

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setup() {
        customerRepository.save(Customer.builder().firstName("Thierry").lastName("Randria").email("thierry@gmail.com").build());
        customerRepository.save(Customer.builder().firstName("Nambinina").lastName("Randria").email("nambinina@gmail.com").build());
        customerRepository.save(Customer.builder().firstName("Harti").lastName("Randria").email("harti@gmail.com").build());
        customerRepository.save(Customer.builder().firstName("Ninah").lastName("Razafy").email("ninah@gmail.com").build());
        customerRepository.save(Customer.builder().firstName("Hasimino").lastName("Razafy").email("hasimino@gmail.com").build());
    }

    @Test
    void connectionEstablishedTest() {
        assertThat(postgreSQLContainer.isCreated()).isTrue();
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }

    /**
     * Method under test : {@link CustomerRepository#findByEmail(String)}
     */
    @Test
    void shouldFindCustomerByEmail() {
        // GIVEN
        String givenEmail = "harti@gmail.com";
        // WHEN
        Optional<Customer> result = customerRepository.findByEmail(givenEmail);
        // THEN
        assertThat(result).isPresent();
    }

    @Test
    void shouldNotFindCustomerByEmail() {
        // GIVEN
        String givenEmail = "xxx@gmail.com";
        // WHEN
        Optional<Customer> result = customerRepository.findByEmail(givenEmail);
        // THEN
        assertThat(result).isEmpty();
    }

    @Test
    void shouldFindCustomerByFirstName() {
        // GIVEN
        String givenKeyword = "ha";
        List<Customer> expected = List.of(
                Customer.builder().firstName("Harti").lastName("Randria").email("harti@gmail.com").build(),
                Customer.builder().firstName("Hasimino").lastName("Razafy").email("hasimino@gmail.com").build()
        );
        // WHEN
        List<Customer> result = customerRepository.findByFirstNameContainsIgnoreCase(givenKeyword);
        // THEN
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
    }
}

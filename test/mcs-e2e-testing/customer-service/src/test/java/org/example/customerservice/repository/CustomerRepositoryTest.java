package org.example.customerservice.repository;

import org.example.customerservice.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@DataJpaTest
/**
 * 1- Configuration d'une base de données en mémoire
 * 2- Configuration d'un contexte d'application restreint : Seuls les composants pertinents pour les tests du repo sont chargés dans le contexte.
 * 3- Trasaction automatique qui effectue un rollback à chaque fin de test.
 */
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        customerRepository.save(Customer.builder()
                .firstName("Mohamed")
                .lastName("Thierry")
                .email("rthierry@mail.com")
                .build());
        customerRepository.save(Customer.builder()
                .firstName("Imane")
                .lastName("Nambinina")
                .email("rnambinina@mail.com")
                .build());
        customerRepository.save(Customer.builder()
                .firstName("Randrianandrianina")
                .lastName("Harti")
                .email("rharti@mail.com")
                .build());
    }

    /**
     * Method under test: {@link CustomerRepository#findByEmail(String)}
     */
    @Test
    public void shouldFindCustomerByEmail() {
        // GIVEN
        String givenEmail = "rthierry@mail.com";

        // WHEN
        Optional<Customer> result = customerRepository.findByEmail(givenEmail);

        // THEN
        assertThat(result).isPresent();
    }

    /**
     * Method under test: {@link CustomerRepository#findByEmail(String)}
     */
    @Test
    public void shouldNotFindCustomerByEmail() {
        // GIVEN
        String givenEmail = "xxx@mail.com";

        // WHEN
        Optional<Customer> result = customerRepository.findByEmail(givenEmail);

        // THEN
        assertThat(result).isEmpty();
    }

    /**
     * Method under test: {@link CustomerRepository#findByFirstNameContainsIgnoreCase(String)}
     */
    @Test
    public void shouldFindCustomersByFirstName() {
        // GIVEN
        String keyword = "e";
        List<Customer> expected = List.of(
            Customer.builder().firstName("Mohamed").lastName("Thierry").email("rthierry@mail.com").build(),
            Customer.builder().firstName("Imane").lastName("Nambinina").email("rnambinina@mail.com").build()
        );

        // WHEN
        List<Customer> result = customerRepository.findByFirstNameContainsIgnoreCase(keyword);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(expected.size());
        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
    }
}
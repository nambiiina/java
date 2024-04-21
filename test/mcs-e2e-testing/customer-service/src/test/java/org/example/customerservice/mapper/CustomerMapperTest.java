package org.example.customerservice.mapper;

import org.example.customerservice.dto.CustomerDTO;
import org.example.customerservice.entities.Customer;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.org.bouncycastle.est.LimitedSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CustomerMapperTest {
    CustomerMapper underTest = new CustomerMapper();
    @Test
    public void shouldMapCustomerToCustomerDTO() {
        // GIVEN
        Customer givenCustomer = Customer.builder()
                .id(1L)
                .firstName("Randrianandrianina")
                .lastName("Thierry")
                .email("rthierry@gmail.com")
                .build();
        CustomerDTO expected = CustomerDTO.builder()
                .id(1L)
                .firstName("Randrianandrianina")
                .lastName("Thierry")
                .email("rthierry@gmail.com")
                .build();

        // WHEN
        CustomerDTO result = underTest.fromCustomer(givenCustomer);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void shouldMapCustomerDTOToCustomer() {
        // GIVEN
        CustomerDTO givenCustomerDTO = CustomerDTO.builder()
                .id(1L)
                .firstName("Randrianandrianina")
                .lastName("Thierry")
                .email("rthierry@gmail.com")
                .build();
        Customer expected = Customer.builder()
                .id(1L)
                .firstName("Randrianandrianina")
                .lastName("Thierry")
                .email("rthierry@gmail.com")
                .build();

        // WHEN
        Customer result = underTest.fromCustomerDTO(givenCustomerDTO);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void shouldMapListOfCustomersToListOfCustomerDTOs() {
        // GIVEN
        List<Customer> givenCustomers = List.of(
                Customer.builder()
                    .id(1L).firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build(),
                Customer.builder()
                    .id(2L).firstName("Randrianandrianina").lastName("Nambinina").email("rnambinina@gmail.com").build(),
                Customer.builder()
                        .id(3L).firstName("Razafindratsimba").lastName("Ninah").email("rninah@gmail.com").build()
        );
        List<CustomerDTO> expected = List.of(
                CustomerDTO.builder()
                        .id(1L).firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build(),
                CustomerDTO.builder()
                        .id(2L).firstName("Randrianandrianina").lastName("Nambinina").email("rnambinina@gmail.com").build(),
                CustomerDTO.builder()
                        .id(3L).firstName("Razafindratsimba").lastName("Ninah").email("rninah@gmail.com").build()
        );

        // WHEN
        List<CustomerDTO> result = underTest.fromListCustomers(givenCustomers);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void shouldNotMapNullCustomerToCustomerDTO() {
        // GIVEN
        Customer givenCustomer = null;

        // WHEN
        // THEN
        assertThatThrownBy(() -> underTest.fromCustomer(givenCustomer)).isInstanceOf(IllegalArgumentException.class);
    }
}
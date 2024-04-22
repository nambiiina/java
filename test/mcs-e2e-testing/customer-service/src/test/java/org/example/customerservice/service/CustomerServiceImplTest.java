package org.example.customerservice.service;

import org.example.customerservice.dto.CustomerDTO;
import org.example.customerservice.entities.Customer;
import org.example.customerservice.exceptions.CustomerNotFoundException;
import org.example.customerservice.exceptions.EmailAlreadyExistException;
import org.example.customerservice.mapper.CustomerMapper;
import org.example.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    /**
     * @Mock est utilisé pour créer des objets fictifs pour les dépendances d'une class à tester (CustomerServiceImpl)
     */
    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private CustomerRepository customerRepository;

    /**
     * @InjectMocks est utilisé pour injecter les mocks créés avec @Mock de la classe à tester (CustomerServiceImpl)
     */
    @InjectMocks
    private CustomerServiceImpl underTest;

    /**
     * Method under test: {@link CustomerServiceImpl#save(CustomerDTO)}
     */
    @Test
    public void shouldSaveNewCustomer() {
        // GIVEN
        CustomerDTO customerDTO = CustomerDTO.builder().firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build();
        Customer customer = Customer.builder().firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build();
        Customer savedCustomer = Customer.builder().id(1L).firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build();
        CustomerDTO expected = CustomerDTO.builder().id(1L).firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build();

        // WHEN
        Mockito.when(customerRepository.findByEmail(customerDTO.getEmail())).thenReturn(Optional.empty());
        Mockito.when(customerMapper.fromCustomerDTO(customerDTO)).thenReturn(customer);
        Mockito.when(customerRepository.save(customer)).thenReturn(savedCustomer);
        Mockito.when(customerMapper.fromCustomer(savedCustomer)).thenReturn(expected);

        CustomerDTO result = underTest.save(customerDTO);

        // THEN
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * Methode under test: {@link CustomerServiceImpl#save(CustomerDTO)}
     */
    @Test
    public void shouldNotSaveNewCustomerWhenEmailExist() {
        // GIVEN
        CustomerDTO customerDTO = CustomerDTO.builder().firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build();
        Customer customer = Customer.builder().id(5L).firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build();

        // WHEN
        Mockito.when(customerRepository.findByEmail(customerDTO.getEmail())).thenReturn(Optional.of(customer));

        //THEN
        assertThatThrownBy(() -> underTest.save(customerDTO)).isInstanceOf(EmailAlreadyExistException.class);
    }

    /**
     * Method under test: {@link CustomerServiceImpl#findAll()}
     */
    @Test
    public void shouldGetAllCustomers() {
        // GIVEN
        List<Customer> customers = List.of(
                Customer.builder().firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build(),
                Customer.builder().firstName("Randrianandrianina").lastName("Nambinina").email("rnambinina@gmail.com").build()
        );
        List<CustomerDTO> expected = List.of(
                CustomerDTO.builder().firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build(),
                CustomerDTO.builder().firstName("Randrianandrianina").lastName("Nambinina").email("rnambinina@gmail.com").build()
        );

        // WHEN
        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        Mockito.when(customerMapper.fromListCustomers(customers)).thenReturn(expected);

        List<CustomerDTO> result = underTest.findAll();

        // THEN
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * Method under test: {@link CustomerServiceImpl#findById(Long)}
     */
    @Test
    public void shouldFindCustomerById() {
        // GIVEN
        Long customerId = 1L;
        Customer customer = Customer.builder().firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build();
        CustomerDTO expected = CustomerDTO.builder().firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build();

        // WHEN
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        Mockito.when(customerMapper.fromCustomer(customer)).thenReturn(expected);

        CustomerDTO result = underTest.findById(customerId);

        // THEN
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * Method under test: {@link CustomerServiceImpl#findById(Long)}
     */
    @Test
    public void shouldNotFindCustomerById() {
        // GIVEN
        Long customerId = 2L;

        // WHEN
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // THEN
        assertThatThrownBy(() -> underTest.findById(customerId))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessage(null);
    }

    /**
     * Methode under test: {@link CustomerServiceImpl#search(String)}
     */
    @Test
    public void shouldSearchCustomer() {
        // GIVEN
        String keyword = "m";
        List<Customer> customers = List.of(
                Customer.builder().firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build(),
                Customer.builder().firstName("Randrianandrianina").lastName("Nambinina").email("rnambinina@gmail.com").build()
        );
        List<CustomerDTO> expected = List.of(
                CustomerDTO.builder().firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build(),
                CustomerDTO.builder().firstName("Randrianandrianina").lastName("Nambinina").email("rnambinina@gmail.com").build()
        );

        // WHEN
        Mockito.when(customerRepository.findByFirstNameContainsIgnoreCase(keyword)).thenReturn(customers);
        Mockito.when(customerMapper.fromListCustomers(customers)).thenReturn(expected);

        List<CustomerDTO> result = underTest.search(keyword);

        // THEN
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * Methode under test: {@link CustomerServiceImpl#update(Long, CustomerDTO)}
     */
    @Test
    public void shouldUpdateCustomer() {
        // GIVEN
        Long customerId = 3L;
        CustomerDTO customerDTO = CustomerDTO.builder().firstName("Randrianandrianina").lastName("Harti").email("rharti@gmail.com").build();
        Customer customer = Customer.builder().id(customerId).firstName("Randrianandrianina").lastName("Harti").email("rharti@gmail.com").build();
        Customer updatedCustomer = Customer.builder().id(customerId).firstName("Randrianandrianina").lastName("Harti").email("rharti@gmail.com").build();
        CustomerDTO expected = CustomerDTO.builder().id(customerId).firstName("Randrianandrianina").lastName("Harti").email("rharti@gmail.com").build();

        // WHEN
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        customerDTO.setId(customerId);
        Mockito.when(customerMapper.fromCustomerDTO(customerDTO)).thenReturn(customer);
        Mockito.when(customerRepository.save(customer)).thenReturn(updatedCustomer);
        Mockito.when(customerMapper.fromCustomer(updatedCustomer)).thenReturn(expected);

        CustomerDTO result = underTest.update(customerId, customerDTO);

        // THEN
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    /**
     * Methode under test: {@link CustomerServiceImpl#update(Long, CustomerDTO)}
     */
    @Test
    public void shouldNotUpdateCustomerWhenCustomerNotExist() {
        // GIVEN
        Long customerId = 4L;
        CustomerDTO customerDTO = CustomerDTO.builder().firstName("Randrianandrianina").lastName("Harti").email("rharti@gmail.com").build();

        // WHEN
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // THEN
        assertThatThrownBy(() -> underTest.update(customerId, customerDTO)).isInstanceOf(CustomerNotFoundException.class);
    }

    /**
     * Method under test: {@link CustomerServiceImpl#delete(Long)}
     */
    @Test
    public void shouldDeleteCustomer() {
        // GIVEN
        Long customerId = 9L;
        Customer customer = Customer.builder().id(customerId).firstName("Randrianandrianina").lastName("Thierry").email("rthierry@gmail.com").build();

        // WHEN
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        underTest.delete(customerId);

        // THEN
        Mockito.verify(customerRepository).deleteById(customerId);
    }

    /**
     * Methode under test: {@link CustomerServiceImpl#delete(Long)}
     */
    @Test
    public void shouldNotDeleteCustomerIfNotExist() {
        // GIVEN
        Long customerId = 10L;

        // WHEN
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // THEN
        assertThatThrownBy(() -> underTest.delete(customerId)).isInstanceOf(CustomerNotFoundException.class);
    }

}
package pl.TomDal.RentACarApplication.services.dao;

import pl.TomDal.RentACarApplication.domain.Customer;

import java.util.Optional;

public interface CustomerDAO {
    Optional<Customer> findCustomerByEmail(String email);

    void saveCustomer(Customer customer);
}

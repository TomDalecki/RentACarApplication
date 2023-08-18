package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.domain.Customer;
import pl.TomDal.RentACarApplication.repository.jpa.CustomerJpaRepository;
import pl.TomDal.RentACarApplication.repository.mapper.CustomerEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.CustomerDAO;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomerRepository implements CustomerDAO {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerEntityMapper customerEntityMapper;

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return customerJpaRepository.findByEmail(email)
                .map(customerEntityMapper::mapFromEntity);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerJpaRepository.save(customerEntityMapper.mapToEntity(customer));
    }
}

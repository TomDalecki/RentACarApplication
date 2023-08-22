package pl.TomDal.RentACarApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.controllers.dto.CustomerDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CustomerMapper;
import pl.TomDal.RentACarApplication.domain.Customer;
import pl.TomDal.RentACarApplication.services.dao.CustomerDAO;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    CustomerDAO customerDAO;
    CustomerMapper customerMapper;

    public Optional<Customer> findCustomerByEmail(String email){
        return customerDAO.findCustomerByEmail(email);
    }

    @Transactional
    public void saveCustomer(CustomerDTO customerDTO){
        customerDAO.saveCustomer(customerMapper.mapFromDTO(customerDTO));
    }
}

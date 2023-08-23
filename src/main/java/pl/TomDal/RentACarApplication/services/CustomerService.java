package pl.TomDal.RentACarApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.controllers.dto.CustomerDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CustomerMapper;
import pl.TomDal.RentACarApplication.domain.Customer;
import pl.TomDal.RentACarApplication.exceptions.NotFoundException;
import pl.TomDal.RentACarApplication.services.dao.CustomerDAO;

@Service
@AllArgsConstructor
public class CustomerService {
    CustomerDAO customerDAO;
    CustomerMapper customerMapper;

    public Customer findCustomerByEmail(String email){
        return customerDAO.findCustomerByEmail(email).orElseThrow(
                ()->new NotFoundException("Could not find customer with email: [%s]".formatted(email)));
    }

    @Transactional
    public void saveCustomer(CustomerDTO customerDTO){
        customerDAO.saveCustomer(customerMapper.mapFromDTO(customerDTO));
    }
}

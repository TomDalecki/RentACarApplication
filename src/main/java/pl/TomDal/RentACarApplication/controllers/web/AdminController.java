package pl.TomDal.RentACarApplication.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.TomDal.RentACarApplication.controllers.dto.AddressDTO;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.CustomerDTO;
import pl.TomDal.RentACarApplication.controllers.dto.EmployeeDTO;
import pl.TomDal.RentACarApplication.services.CarToRentService;
import pl.TomDal.RentACarApplication.services.CustomerService;
import pl.TomDal.RentACarApplication.services.EmployeeService;

@Controller
@RequiredArgsConstructor
public class AdminController {

    static final String ADMIN = "/admin";

    private final CustomerService customerService;
    private final CarToRentService carToRentService;
    private final EmployeeService employeeService;


    @GetMapping(value = ADMIN)
    public String adminPanel() {
        return "admin_panel";
    }

    @PostMapping(value = "/admin/saveCar")
    public String saveNewCar(CarToRentDTO carToRentDTO) {
        carToRentService.saveCar(carToRentDTO);
        return "new_car_summary";
    }

    @PostMapping(value = "/admin/saveCustomer")
    public String saveNewCustomer(CustomerDTO customerDTO, AddressDTO addressDTO) {
        customerService.saveCustomer(customerDTO);
        return "new_customer_summary";
    }

    @PostMapping(value = "/admin/saveEmployee")
    public String saveNewEmployee(EmployeeDTO employeeDTO) {
        employeeService.saveEmployee(employeeDTO);
        return "new_employee_summary";
    }
}

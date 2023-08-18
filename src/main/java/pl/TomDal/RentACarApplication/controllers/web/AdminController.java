package pl.TomDal.RentACarApplication.controllers.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.TomDal.RentACarApplication.controllers.dto.*;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.entity.enums.CarProducer;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.CarType;
import pl.TomDal.RentACarApplication.services.CarToRentService;
import pl.TomDal.RentACarApplication.services.CustomerService;
import pl.TomDal.RentACarApplication.services.EmployeeService;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    static final String ADMIN = "/admin";

    private final CustomerService customerService;
    private final CarToRentService carToRentService;
    private final EmployeeService employeeService;
    private final CarToRentMapper carToRentMapper;



    @GetMapping(value = ADMIN)
    public String adminPanel(Model model, CarToRentDTO carToRentDTO) {

        List<CarToRentDTO> availableCarsToRent = carToRentService.findAvailableCars().stream()
                .map(carToRentMapper::mapToDTO).toList();

        List<CarToRentDTO> carsWithTechnicalIssue = carToRentService
                .findCarsToRentByCarStatus(CarStatus.TECHNICAL_ISSUE).stream()
                .map(carToRentMapper::mapToDTO).toList();

        List<CarToRentDTO> carsWithInsuranceIssue = carToRentService
                .findCarsToRentByCarStatus(CarStatus.DISABLED_BY_INSURANCE).stream()
                .map(carToRentMapper::mapToDTO).toList();

        CarType[] carTypes = CarType.values();
        Arrays.sort(carTypes, Comparator.comparing(Enum::name));

        CarProducer[] carProducers = CarProducer.values();
        Arrays.sort(carProducers, Comparator.comparing(Enum::name));

        model.addAttribute("availableCarsToRentDTOs", availableCarsToRent);
        model.addAttribute("carsWithTechnicalIssueDTOs", carsWithTechnicalIssue);
        model.addAttribute("carsWithInsuranceIssueDTOs", carsWithInsuranceIssue);
        model.addAttribute("carTypes", carTypes);
        model.addAttribute("carProducers", carProducers);
        model.addAttribute("carToRentDTO", carToRentDTO);

        return "admin_panel";
    }

    @PostMapping(value = "/admin/saveCar")
    public String saveNewCar(@Valid @ModelAttribute CarToRentDTO carToRentDTO) {
        carToRentDTO.setCarStatus(CarStatus.TO_RENT);
        carToRentService.saveCar(carToRentDTO);
        return "redirect:/admin";
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

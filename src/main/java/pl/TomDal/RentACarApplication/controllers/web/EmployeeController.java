package pl.TomDal.RentACarApplication.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.services.CarToRentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    static final String EMPLOYEE = "/employee";

    private final CarToRentService carToRentService;
    private final CarToRentMapper carToRentMapper;

    @GetMapping(value = EMPLOYEE)
    public String employeePanel (Model model) {

        List<CarToRentDTO> carsBookedByCustomers = carToRentService.findCarsToRentByCarStatus(CarStatus.RESERVED).stream()
                .map(carToRentMapper::mapToDTO).toList();

        model.addAttribute("carsBookedByCustomersDTOs", carsBookedByCustomers);
        return "employee_panel";
    }
}

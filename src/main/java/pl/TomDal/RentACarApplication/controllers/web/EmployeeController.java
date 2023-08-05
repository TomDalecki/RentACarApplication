package pl.TomDal.RentACarApplication.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.TomDal.RentACarApplication.controllers.dto.OrderAndCarDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.OrderAndCarMapper;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;
import pl.TomDal.RentACarApplication.services.RentalOrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    static final String EMPLOYEE = "/employee";

    private final RentalOrderService rentalOrderService;
    private final OrderAndCarMapper orderAndCarMapper;

    @GetMapping(value = EMPLOYEE)
    public String employeePanel (Model model, Integer rentalOrderId) {

        List<OrderAndCarDTO> carsBookedByCustomers = rentalOrderService
                .findOrdersByStatusJoinedWithCars(OrderStatus.NEW_ORDER).stream()
                .map(orderAndCarMapper::mapToDTO).toList();

        model.addAttribute("carsBookedByCustomersDTOs", carsBookedByCustomers);
        model.addAttribute("rentalOrderId", rentalOrderId);
        return "employee_panel";
    }

    @PostMapping(value = "/employee/accept")
    public String reservationStatusChange (Model model, Integer rentalOrderId) {
        rentalOrderService.changeOrderStatusByOrderId(rentalOrderId, OrderStatus.ACCEPTED);
        return "redirect:/employee";
    }
}

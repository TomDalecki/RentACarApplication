package pl.TomDal.RentACarApplication.controllers.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.TomDal.RentACarApplication.controllers.dto.OrderAndCarDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.OrderAndCarMapper;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;
import pl.TomDal.RentACarApplication.services.RentalOrderService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    static final String EMPLOYEE = "/employee";

    private final RentalOrderService rentalOrderService;
    private final OrderAndCarMapper orderAndCarMapper;
    private final LocalDate date = LocalDate.now();

    @GetMapping(value = EMPLOYEE)
    public String employeePanel (Model model) {

        List<OrderAndCarDTO> carsBookedByCustomers = rentalOrderService
                .findOrdersByStatusJoinedWithCars(OrderStatus.NEW_ORDER).stream()
                .map(orderAndCarMapper::mapToDTO).toList();

        model.addAttribute("carsBookedByCustomersDTOs", carsBookedByCustomers);
        model.addAttribute("date", date);
        return "employee_panel";
    }

    @PostMapping(value = "/employee/accept")
    public String reservationStatusAccept (Model model, Integer rentalOrderId) {
        rentalOrderService.changeOrderStatusByOrderId(rentalOrderId, OrderStatus.ACCEPTED);
        return "redirect:/employee";
    }

    @PostMapping(value = "/employee/cancel")
    public String reservationStatusCancel (Model model, Integer rentalOrderId) {
        rentalOrderService.changeOrderStatusByOrderId(rentalOrderId, OrderStatus.CANCELED);
        return "redirect:/employee";
    }

    @PostMapping(value = "/employee/modify")
    public String reservationStatusModify (Model model, Integer rentalOrderId, LocalDate newRentalStartDate,
                                           LocalDate newRentalEndDate) {

        rentalOrderService.changeRentalPeriodByOrderId(rentalOrderId, newRentalStartDate, newRentalEndDate);
        return "redirect:/employee";
    }
}

package pl.TomDal.RentACarApplication.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.CustomerRentalOrderDTO;
import pl.TomDal.RentACarApplication.controllers.dto.OrderAndCarDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.OrderAndCarMapper;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.RentalOrderMapper;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.services.CarToRentService;
import pl.TomDal.RentACarApplication.services.CustomerService;
import pl.TomDal.RentACarApplication.services.PriceCalculationService;
import pl.TomDal.RentACarApplication.services.RentalOrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    static final String CUSTOMER = "/customer";
    private final CarToRentService carToRentService;
    private final CarToRentMapper carToRentMapper;
    private final RentalOrderMapper rentalOrderMapper;
    private final RentalOrderService rentalOrderService;
    private final PriceCalculationService priceCalculationService;
    private final CustomerService customerService;
    private final OrderAndCarMapper orderAndCarMapper;
    private List<CarToRentDTO> availableCarsToRent;
    private final CustomerRentalOrderDTO customerOrderDTO = new CustomerRentalOrderDTO();

    @GetMapping(value = CUSTOMER)
    public String customerPanel(Model model) {
        availableCarsToRent = carToRentService.findAvailableCars().stream()
                .map(carToRentMapper::mapToDTO).toList();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userEmail = authentication.getName();
        customerOrderDTO.setCustomerEmail(authentication.getName());

        model.addAttribute("availableCarsToRentDTOs", availableCarsToRent);
        model.addAttribute("customerRentalOrderDTO", customerOrderDTO);
//        model.addAttribute("userEmail", userEmail);
        return "customer_panel";
    }

    @PostMapping("/check")
    public ModelAndView availableCarsToRentInDate(
            @ModelAttribute CustomerRentalOrderDTO customerRentalOrderDTO,
            Model model
    ){
            availableCarsToRent = carToRentService
                    .findAvailableCarsByStartEndDate(customerRentalOrderDTO.getRentalStartDate(),
                            customerRentalOrderDTO.getRentalEndDate()).stream()
                .map(carToRentMapper::mapToDTO).toList();


        model.addAttribute("availableCarsToRentInDateDTOs", availableCarsToRent);
        model.addAttribute("customerRentalOrderDTO", customerRentalOrderDTO);

        customerOrderDTO.setRentalStartDate(customerRentalOrderDTO.getRentalStartDate());
        customerOrderDTO.setRentalEndDate(customerRentalOrderDTO.getRentalEndDate());

        return new ModelAndView("choosing_panel");
    }

    @PostMapping("/saveOrder")
    public ModelAndView saveRentalOrder (
        @ModelAttribute CustomerRentalOrderDTO customerRentalOrderDTO, Model model)
    {
        model.addAttribute("customerRentalOrderDTO", customerRentalOrderDTO);

        customerOrderDTO.setCarType(customerRentalOrderDTO.getCarType());
        customerOrderDTO.setSelectedCarToRentId(customerRentalOrderDTO.getSelectedCarToRentId());

        RentalOrder rentalOrder = rentalOrderMapper.mapFromDTO(customerOrderDTO, priceCalculationService, customerService);
        rentalOrderService.saveRentalOrder(rentalOrder);

        OrderAndCarDTO orderSumary = orderAndCarMapper.mapToDTO(rentalOrderService
                .findOrderByRentalOrderIdJoinedWithCar(rentalOrder.getRentNumber()));

        model.addAttribute("orderSumary", orderSumary);

        return new ModelAndView("rental_order_summary");
    }
}



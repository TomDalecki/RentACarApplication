package pl.TomDal.RentACarApplication.controllers.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.CustomerRentalOrderDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.RentalOrderMapper;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.services.CarToRentService;
import pl.TomDal.RentACarApplication.services.PriceCalculationService;
import pl.TomDal.RentACarApplication.services.RentalOrderService;
import pl.TomDal.RentACarApplication.services.dao.CustomerDAO;

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
    private final CustomerDAO customerDAO;
    private List<CarToRentDTO> availableCarsToRent;
    private CarToRentDTO selectedCarDTO;

    @GetMapping(value = CUSTOMER)
    public String customerPanel(Model model) {

        List<CarToRentDTO> allCars = carToRentService.findAllCars().stream()
                .map(carToRentMapper::mapToDTO).toList();

        List<CarToRentDTO> carsToRent = carToRentService.findAvailableCars().stream()
                .map(carToRentMapper::mapToDTO).toList();
        availableCarsToRent = carsToRent;

        model.addAttribute("allCarsDTOs", allCars);
        model.addAttribute("availableCarsToRentDTOs", carsToRent);
        return "customer_panel";
    }

    @GetMapping(value = "/rent/{carId}")
    public ModelAndView rentCar(@PathVariable Integer carId, Model model) {
        CarToRentDTO carToRentDTO = availableCarsToRent.stream()
                .filter(car -> car.getCarToRentId().equals(carId)).findFirst().orElse(null);
        selectedCarDTO = carToRentDTO;

        model.addAttribute("customerRentalOrderDTO", new CustomerRentalOrderDTO());
        model.addAttribute("selectedCarToRent", carToRentDTO);
        return new ModelAndView("rent_panel");
    }


    @PostMapping(value = "/saveOrder")
    public String saveRentalOrder (
        @Valid @ModelAttribute("customerRentOrderDTO") CustomerRentalOrderDTO customerRentalOrderDTO)
    {
        CarToRent carToRent = carToRentMapper.mapFromDTO(selectedCarDTO);
        customerRentalOrderDTO = customerRentalOrderDTO
                .withCarType(carToRent.getCarType())
                .withSelectedCarToRentId(carToRent.getCarToRentId());

        RentalOrder rentalOrder = rentalOrderMapper.mapFromDTO(customerRentalOrderDTO, priceCalculationService, customerDAO);
        rentalOrder = rentalOrder.withCarToRent(carToRent);
        rentalOrderService.saveRentalOrder(rentalOrder);
        carToRentService.changeCarStatusAfterCustomerReservation(carToRent.getCarToRentId(), CarStatus.RESERVED);
        return "rental_order_summary";
    }
}



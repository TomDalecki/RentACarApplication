package pl.TomDal.RentACarApplication.controllers.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.services.CarToRentService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class CustomerController {

    static final String CUSTOMER = "/customer";

    private final CarToRentService carToRentService;
    private final CarToRentMapper carToRentMapper;


    @GetMapping(value = CUSTOMER)
    public String customerPage(Model model) {

        List<CarToRentDTO> allCars = carToRentService.findAllCars().stream()
                .map(carToRentMapper::mapToDTO).toList();

        List<CarToRentDTO> carsToRent = carToRentService.findAvailableCars().stream()
                .map(carToRentMapper::mapToDTO).toList();

        model.addAttribute("allCarsDTOs", allCars);
        model.addAttribute("availableCarsToRentDTOs", carsToRent);
//        model.addAttribute("availableMechanicDTOs", availableMechanics);

        return "customer_panel";
    }

}

package pl.TomDal.RentACarApplication.controllers.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.TomDal.RentACarApplication.controllers.dto.CarInsuranceDTO;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.CredentialDetailsDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.entity.enums.*;
import pl.TomDal.RentACarApplication.services.CarToRentService;
import pl.TomDal.RentACarApplication.services.UserService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    static final String ADMIN = "/admin";

    private final CarToRentService carToRentService;
    private final CarToRentMapper carToRentMapper;
    private final UserService userService;

    @GetMapping(value = ADMIN)
    public String adminPanel(Model model, CarToRentDTO carToRentDTO) {

        List<CarToRentDTO> availableCarsToRent = carToRentService.findAvailableCars().stream()
                .map(carToRentMapper::mapToDTO).toList();

        List<CarToRentDTO> carsWithTechnicalIssue = new java.util.ArrayList<>(carToRentService
                .findCarsToRentByCarStatus(CarStatus.TECHNICAL_ISSUE).stream()
                .map(carToRentMapper::mapToDTO).toList());

        carsWithTechnicalIssue.addAll(carToRentService
                .findCarsToRentByCarStatus(CarStatus.DISABLED_BY_TECH_INSP).stream()
                .map(carToRentMapper::mapToDTO).toList());

        List<CarToRentDTO> carsWithInsuranceIssue = carToRentService
                .findCarsToRentByCarStatus(CarStatus.DISABLED_BY_INSURANCE).stream()
                .map(carToRentMapper::mapToDTO).toList();

        CarType[] carTypes = CarType.values();
        Arrays.sort(carTypes, Comparator.comparing(Enum::name));

        CarProducer[] carProducers = CarProducer.values();
        Arrays.sort(carProducers, Comparator.comparing(Enum::name));

        CarColor[] carColors = CarColor.values();
        Arrays.sort(carColors, Comparator.comparing(Enum::name));

        InsuranceType[] insuranceType = InsuranceType.values();
        Arrays.sort(insuranceType, Comparator.comparing(Enum::name));

        InsuranceCompanies[] insuranceCompanies = InsuranceCompanies.values();
        Arrays.sort(insuranceCompanies, Comparator.comparing(Enum::name));

        CarStatus[] carStatuses = CarStatus.values();
        Arrays.sort(carStatuses, Comparator.comparing(Enum::name));

        model.addAttribute("availableCarsToRentDTOs", availableCarsToRent);
        model.addAttribute("carsWithTechnicalIssueDTOs", carsWithTechnicalIssue);
        model.addAttribute("carsWithInsuranceIssueDTOs", carsWithInsuranceIssue);
        model.addAttribute("carTypes", carTypes);
        model.addAttribute("carProducers", carProducers);
        model.addAttribute("carColors", carColors);
        model.addAttribute("insuranceType", insuranceType);
        model.addAttribute("insuranceCompanies", insuranceCompanies);
        model.addAttribute("carStatuses", carStatuses);
        model.addAttribute("carToRentDTO", carToRentDTO);
        model.addAttribute("credentialDetailsDTO", new CredentialDetailsDTO());
        model.addAttribute("carInsuranceDTO", new CarInsuranceDTO());

        return "admin_panel";
    }

    @PostMapping(value = "/admin/saveCar")
    public String saveNewCar(@Valid @ModelAttribute CarToRentDTO carToRentDTO, LocalDate technicalInspectionDate) {
        carToRentDTO.setCarStatus(CarStatus.DISABLED_BY_INSURANCE);
        carToRentService.saveCar(carToRentDTO, technicalInspectionDate);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/saveCustomer")
    public String saveNewCustomer(@Valid @ModelAttribute CredentialDetailsDTO credentialDetailsDTO) {
        userService.createUser(credentialDetailsDTO.getEmail(), credentialDetailsDTO.getPassword(), UserRole.USER);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/saveEmployee")
    public String saveNewEmployee(@Valid @ModelAttribute CredentialDetailsDTO credentialDetailsDTO) {
        userService.createUser(credentialDetailsDTO.getEmail(), credentialDetailsDTO.getPassword(), UserRole.EMPLOYEE);
        return "redirect:/admin";
    }
}

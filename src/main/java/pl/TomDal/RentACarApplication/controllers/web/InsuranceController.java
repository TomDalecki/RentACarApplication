package pl.TomDal.RentACarApplication.controllers.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.TomDal.RentACarApplication.controllers.dto.CarInsuranceDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarInsuranceMapper;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.enums.InsuranceCompanies;
import pl.TomDal.RentACarApplication.entity.enums.InsuranceType;
import pl.TomDal.RentACarApplication.services.InsuranceService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class InsuranceController {
    private final InsuranceService insuranceService;
    private final CarInsuranceMapper carInsuranceMapper;

    @GetMapping(value = "/insurance/insuranceDetails")
    public String showInsuranceDetails(Integer carToRentId, Model model) {

        List<CarInsuranceDTO> insurancesDTOs = insuranceService.findInsurances(carToRentId).stream()
                .map(carInsuranceMapper::mapToDTO).toList();

        InsuranceType[] insuranceType = InsuranceType.values();
        Arrays.sort(insuranceType, Comparator.comparing(Enum::name));

        InsuranceCompanies[] insuranceCompanies = InsuranceCompanies.values();
        Arrays.sort(insuranceCompanies, Comparator.comparing(Enum::name));

        model.addAttribute("insurancesDTOs", insurancesDTOs);
        model.addAttribute("insuranceType", insuranceType);
        model.addAttribute("insuranceCompanies", insuranceCompanies);
        model.addAttribute("carInsuranceDTO", new CarInsuranceDTO());

        return "insurance_details_panel";
    }

    @PostMapping(value = "/insurance/saveInsurance")
    public String updateInsuranceDate(@Valid @ModelAttribute CarInsuranceDTO carInsuranceDTO, Integer carToRentId) {
        insuranceService.saveInsurance(carInsuranceMapper.mapFromDTO(carInsuranceDTO
                .withCarToRent(CarToRent.builder().carToRentId(carToRentId).build())));
        return "redirect:/insurance/insuranceDetails?carToRentId=" + carToRentId.toString();
    }

    @PostMapping(value = "/insurance/updateInsurance")
    public String updateInsuranceEndDate(Integer carToRentId, Integer insuranceId, LocalDate insuranceEndDate) {
        insuranceService.updateUpdateInsuranceEndDate(insuranceId, insuranceEndDate);
        return "redirect:/insurance/insuranceDetails?carToRentId=" + carToRentId.toString();
    }
}

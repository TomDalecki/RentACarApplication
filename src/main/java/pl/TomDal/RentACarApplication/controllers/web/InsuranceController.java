package pl.TomDal.RentACarApplication.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.TomDal.RentACarApplication.controllers.dto.CarInsuranceDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarInsuranceMapper;
import pl.TomDal.RentACarApplication.services.InsuranceService;

import java.time.LocalDate;
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

        model.addAttribute("insurancesDTOs", insurancesDTOs);

        return "insurance_details_panel";
    }

    @PostMapping(value = "/insurance/updateInsurance")
    public String updateInsuranceEndDate(Integer carToRentId, LocalDate insuranceEndDate) {
        //insuranceService.updateUpdateInsuranceEndDate(carToRentId, insuranceEndDate);

        return "redirect:/admin";
    }


}

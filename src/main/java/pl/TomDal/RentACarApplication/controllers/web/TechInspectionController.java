package pl.TomDal.RentACarApplication.controllers.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.TomDal.RentACarApplication.controllers.dto.TechnicalInspectionDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.TechnicalInspectionMapper;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.services.CarToRentService;
import pl.TomDal.RentACarApplication.services.TechnicalInspectionService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class TechInspectionController {
    private final TechnicalInspectionService technicalInspectionService;
    private final TechnicalInspectionMapper technicalInspectionMapper;
    private final CarToRentService carToRentService;

    @GetMapping(value = "/tech/technicalInspection")
    public String showTechnicalInspectionDetails(Integer carToRentId, Model model) {

        TechnicalInspectionDTO technicalInspectionDTO = technicalInspectionMapper
                .mapToDTO(technicalInspectionService.findInspectionDetailByCarId(carToRentId));

        model.addAttribute("technicalInspectionDTO", technicalInspectionDTO);
        return "technical_inspection_panel";
    }

    @PostMapping(value = "/tech/technicalInspectionUpdate")
    public String updateInsuranceDate(Integer technicalInspectionId, Integer carToRentId, LocalDate inspectionEndDate) {
        technicalInspectionService.updateExpiryDate(technicalInspectionId, inspectionEndDate);
        return "redirect:/tech/technicalInspection?carToRentId=" + carToRentId;
    }

    @PostMapping(value = "/admin/updateTechIssue")
    public String updateTechnicalIssueStatus(Integer carToRentId, CarStatus carStatus) {
        carToRentService.updateTechnicalStatus(carToRentId, carStatus);
        return "redirect:/admin";
    }
}

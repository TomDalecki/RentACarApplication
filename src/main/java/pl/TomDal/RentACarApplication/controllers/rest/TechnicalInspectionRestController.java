package pl.TomDal.RentACarApplication.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.TomDal.RentACarApplication.controllers.dto.TechnicalInspectionDTO;
import pl.TomDal.RentACarApplication.domain.TechnicalInspection;
import pl.TomDal.RentACarApplication.services.TechnicalInspectionService;

import java.time.LocalDate;

@RestController
@RequestMapping(TechnicalInspectionRestController.CAR_TO_RENT)
@AllArgsConstructor
public class TechnicalInspectionRestController {
    public static final String CAR_TO_RENT = "/api";
    public static final String CAR_TO_RENT_UPDATE_TECH_INSP_PUT = "/updateTechInsp/{carIdNumber}";
    public static final String CAR_TO_RENT_UPDATE_TECH_INSP_PATCH = "/updateTechInsp/{carIdNumber}/inspDate";
    private TechnicalInspectionService technicalInspectionService;

    @PatchMapping(CAR_TO_RENT_UPDATE_TECH_INSP_PATCH)
    public ResponseEntity<?> updateTechInspectionDate(
            @PathVariable Integer carIdNumber,
            @RequestParam LocalDate newInspDate
    ) {
        TechnicalInspection existingTechnicalInspection = technicalInspectionService
                .findInspectionDetailByCarId(carIdNumber);

        technicalInspectionService.updateExpiryDate(existingTechnicalInspection.getTechnicalInspectionId(), newInspDate);
        return ResponseEntity.ok().build();
    }

    @PutMapping(CAR_TO_RENT_UPDATE_TECH_INSP_PUT)
    public ResponseEntity<?> updateTechInspectionDate(
            @PathVariable Integer carIdNumber,
            @RequestBody TechnicalInspectionDTO technicalInspectionDTO
    ) {
        TechnicalInspection existingTechnicalInspection = technicalInspectionService
                .findInspectionDetailByCarId(carIdNumber);

        technicalInspectionService.updateExpiryDate(existingTechnicalInspection.getTechnicalInspectionId(),
                technicalInspectionDTO.getInspectionExpiryDate());

        return ResponseEntity.ok().build();
    }

}

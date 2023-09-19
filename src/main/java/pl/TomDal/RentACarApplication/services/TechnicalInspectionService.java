package pl.TomDal.RentACarApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.TomDal.RentACarApplication.domain.TechnicalInspection;
import pl.TomDal.RentACarApplication.repository.mapper.TechnicalInspectionEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.TechnicalInspectionDAO;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class TechnicalInspectionService {
    TechnicalInspectionDAO technicalInspectionDAO;
    TechnicalInspectionEntityMapper technicalInspectionEntityMapper;

    public TechnicalInspection findInspectionDetailByCarId(Integer carToRentId) {
       return technicalInspectionEntityMapper
               .mapFromEntity(technicalInspectionDAO.findInspectionDetailByCarId(carToRentId).orElseThrow());
    }

    public void updateExpiryDate(Integer technicalInspectionId, LocalDate inspectionEndDate) {
        technicalInspectionDAO.updateExpiryDate(technicalInspectionId, inspectionEndDate);
    }
}

package pl.TomDal.RentACarApplication.services.dao;

import pl.TomDal.RentACarApplication.entity.TechnicalInspectionEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface TechnicalInspectionDAO {
    Optional<TechnicalInspectionEntity> findInspectionDetailByCarId(Integer carToRentId);

    void updateExpiryDate(Integer technicalInspectionId, LocalDate inspectionEndDate);

    void saveTechnicalInspection(Integer carToRentId, LocalDate technicalInspectionDate);
}

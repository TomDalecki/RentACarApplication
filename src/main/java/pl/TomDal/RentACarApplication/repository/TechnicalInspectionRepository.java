package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.entity.CarToRentEntity;
import pl.TomDal.RentACarApplication.entity.TechnicalInspectionEntity;
import pl.TomDal.RentACarApplication.repository.jpa.TechnicalInspectionJpaRepository;
import pl.TomDal.RentACarApplication.repository.mapper.TechnicalInspectionEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.TechnicalInspectionDAO;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TechnicalInspectionRepository implements TechnicalInspectionDAO {
   TechnicalInspectionJpaRepository technicalInspectionJpaRepository;
   TechnicalInspectionEntityMapper technicalInspectionEntityMapper;

    @Override
    public Optional<TechnicalInspectionEntity> findInspectionDetailByCarId(Integer carToRentId) {
        return technicalInspectionJpaRepository.findByCarToRent_CarToRentId(carToRentId);
    }


    @Override
    public void updateExpiryDate(Integer technicalInspectionId, LocalDate inspectionEndDate) {
        technicalInspectionJpaRepository
                .updateInspectionExpiryDateByTechnicalInspectionId(inspectionEndDate, technicalInspectionId);
    }

    @Override
    public void saveTechnicalInspection(Integer carToRentId, LocalDate technicalInspectionDate) {
        TechnicalInspectionEntity technicalInspectionEntity = TechnicalInspectionEntity.builder()
                .inspectionExpiryDate(technicalInspectionDate)
                .carToRent(CarToRentEntity.builder()
                        .carToRentId(carToRentId)
                        .build())
                .build();
        technicalInspectionJpaRepository.save(technicalInspectionEntity);
    }
}

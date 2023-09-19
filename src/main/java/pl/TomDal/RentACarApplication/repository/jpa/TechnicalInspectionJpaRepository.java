package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.entity.TechnicalInspectionEntity;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TechnicalInspectionJpaRepository extends JpaRepository<TechnicalInspectionEntity, Integer> {

    Optional<TechnicalInspectionEntity> findByCarToRent_CarToRentId(Integer carToRentId);


    @Transactional
    @Modifying
    @Query("update TechnicalInspectionEntity t set t.inspectionExpiryDate = ?1 where t.technicalInspectionId = ?2")
    void updateInspectionExpiryDateByTechnicalInspectionId(LocalDate inspectionExpiryDate, Integer technicalInspectionId);
//
//    @Query("SELECT t FROM TechnicalInspectionEntity t WHERE t.carToRent.carToRentId = ?1")
//    Optional<TechnicalInspectionEntity> findByCarToRent_CarToRentId2(Integer carToRentId);
//
//    @Query("""
//            SELECT ti FROM TechnicalInspectionEntity ti
//            WHERE ti.carToRent.carToRentId = :carToRentId
//            """)
//    Optional<TechnicalInspectionEntity> findByCarToRent_CarToRentId3(Integer carToRentId);
//
//    @Query(value = """
//            SELECT ti.*
//            FROM car_technical_inspection ti
//            JOIN car_to_rent cr ON ti.car_to_rent_id = cr.car_to_rent_id
//            WHERE cr.car_to_rent_id = :carToRentId
//            """, nativeQuery = true)
//    Optional<TechnicalInspectionEntity> findByCarToRent_CarToRentId4(Integer carToRentId);


}

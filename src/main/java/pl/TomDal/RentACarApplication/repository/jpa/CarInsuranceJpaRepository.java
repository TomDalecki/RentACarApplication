package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.entity.CarInsuranceEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarInsuranceJpaRepository extends JpaRepository<CarInsuranceEntity, Integer> {

    @Transactional
    @Modifying
    @Query("update CarInsuranceEntity c set c.insuranceEndDate = ?1 where c.carInsuranceId = ?2")
    void updateInsuranceEndDateByCarInsuranceId(LocalDate insuranceEndDate, Integer carInsuranceId);

    List<CarInsuranceEntity> findByCarToRent_CarToRentId(Integer carToRentId);
}

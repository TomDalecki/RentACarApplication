package pl.TomDal.RentACarApplication.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import pl.TomDal.RentACarApplication.domain.CarInsurance;
import pl.TomDal.RentACarApplication.entity.CarInsuranceEntity;
import pl.TomDal.RentACarApplication.repository.mapper.CarInsuranceEntityMapper;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@DataJpaTest
@ComponentScan(basePackages = "pl.TomDal.RentACarApplication")
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CarInsuranceRepositoryTest {

    private final CarInsuranceRepository carInsuranceRepository;
    private final CarInsuranceEntityMapper carInsuranceEntityMapper;
    private final CarToRentRepository carToRentRepository;
    private final EntityManager entityManager;
    private Integer carToRentID;

    @BeforeEach
    void setUp() {
        carToRentRepository.saveCar(TestDataFixtures.testCar1().withCarToRentId(1));

        carToRentID = carToRentRepository.findByCarIdNumber(TestDataFixtures.testCar1().getCarIdNumber())
                .orElseThrow().getCarToRentId();
    }

    @Test
    void thatSavingInsuranceWorksCorrectly() {
        //given
        carInsuranceRepository.saveInsurance(carInsuranceEntityMapper
                .mapToEntity(TestDataFixtures.testACInsurance1()
                        .withCarToRent(TestDataFixtures.testCar1().withCarToRentId(carToRentID))));

        carInsuranceRepository.saveInsurance(carInsuranceEntityMapper
                .mapToEntity(TestDataFixtures.testOCInsurance1()
                        .withCarToRent(TestDataFixtures.testCar1().withCarToRentId(carToRentID))));

        //when
        List<CarInsurance> result = carInsuranceRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void thatFindingCarInsuranceByCarIdWorksCorrectly() {
        //given
        carInsuranceRepository.saveInsurance(carInsuranceEntityMapper
                .mapToEntity(TestDataFixtures.testACInsurance1()
                        .withCarToRent(TestDataFixtures.testCar1().withCarToRentId(carToRentID))));

        carInsuranceRepository.saveInsurance(carInsuranceEntityMapper
                .mapToEntity(TestDataFixtures.testOCInsurance1()
                        .withCarToRent(TestDataFixtures.testCar1().withCarToRentId(carToRentID))));

        //when
        List<CarInsurance> result = carInsuranceRepository.findCarInsuranceByCarId(carToRentID);

        //then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void updateInsuranceEndDate() {
        //given
        carInsuranceRepository.saveInsurance(carInsuranceEntityMapper
                .mapToEntity(TestDataFixtures.testACInsurance1()
                        .withInsuranceEndDate(LocalDate.of(2023, 1, 1))
                        .withCarToRent(TestDataFixtures.testCar1().withCarToRentId(carToRentID))));

        Integer insuranceID = carInsuranceRepository.findAll().stream()
                .map(CarInsurance::getCarInsuranceId)
                .findFirst().orElseThrow();

        LocalDate newInsuranceEndDate = LocalDate.now();

        //when
        carInsuranceRepository.updateInsuranceEndDate(insuranceID, newInsuranceEndDate);

        CarInsuranceEntity carInsuranceEntity = entityManager.find(CarInsuranceEntity.class, insuranceID);
        entityManager.refresh(carInsuranceEntity);

        LocalDate result = carInsuranceRepository.findAll().stream()
                .map(CarInsurance::getInsuranceEndDate)
                .findFirst().orElseThrow();

        //then
        assertEquals(newInsuranceEndDate, result);
    }
}
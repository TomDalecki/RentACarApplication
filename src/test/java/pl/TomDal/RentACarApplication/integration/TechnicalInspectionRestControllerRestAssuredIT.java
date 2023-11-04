package pl.TomDal.RentACarApplication.integration;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.rest.CarToRentRestController;
import pl.TomDal.RentACarApplication.integration.configuration.RestAssuredIntegrationTestBase;
import pl.TomDal.RentACarApplication.integration.support.CarToRentRestControllerTestSupport;
import pl.TomDal.RentACarApplication.integration.support.TechnicalInspectionRestControllerTestSupport;
import pl.TomDal.RentACarApplication.repository.jpa.CarToRentJpaRepository;
import pl.TomDal.RentACarApplication.util.TestDtoDataFixtures;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


public class TechnicalInspectionRestControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
        implements TechnicalInspectionRestControllerTestSupport, CarToRentRestControllerTestSupport {

    @Autowired
    private CarToRentJpaRepository carToRentJpaRepository;

    @AfterEach
    public void after() {
        carToRentJpaRepository.deleteAll();
    }

    @Test
    void thatTechnicalInspectionCanBeUpdatedCorrectly(){
        //given
        CarToRentDTO carToRentDTO1 = TestDtoDataFixtures.testCarDTO1();
        CarToRentRestController.CarToRentRestDTO carToRentRestDTO1 = new CarToRentRestController.CarToRentRestDTO();
        carToRentRestDTO1.setCarToRentDTO(carToRentDTO1);
        carToRentRestDTO1.setTechnicalInspectionDate(LocalDate.now());
        addCarToRent(carToRentRestDTO1);
        Integer carIdNumber = carToRentJpaRepository.findByCarIdNumber(carToRentDTO1.getCarIdNumber())
                .orElseThrow().getCarToRentId();
        String newInspDate = "2023-11-04";

        //when
        ExtractableResponse<Response> response = updateTechnicalInspectionDate(carIdNumber, newInspDate);

        //then
        String responseAsString = response.body().asString();
        assertThat(responseAsString).isEmpty();
    }

    @Test
    void thatTechnicalInspectionCanNotBeUpdatedCorrectlyDueToNotExistingCarIdNumber(){
        //given
        Integer carIdNumber = 999;
        String newInspDate = "2023-11-04";

        //when
        ExtractableResponse<Response> response = updateTechnicalInspectionDate_carIDNumberDoesNotExist(carIdNumber, newInspDate);

        //then
        String responseAsString = response.body().asString();
        assertThat(responseAsString).isNotEmpty();
    }
}

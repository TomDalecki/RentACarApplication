package pl.TomDal.RentACarApplication.integration;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.rest.CarToRentRestController;
import pl.TomDal.RentACarApplication.integration.configuration.RestAssuredIntegrationTestBase;
import pl.TomDal.RentACarApplication.integration.support.AdminRestControllerTestSupport;
import pl.TomDal.RentACarApplication.integration.support.CarToRentRestControllerTestSupport;
import pl.TomDal.RentACarApplication.util.TestDtoDataFixtures;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


public class AdminRestControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
        implements AdminRestControllerTestSupport, CarToRentRestControllerTestSupport {

    @Test
    void thatCarCanBeDeletedCorrectly(){
        //given
        CarToRentDTO carToRentDTO1 = TestDtoDataFixtures.testCarDTO1();
        CarToRentRestController.CarToRentRestDTO carToRentRestDTO1 = new CarToRentRestController.CarToRentRestDTO();
        carToRentRestDTO1.setCarToRentDTO(carToRentDTO1);
        carToRentRestDTO1.setTechnicalInspectionDate(LocalDate.now());
        addCarToRent(carToRentRestDTO1);

        //when
        ExtractableResponse<Response> response = deleteCarToRent(carToRentDTO1.getVin());

        //then
        String responseAsString = response.body().asString();
        assertThat(responseAsString).isEmpty();
    }


    @Test
    void thatCarCantBeDeletedWhenVinDoesNotExist(){
        //given
        String vin = "nonExistentVin";

        //when
        ExtractableResponse<Response> response = deleteCarToRent_notExistentVin(vin);

        //then
        String responseAsString = response.body().asString();
        assertThat(responseAsString).isEmpty();
    }
}

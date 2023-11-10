package pl.TomDal.RentACarApplication.integration.support;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.TomDal.RentACarApplication.controllers.rest.TechnicalInspectionRestController;

import static pl.TomDal.RentACarApplication.controllers.rest.TechnicalInspectionRestController.CAR_TO_RENT_UPDATE_TECH_INSP_PATCH;

public interface TechnicalInspectionRestControllerTestSupport {

    RequestSpecification requestSpecification();

    default ExtractableResponse<Response> updateTechnicalInspectionDate(Integer carIdNumber, String newInspDate){
        return requestSpecification()
                .pathParam("carIdNumber", carIdNumber)
                .queryParam("newInspDate", newInspDate)
                .patch(TechnicalInspectionRestController.CAR_TO_RENT + CAR_TO_RENT_UPDATE_TECH_INSP_PATCH)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract();
    }

    default ExtractableResponse<Response> updateTechnicalInspectionDate_carIDNumberDoesNotExist(Integer carIdNumber, String newInspDate){
        return requestSpecification()
                .pathParam("carIdNumber", carIdNumber)
                .queryParam("newInspDate", newInspDate)
                .patch(TechnicalInspectionRestController.CAR_TO_RENT + CAR_TO_RENT_UPDATE_TECH_INSP_PATCH)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .and()
                .extract();
    }
}

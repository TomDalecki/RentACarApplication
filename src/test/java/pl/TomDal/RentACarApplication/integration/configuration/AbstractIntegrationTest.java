package pl.TomDal.RentACarApplication.integration.configuration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.TomDal.RentACarApplication.controllers.rest.AdminRestController;
import pl.TomDal.RentACarApplication.controllers.rest.CarToRentRestController;
import pl.TomDal.RentACarApplication.controllers.rest.TechnicalInspectionRestController;

@ActiveProfiles("test")
@SpringBootTest(
        classes = {AdminRestController.class, CarToRentRestController.class, TechnicalInspectionRestController.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest {

}
